package de.jpaw.bonaparte.jpa.converters;

import java.sql.Time;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

@Converter(autoApply = true)
public class ConverterLocalTime implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime attribute) {
        return attribute == null ? null : new Time(attribute.getMillisOfDay());
    }

    @Override
    public LocalTime convertToEntityAttribute(Time dbData) {
        return dbData == null ? null : new LocalTime(dbData.getTime(), DateTimeZone.UTC);
    }
}
