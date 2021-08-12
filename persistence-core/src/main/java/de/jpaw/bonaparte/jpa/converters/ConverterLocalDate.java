package de.jpaw.bonaparte.jpa.converters;

import java.sql.Date;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.joda.time.LocalDate;

@Converter(autoApply = true)
public class ConverterLocalDate implements AttributeConverter<LocalDate, Date> {

    public Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : new Date(localDate.toDate().getTime());
    }

    public LocalDate convertToEntityAttribute(Date date) {
        return date == null ? null : LocalDate.fromDateFields(date);
    }
}
