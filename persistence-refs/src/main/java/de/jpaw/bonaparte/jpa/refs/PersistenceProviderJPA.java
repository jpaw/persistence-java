package de.jpaw.bonaparte.jpa.refs;

import javax.persistence.EntityManager;

import de.jpaw.bonaparte.refs.PersistenceProvider;

public interface PersistenceProviderJPA extends PersistenceProvider {
    EntityManager getEntityManager();
}
