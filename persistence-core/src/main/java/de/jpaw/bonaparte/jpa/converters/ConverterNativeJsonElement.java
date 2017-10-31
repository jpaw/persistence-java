package de.jpaw.bonaparte.jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.jpaw.bonaparte.jpa.json.CompactJsonElement;

// persists a serialized object in a jsonb column (Postgres) or corresponding (other DBs)
@Converter(autoApply = true)
public class ConverterNativeJsonElement implements AttributeConverter<CompactJsonElement, Object> {
    @Override
    public Object convertToDatabaseColumn(CompactJsonElement obj) {
        return obj == null ? null : obj.getData();
    }

    @Override
    public CompactJsonElement convertToEntityAttribute(Object data) {
        return data != null ? new CompactJsonElement(data) : null;
    }
}
