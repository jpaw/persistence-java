package de.jpaw.bonaparte.jpa;

import java.sql.Time;
import java.sql.Types;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.LocalTime;

public class JodaLocalTimeConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : LocalTime.fromDateFields((Time) dataValue);
    }

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : new Time(((LocalTime) objectValue).getMillisOfDay());
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        ((AbstractDirectMapping) mapping).setFieldType(Types.TIME);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

}
