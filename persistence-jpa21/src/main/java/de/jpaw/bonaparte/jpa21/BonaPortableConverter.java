package de.jpaw.bonaparte.jpa21;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import de.jpaw.bonaparte.core.BonaPortable;
import de.jpaw.bonaparte.core.ByteArrayComposer;
import de.jpaw.bonaparte.core.ByteArrayParser;
import de.jpaw.bonaparte.core.MessageParserException;
import de.jpaw.bonaparte.core.StaticMeta;

@Converter(autoApply = true)
public class BonaPortableConverter implements AttributeConverter<BonaPortable, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(BonaPortable obj) {
        if (obj == null)
            return null;
        ByteArrayComposer composer = new ByteArrayComposer();
        composer.addField(StaticMeta.OUTER_BONAPORTABLE, obj);
        return composer.getBytes();
    }

    @Override
    public BonaPortable convertToEntityAttribute(byte[] data) {
        if (data == null || data.length == 0)
            return null;
        try {
            ByteArrayParser bap = new ByteArrayParser(data, 0, -1);
            return bap.readObject(StaticMeta.OUTER_BONAPORTABLE, BonaPortable.class);
        } catch (MessageParserException e) {
            throw new RuntimeException(e);
        }
    }
}
