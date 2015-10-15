package de.jpaw.bonaparte.jpa;

import java.sql.Types;
import java.util.Map;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;
import org.eclipse.persistence.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jpaw.bonaparte.core.BonaparteJsonEscaper;
import de.jpaw.json.JsonException;
import de.jpaw.json.JsonParser;

// convert between the Java type "Map<String, Object>" and a text, which in the database will be used as JSON
public class JsonConverter implements Converter {

    private static final long serialVersionUID = 166786L;
    protected static final Logger LOGGER = LoggerFactory.getLogger(JsonConverter.class);

    // parse String to Map
    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        try {
            return dataValue == null ? null : new JsonParser((String) dataValue, false).parseObject();
        } catch (JsonException e) {
            LOGGER.error("Cannot parse JSON data: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // print Map in JSON format, also expand any BonaPortables included
    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : BonaparteJsonEscaper.asJson((Map<String,Object>) objectValue);
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        ((AbstractDirectMapping) mapping).setFieldType(Types.NCLOB);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

}
