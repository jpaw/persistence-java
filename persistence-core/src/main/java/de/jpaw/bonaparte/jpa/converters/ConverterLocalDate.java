package de.jpaw.bonaparte.jpa.converters;

import java.sql.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
