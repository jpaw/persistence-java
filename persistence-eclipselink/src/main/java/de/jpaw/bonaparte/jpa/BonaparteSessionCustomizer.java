package de.jpaw.bonaparte.jpa;

import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.factories.SessionCustomizer;

public class BonaparteSessionCustomizer implements SessionCustomizer {

    @Override
    public void customize(Session session) throws Exception {
        session.getDatasourcePlatform().setConversionManager(new BonaparteConversionManager(session.getDatasourcePlatform().getConversionManager()));
    }
}
