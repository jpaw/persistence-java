##Support library for the Bonaparte persistence JPA DSL

The classes defined within this repository are supporting the generated JPA entity classes of the DSL.

The projects have the following contents:
  * persistence-base:           Parent project, initiates the build of the child projects.
  * persistence-bom:            Bill of materials / dependency management. Import this into other projects.
  * persistence-core:           Defines annotations and interfaces used by the generated code. JPA 2.1 AttributeConverter classes.
  * persistence-postgres:       Defines Postgres specific mappings for the "jsonb" data type (work in progress)
  * persistence-eclipselink:    Session customizers for Eclipselink (work in progress for update to Eclipselink 2.7.0)
  * persistence-hibernate:      Customizer for Hibernate 5.2.x, for planned support of jsonb Postgres data type (normally not required)

###Building

This project uses maven3 as a build tool. Just run

    (cd persistence-base && mvn install)

or just
    ./build.sh


###Prerequisites

You need the bonaparte DSL of version 4.5.0 (4.4.23 is fine if you do not need JSON type filed (Array, Element, Json) or serialized storage of BonaPortables).
You need bonaparte-java of version 4.4.23.


###News / Changes

Release 4.5.x switches from Hibernate 4.3 to Hibernate 5.2.
JPA 2.1 now is no longer optional, but required.
As a benefit, standard JPA AttributeConverter classes are now used for all conversion.
A special Dialect is no longer required, for Postgres PostgreSQL94Dialect should used.
Unless native jsonb fields are desired (support is WIP), the project persistence-hibernate is no longer required.

For EclipseLink, there are some remaining issues:
- Eclipselink seems to have dropped support for Postgres UUID (this worked out of the box in some prior release)
- For converters to Strings, the @Lob annotation seems to be discarded
- There is some exception at startup about a missing JNDI context (we should not need any), even if NoServerPlatform is specified in the persistence.xml

