package de.jpaw.bonaparte.jpa.converters;

import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.jpaw.bonaparte.jpa.json.CompactJsonArray;

// persists a serialized object in a jsonb column (Postgres) or corresponding (other DBs)
@Converter(autoApply = true)
public class ConverterNativeJsonArray implements AttributeConverter<CompactJsonArray, Object> {
    @Override
    public Object convertToDatabaseColumn(CompactJsonArray obj) {
        return obj == null ? null : obj.getData();
    }

    @Override
    public CompactJsonArray convertToEntityAttribute(Object data) {
        return data != null && data instanceof List ? new CompactJsonArray((List)data) : null;
    }
}
