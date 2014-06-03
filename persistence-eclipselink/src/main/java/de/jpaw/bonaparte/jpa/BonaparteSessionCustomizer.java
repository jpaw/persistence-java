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
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.jpaw.util.ByteArray;

public class BonaparteSessionCustomizer implements SessionCustomizer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BonaparteSessionCustomizer.class);

    private Map<Class<?>, Converter> convertersPerType;

    public BonaparteSessionCustomizer() {
        LOGGER.info("BonaparteSessionCustomizer created");
        convertersPerType = new HashMap<Class<?>, Converter>();
        convertersPerType.put(ByteArray.class, new ByteArrayConverter());
        convertersPerType.put(LocalDateTime.class, new JodaLocalDateTimeConverter());
        convertersPerType.put(LocalTime.class, new JodaLocalTimeConverter());
        convertersPerType.put(LocalDate.class, new JodaLocalDateConverter());
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void customize(Session session) throws Exception {
        LOGGER.debug("customizing session");
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
