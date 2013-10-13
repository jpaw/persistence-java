package de.jpaw.bonaparte.jpa;

import java.util.Map;

/** Various utility methods to retrieve properties from the persistence.xml.
 * Some cases are straightforward, others depend on the OR mapper used.
 *
 */
public class PersistenceUnitProps {

    /** Just a shorthand to get the JDBC driver name from the persistence.xml. */
    public static String getJdbcDriver(Map<String,Object> properties) {
        return (String)properties.get("javax.persistence.jdbc.driver");
    }
    /** Just a shorthand to get the JDBC connection URL from the persistence.xml. */
    public static String getJdbcUrl(Map<String,Object> properties) {
        return (String)properties.get("javax.persistence.jdbc.url");
    }
    /** Just a shorthand to get the JDBC user name from the persistence.xml. */
    public static String getJdbcUserId(Map<String,Object> properties) {
        return (String)properties.get("javax.persistence.jdbc.user");
    }
    /** Just a shorthand to get the JDBC user's password from the persistence.xml. */
    public static String getJdbcPassword(Map<String,Object> properties) {
        return (String)properties.get("javax.persistence.jdbc.password");
    }

    // access to the "provider" is not possible? So we have to guess it from other properties.
    // One which is always available is the database
    public static ORMapper getORMapper(Map<String,Object> properties) {
        if (properties.get("eclipselink.target-database") != null)
            return ORMapper.ECLIPSELINK;
        if (properties.get("hibernate.dialect") != null)
            return ORMapper.HIBERNATE;
        return ORMapper.UNSUPPORTED;
    }
    
    private static String normalized(Object dbo) {
        if (dbo instanceof String) {
            // strip off any fully qualified class name prefix
            String db = (String)dbo;
            int pos = db.lastIndexOf(".");
            if (pos > 0)
                db = db.substring(pos+1);
            return db;
        }
        if (dbo instanceof Enum) {
            // convert it to its token
            return ((Enum<?>)dbo).name();
        }
        // some other class: use the simple class name
        return dbo.getClass().getSimpleName();
    }
    
    /** Returns a guess of the underlying database vendor. */
    public static DatabaseFlavour getDatabaseFlavour(Map<String,Object> properties) {
        Object dbo = properties.get("eclipselink.target-database");
        if (dbo != null) {
            String db = normalized(dbo);
            if ("PostgreSQL".equals(db))
                return DatabaseFlavour.POSTGRES;
            if (db.startsWith("Oracle"))
                return DatabaseFlavour.ORACLE;
            if ("SQLServer".equals(db))
                return DatabaseFlavour.MSSQLSERVER;
            if ("DB2".equals(db))
                return DatabaseFlavour.DB2;
            if ("Derby".equals(db))
                return DatabaseFlavour.DERBY;
            if ("MySQL".equals(db))
                return DatabaseFlavour.MYSQL;
            return DatabaseFlavour.UNSUPPORTED;
        }
        dbo = properties.get("hibernate.dialect");
        if (dbo != null) {
            String db = normalized(dbo);
            if ("PostgreSQLDialect".equals(db))
                return DatabaseFlavour.POSTGRES;
            if ("PostgresPlusDialect".equals(db))
                return DatabaseFlavour.POSTGRES;
            if (db.startsWith("Oracle"))
                return DatabaseFlavour.ORACLE;
            if ("SQLServer2008Dialect".equals(db))
                return DatabaseFlavour.MSSQLSERVER;
            if ("DB2Dialect".equals(db))
                return DatabaseFlavour.DB2;
            if ("DerbyDialect".equals(db))
                return DatabaseFlavour.DERBY;
            if ("H2Dialect".equals(db))
                return DatabaseFlavour.H2;
            if ("MySQL5Dialect".equals(db))
                return DatabaseFlavour.MYSQL;
            if ("MySQL5InnoDBDialect".equals(db))
                return DatabaseFlavour.MYSQL;
            return DatabaseFlavour.UNSUPPORTED;
        }
        return DatabaseFlavour.UNSUPPORTED;
    }
}
