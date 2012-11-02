package de.jpaw.bonaparte.jpa;

import java.util.Map;
import java.util.Vector;

import org.eclipse.persistence.exceptions.ConversionException;
import org.eclipse.persistence.internal.helper.ConversionManager;

public class BonaparteConversionManager extends ConversionManager {

    private ConversionManager cm;

    public BonaparteConversionManager(ConversionManager cm) {
        this.cm = cm;

        customizeConversionManager();
    }

    private void customizeConversionManager() {

    }


    // simple delegates

    @Override
    public Object clone() {
        return cm.clone();
    }

    @Override
    public Class convertClassNameToClass(String className) throws ConversionException {
        return cm.convertClassNameToClass(className);
    }

    @Override
    public Object convertObject(Object sourceObject, Class javaClass) throws ConversionException {
        return cm.convertObject(sourceObject, javaClass);
    }

    @Override
    public boolean equals(Object obj) {
        return cm.equals(obj);
    }

    @Override
    public Vector getDataTypesConvertedFrom(Class javaClass) {
        return cm.getDataTypesConvertedFrom(javaClass);
    }

    @Override
    public Vector getDataTypesConvertedTo(Class javaClass) {
        return cm.getDataTypesConvertedTo(javaClass);
    }

    @Override
    public Object getDefaultNullValue(Class theClass) {
        return cm.getDefaultNullValue(theClass);
    }

    @Override
    public Map getDefaultNullValues() {
        return cm.getDefaultNullValues();
    }

    @Override
    public ClassLoader getLoader() {
        return cm.getLoader();
    }

    @Override
    public boolean hasDefaultNullValues() {
        return cm.hasDefaultNullValues();
    }

    @Override
    public int hashCode() {
        return cm.hashCode();
    }

    @Override
    public void setDefaultNullValue(Class theClass, Object theValue) {
        cm.setDefaultNullValue(theClass, theValue);
    }

    @Override
    public void setDefaultNullValues(Map defaultNullValues) {
        cm.setDefaultNullValues(defaultNullValues);
    }

    @Override
    public void setLoader(ClassLoader classLoader) {
        cm.setLoader(classLoader);
    }

    @Override
    public void setShouldUseClassLoaderFromCurrentThread(boolean useCurrentThread) {
        cm.setShouldUseClassLoaderFromCurrentThread(useCurrentThread);
    }

    @Override
    public boolean shouldUseClassLoaderFromCurrentThread() {
        return cm.shouldUseClassLoaderFromCurrentThread();
    }

    @Override
    public String toString() {
        return cm.toString();
    }

}
