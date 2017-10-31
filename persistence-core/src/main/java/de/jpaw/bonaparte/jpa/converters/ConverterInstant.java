package de.jpaw.bonaparte.jpa.converters;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class ConverterInstant implements AttributeConverter<Instant, Timestamp> {
	static final private Logger LOGGER = LoggerFactory.getLogger(ConverterInstant.class);
	public ConverterInstant() {
		System.out.println("************************************************************** New converter");
		LOGGER.info("************************************************************** New converter");
	}
	
    @Override
    public Timestamp convertToDatabaseColumn(Instant attribute) {
        return attribute == null ? null : new Timestamp(attribute.getMillis());
    }

    @Override
    public Instant convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : new Instant(dbData.getTime());
    }
}
