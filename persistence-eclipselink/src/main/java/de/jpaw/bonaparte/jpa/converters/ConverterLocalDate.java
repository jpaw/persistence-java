package de.jpaw.bonaparte.jpa.converters;

import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// only required for Eclipselink, hibernate 5.2 knows it

@Converter(autoApply = true)
public class ConverterLocalDate implements AttributeConverter<LocalDate, java.sql.Date> {

    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate attribute) {
        return attribute == null ? null : java.sql.Date.valueOf(attribute);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date dbData) {
        return dbData == null ? null : dbData.toLocalDate();
    }
}
