package de.jpaw.bonaparte.jpa.converters;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.LocalDateTime;

@Converter(autoApply = true)
public class ConverterLocalDateTime implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : new Timestamp(attribute.toDate().getTime());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : new LocalDateTime(dbData.getTime());
    }
}
