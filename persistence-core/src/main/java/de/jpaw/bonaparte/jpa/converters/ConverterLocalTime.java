package de.jpaw.bonaparte.jpa.converters;

import org.joda.time.LocalTime;
import java.sql.Time;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConverterLocalTime implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime attribute) {
        return attribute == null ? null : new Time(attribute.getMillisOfDay());
    }

    @Override
    public LocalTime convertToEntityAttribute(Time dbData) {
        return dbData == null ? null : new LocalTime(dbData.getTime());
    }
}
