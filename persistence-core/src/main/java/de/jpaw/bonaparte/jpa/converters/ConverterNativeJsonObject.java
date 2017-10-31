package de.jpaw.bonaparte.jpa.converters;

import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.jpaw.bonaparte.jpa.json.CompactJsonObject;

// persists a serialized object in a jsonb column (Postgres) or corresponding (other DBs)
@Converter(autoApply = true)
public class ConverterNativeJsonObject implements AttributeConverter<CompactJsonObject, Object> {
    @Override
    public Object convertToDatabaseColumn(CompactJsonObject obj) {
        return obj == null ? null : obj.getData();
    }

    @Override
    public CompactJsonObject convertToEntityAttribute(Object data) {
        return data != null && data instanceof Map ? new CompactJsonObject((Map)data) : null;
    }
}
