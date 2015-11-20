package de.jpaw.bonaparte.jpa;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jpaw.bonaparte.core.BonaparteJsonEscaper;
import de.jpaw.bonaparte.core.CompactByteArrayComposer;
import de.jpaw.bonaparte.core.CompactByteArrayParser;
import de.jpaw.bonaparte.core.MessageParserException;
import de.jpaw.bonaparte.core.StaticMeta;
import de.jpaw.json.DeepCopy;
import de.jpaw.json.JsonException;
import de.jpaw.json.JsonParser;

// implementation of a User type to map Map<String,Object> to a NVARCHAR (or jsonb on postgres databases)
public class ArrayUserType implements UserType {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ArrayUserType.class);

    // returns the Java type. Input rs
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        String json = rs.getNString(names[0]);
        if (rs.wasNull()) {
            return null;
        }
        try {
            return new NativeJsonObject(new JsonParser(json, false).parseObject());
        } catch (JsonException e) {
            LOGGER.error("Cannot parse JSON data: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // convert a Map to a JSON string
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.NVARCHAR);
        } else {
            st.setNString(index, BonaparteJsonEscaper.asJson(((NativeJsonObject) value).getData()));
        }
    }

    @Override
    public Class<NativeJsonObject> returnedClass() {
        return NativeJsonObject.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.NVARCHAR };
    }

    // common implementation
    // http://blog.xebia.com/2009/11/09/understanding-and-writing-hibernate-user-types/

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public boolean equals(Object object1, Object object2) throws HibernateException {
        if (object1 == object2) {
            return true;
        }
        if ((object1 == null) || (object2 == null)) {
            return false;
        }
        return object1.equals(object2);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        assert (x != null);
        return x.hashCode();
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return DeepCopy.deepCopy(value);
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        // use a compact for for caching
        return CompactByteArrayComposer.marshalAsArray(StaticMeta.OUTER_BONAPORTABLE_FOR_ARRAY, (List<Object>)value); 
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        try {
            return CompactByteArrayParser.unmarshalArray((byte [])cached, StaticMeta.OUTER_BONAPORTABLE_FOR_ARRAY);
        } catch (MessageParserException e) {
            return new HibernateException(e);
        } 
    }
}
