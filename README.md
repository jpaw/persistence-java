## Support library for the Bonaparte persistence JPA DSL

The classes defined within this repository are supporting the generated JPA entity classes of the DSL.

The projects have the following contents:
  * persistence-base:           Parent project, initiates the build of the child projects.
  * persistence-bom:            Bill of materials / dependency management. Import this into other projects.
  * persistence-core:           Defines annotations and interfaces used by the generated code. JPA 2.1 AttributeConverter classes.
  * persistence-postgres:       Defines Postgres specific mappings for the "jsonb" data type (work in progress)
  * persistence-eclipselink:    Session customizers for Eclipselink (work in progress for update to Eclipselink 2.7.0)
  * persistence-hibernate:      Customizer for Hibernate 5.2.x, for planned support of jsonb Postgres data type (normally not required)


## Building

This project uses maven3 as a build tool. Just run

```sh
    mvn install
```

or just
```sh
    ./build.sh
```


## Prerequisites

You need the bonaparte DSL and bonaparte-java of version 4.4.23 or newer.
The upcoming 4.5.x releases will provide additional support for JPA 2.1 (Index creation and increased use of AttributeConverters).


## News / Changes

Release 4.5.x switches from Hibernate 4.3 to Hibernate 5.2.
JPA 2.1 now is no longer optional, but required.
As a benefit, standard JPA AttributeConverter classes are now used for all conversion. You also do not need the jadira usertype library as a dependency any more if you use Hibernate.
A special Dialect is no longer required, for Postgres PostgreSQL94Dialect should used.
Unless native jsonb fields are desired (support is WIP), the project persistence-hibernate is no longer required.

For EclipseLink, there are some remaining issues:
* Eclipselink seems to have dropped support for Postgres UUID (this worked out of the box in some prior release)
* For converters to Strings, the @Lob annotation seems to be discarded
* There is some exception at startup about a missing JNDI context (we should not need any), even if NoServerPlatform is specified in the persistence.xml

The converter classes can be listed in persistence.xml as follows:
```xml
        <class>de.jpaw.bonaparte.jpa.converters.ConverterByteArray</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterCompactBonaPortable</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterInstant</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterLocalDate</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterLocalTime</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterLocalDateTime</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterCompactJsonArray</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterCompactJsonElement</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterCompactJsonObject</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterStringJsonArray</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterStringJsonElement</class>
        <class>de.jpaw.bonaparte.jpa.converters.ConverterStringJsonObject</class>
        <class>de.jpaw.bonaparte.jpa.postgres.ConverterNativeJsonArray</class>
        <class>de.jpaw.bonaparte.jpa.postgres.ConverterNativeJsonElement</class>
        <class>de.jpaw.bonaparte.jpa.postgres.ConverterNativeJsonObject</class>
```
