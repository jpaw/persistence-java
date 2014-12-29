##Support library for the Bonaparte persistence JPA DSL

The classes defined within this repository are supporting the generated JPA entity classes of the DSL.

The projects have the following contents:
  * persistence-base:	Parent project, initiates the build of the child projects.
  * persistence-bom:	Bill of materials / dependency management. Import this into other projects.
  * persistence-core:	Defines annotations and interfaces used by the generated code.
  * persistence-jpa21:	Defines a user type converter for JPA 2.1 and up for the "BonaPortable" types.
  * persistence-eclipselink:	Session customizers for Eclipselink, which defines user types for the Joda date and time classes, ByteArray, and (optionally) BonaPortable types (if you use JPA 2.0)
  * persistence-hibernate:	Customizer for Hibernate 4.x, which defines a user type for the ByteArray class, and configures Hibernate to use the native Postgres UUID type. In addition, jadira-usertype should be used to support the Joda date and time classes. 

###Building

This project uses maven3 as a build tool. Just run

    (cd persistence-base && mvn install)

