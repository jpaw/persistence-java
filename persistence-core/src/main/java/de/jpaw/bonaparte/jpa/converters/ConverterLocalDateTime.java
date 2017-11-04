package de.jpaw.bonaparte.jpa.converters;

import java.sql.Timestamp;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Converter(autoApply = true)
public class ConverterLocalDateTime implements AttributeConverter<LocalDateTime, Timestamp> {
	static final private Logger LOGGER = LoggerFactory.getLogger(ConverterInstant.class);
	public ConverterLocalDateTime() {
		LOGGER.debug("Conversion Joda LocalDateTime / sql Timestamp instantiated");
	}

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null ? null : new Timestamp(attribute.toDateTime().getMillis());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null ? null : (new DateTime(dbData.getTime()).toLocalDateTime());
    }
}
