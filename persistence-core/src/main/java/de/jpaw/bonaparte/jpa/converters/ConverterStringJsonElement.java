package de.jpaw.bonaparte.jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.jpaw.bonaparte.core.BonaparteJsonEscaper;
import de.jpaw.bonaparte.jpa.json.CompactJsonElement;
import de.jpaw.json.JsonParser;

// persists a serialized object in a CLOB / varchar(2)
@Converter(autoApply = true)
public class ConverterStringJsonElement implements AttributeConverter<CompactJsonElement, String> {
    @Override
    public String convertToDatabaseColumn(CompactJsonElement obj) {
        return obj == null ? null : BonaparteJsonEscaper.asJson(obj.getData());
    }

    @Override
    public CompactJsonElement convertToEntityAttribute(String data) {
    	if (data == null || data.length() == 0)
    		return null;
        final Object l = new JsonParser(data, true).parseElement();
        return l == null ? null : new CompactJsonElement(l);
    }
}
