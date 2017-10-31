package de.jpaw.bonaparte.jpa.converters;

import java.util.Map;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.jpaw.bonaparte.core.BonaparteJsonEscaper;
import de.jpaw.bonaparte.jpa.json.CompactJsonObject;
import de.jpaw.json.JsonParser;

// persists a serialized object in a CLOB / varchar(2)
@Converter(autoApply = true)
public class ConverterStringJsonObject implements AttributeConverter<CompactJsonObject, String> {
    @Override
    public String convertToDatabaseColumn(CompactJsonObject obj) {
        return obj == null ? null : BonaparteJsonEscaper.asJson(obj.getData());
    }

    @Override
    public CompactJsonObject convertToEntityAttribute(String data) {
    	if (data == null || data.length() == 0)
    		return null;
        Map<String, Object> l = new JsonParser(data, true).parseObject();
        return l == null ? null : new CompactJsonObject(l);
    }
}
