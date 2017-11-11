package de.jpaw.bonaparte.jpa.converters;

import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class ConvertersTest {

    @Test
    public void testInstantConverter() throws Exception {
        final ConverterInstant converter = new ConverterInstant();
        final Instant now = new Instant();
        final Instant then = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now));
        Assert.assertEquals(now,  then);
    }

    @Test
    public void testLocalDateConverter() throws Exception {
        final ConverterLocalDate converter = new ConverterLocalDate();
        final LocalDate now = new LocalDate();
        final LocalDate then = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now));
        Assert.assertEquals(now,  then);
    }

    @Test
    public void testLocalDateTimeConverter() throws Exception {
        final ConverterLocalDateTime converter = new ConverterLocalDateTime();
        final LocalDateTime now = new LocalDateTime();
        final LocalDateTime then = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now));
        Assert.assertEquals(now,  then);
    }

    @Test
    public void testLocalTimeConverter() throws Exception {
        final ConverterLocalTime converter = new ConverterLocalTime();
        final LocalTime now = new LocalTime();
        final LocalTime then = converter.convertToEntityAttribute(converter.convertToDatabaseColumn(now));
        Assert.assertEquals(now,  then);
    }
}
