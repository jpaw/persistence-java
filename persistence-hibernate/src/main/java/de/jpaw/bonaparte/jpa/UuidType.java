package de.jpaw.bonaparte.jpa;

import java.util.UUID;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.PostgresUUIDType;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;

/** Special UUID type for Postgres - using the name java.util.UUID */
public class UuidType extends AbstractSingleColumnStandardBasicType<UUID> {

    public UuidType() {
        super(PostgresUUIDType.PostgresUUIDSqlTypeDescriptor.INSTANCE, UUIDTypeDescriptor.INSTANCE);
    }

    @Override
    public String getName() {
        return "java.util.UUID";
    }
}
