package de.jpaw.bonaparte.jpa;

import java.sql.Date;
import java.sql.Types;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;
import org.eclipse.persistence.sessions.Session;
import org.joda.time.LocalDate;

public class JodaLocalDateConverter implements Converter {

    private static final long serialVersionUID = 1L;

    @Override
    public Object convertDataValueToObjectValue(Object dataValue, Session session) {
        return dataValue == null ? null : LocalDate.fromDateFields((Date) dataValue);
    }

    @Override
    public Object convertObjectValueToDataValue(Object objectValue, Session session) {
        return objectValue == null ? null : new Date(((LocalDate) objectValue).toDate().getTime());
    }

    @Override
    public void initialize(DatabaseMapping mapping, Session session) {
        ((AbstractDirectMapping) mapping).setFieldType(Types.DATE);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

}
