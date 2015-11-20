package de.jpaw.bonaparte.jpa;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.hibernate.type.PostgresUUIDType;
import org.hibernate.type.UUIDBinaryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jpaw.util.ByteArray;

public class UserTypeBonaparteIntegrator implements Integrator {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeBonaparteIntegrator.class);

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // no-op
    }

    @Override
    public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        configuration.registerTypeOverride(new ByteArrayUserType(), new String[] { ByteArray.class.getName() });
        configuration.registerTypeOverride(new JsonUserType(),      new String[] { NativeJsonObject.class.getName() });
        configuration.registerTypeOverride(new ArrayUserType(),     new String[] { NativeJsonArray.class.getName() });
        configuration.registerTypeOverride(new ElementUserType(),   new String[] { NativeJsonElement.class.getName() });
        
        String dialect = configuration.getProperty("hibernate.dialect");
        if ("org.hibernate.dialect.PostgreSQL9Dialect".equals(dialect)) {
            LOGGER.info("Postgres DB configured - using special UUID DB type");
            // configuration.registerTypeOverride(new PostgresUUIDType());
            configuration.registerTypeOverride(new UuidType());  // must have "java.util.UUID" as getName()!
        } else {
            LOGGER.info("DB type is " + ( dialect == null ? "null" : dialect) + " - using binary UUID DB type");
            configuration.registerTypeOverride(new UUIDBinaryType());
        }
    }

    @Override
    public void integrate(MetadataImplementor metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // no-op
    }

}
