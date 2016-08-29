package de.jpaw.bonaparte.jpa.converters;

import java.time.LocalTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

// only required for Eclipselink, hibernate 5.2 knows it

@Converter(autoApply = true)
public class ConverterLocalTime implements AttributeConverter<LocalTime, java.sql.Time> {

    @Override
    public java.sql.Time convertToDatabaseColumn(LocalTime attribute) {
        return attribute == null ? null : java.sql.Time.valueOf(attribute);
    }

    @Override
    public LocalTime convertToEntityAttribute(java.sql.Time dbData) {
        return dbData == null ? null : dbData.toLocalTime();
    }
}
