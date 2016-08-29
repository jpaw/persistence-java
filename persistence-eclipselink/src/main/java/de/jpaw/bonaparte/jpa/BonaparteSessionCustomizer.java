package de.jpaw.bonaparte.jpa;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.mappings.foundation.AbstractDirectMapping;
import org.eclipse.persistence.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Standard session customizer, which adds types for the JSON types.
 * Native UUIDs with Postgres work out of the box for Eclipselink.
 *
 */
public class BonaparteSessionCustomizer implements SessionCustomizer {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BonaparteSessionCustomizer.class);

    protected final Map<Class<?>, Converter> convertersPerType = new HashMap<Class<?>, Converter>();

    public BonaparteSessionCustomizer() {
        LOGGER.info("BonaparteSessionCustomizer with JSON mapping created");
//        convertersPerType.put(ByteArray.class,          new ByteArrayConverter());
        convertersPerType.put(NativeJsonObject.class,   new JsonConverter());
        convertersPerType.put(NativeJsonArray.class,    new ArrayConverter());
        convertersPerType.put(NativeJsonElement.class,  new ElementConverter());
    }

    @Override
    public void customize(Session session) throws Exception {
        LOGGER.trace("customizing session");
        // Iterate through the meta-data of all known JPA entities
        Map<Class, ClassDescriptor> descriptors = session.getDescriptors();
        for (ClassDescriptor descriptor : descriptors.values()) {
            Map<String, Converter> fieldsMap = collectConverters(descriptor.getJavaClass());

            // Iterate through the meta-data of all fields for this particular JPA entity
            for (DatabaseMapping mapping : descriptor.getMappings()) {
                if (mapping instanceof AbstractDirectMapping) {
                    AbstractDirectMapping directMapping = (AbstractDirectMapping) mapping;
                    directMapping.setConverter(fieldsMap.get(directMapping.getAttributeName()));
                }
            }
        }
    }

    private Map<String, Converter> collectConverters(Class<?> javaClass) {
        Map<String, Converter> result = new HashMap<>();
        for (Field field : javaClass.getDeclaredFields()) {
            result.put(field.getName(), convertersPerType.get(field.getType()));
        }
        return result;
    }

}
