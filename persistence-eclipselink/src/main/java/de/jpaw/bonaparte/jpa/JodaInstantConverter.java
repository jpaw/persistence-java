package de.jpaw.bonaparte.jpa;

import java.sql.Timestamp;
import java.sql.Types;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.Instant;

public class JodaInstantConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : new Instant(dataValue);       // Timestamp is a superclass of Date which is a valid input type
    }

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : new Timestamp(((Instant) objectValue).getMillis());
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        ((AbstractDirectMapping) mapping).setFieldType(Types.TIMESTAMP);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

}
