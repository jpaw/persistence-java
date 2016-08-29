package de.jpaw.bonaparte.jpa.converters;

import java.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConverterInstant implements AttributeConverter<Instant, java.sql.Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(Instant attribute) {
        return attribute == null ? null : java.sql.Timestamp.from(attribute);
    }

    @Override
    public Instant convertToEntityAttribute(java.sql.Timestamp dbData) {
        return dbData == null ? null : dbData.toInstant();
    }
}
