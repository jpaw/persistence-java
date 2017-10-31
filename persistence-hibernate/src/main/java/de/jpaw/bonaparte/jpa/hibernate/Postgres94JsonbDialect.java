package de.jpaw.bonaparte.jpa.hibernate;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class Postgres94JsonbDialect extends PostgreSQL94Dialect {
    public Postgres94JsonbDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
