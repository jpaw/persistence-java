package de.jpaw.bonaparte.jpa;

import java.sql.Timestamp;
import java.sql.Types;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.LocalDateTime;

public class JodaLocalDateTimeConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : LocalDateTime.fromDateFields((Timestamp) dataValue);
    }

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : new Timestamp(((LocalDateTime) objectValue).toDate().getTime());
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
