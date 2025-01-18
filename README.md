# Spring JPA Example
## Handling multiple database

This project has been created to exemplify handling of multiple database connections using Spring version 6 and JPA 3.

## Tech stack
- Open JDK 17
- Maven version 3.6.1
- Spring 6
- SQLite Database
- JPA 3

## Setup
1. Two sqlite databases/file has been created and are placed under main/src/resources
-- `user.db` --> It has user table
-- `country.db` --> It has country table
-- Entity beans are placed under `com.ms.example.xxxdb` package

## Configurations
1. `AppConfig.java` has `main()` method which initialises Springs` application context.
1. Refer to `UserDbConfig` and `CountryDbConfig` for required database connections and `EntityManager` beans for each connection.
1. Both beans has been configured to use individual persistent unit name by calling `setPersistenceUnitName()` method in `LocalContainerEntityManagerFactoryBean` bean configuration method.
1. `DbService` class has two EntityManager fields, one for each connection and are annotated with `@PersistenceContext` with explicit value set for `unitName`.

## Build and run
1. Run test `mvn clean test`
