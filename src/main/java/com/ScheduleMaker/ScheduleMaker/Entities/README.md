# Setup notes

## 1. Enable auditing (required for created_at/updated_at to populate)

Add this to your main `@SpringBootApplication` class:

```java
@SpringBootApplication
@EnableJpaAuditing
public class StudyPlannerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyPlannerApplication.class, args);
    }
}
```

## 2. pom.xml dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

If you're using Spring Initializr, just check the "Lombok" box and it adds
this for you. If your IDE doesn't recognize the generated getters/setters,
install the Lombok plugin (IntelliJ: Settings → Plugins → search "Lombok").

### Builder gotcha (already handled in these files)

Lombok's `@Builder` ignores field initializers (`= new ArrayList<>()`,
`= ScheduleStatus.ACTIVE`, `= 0.0`, `= 0`) unless you also add
`@Builder.Default` on that field — otherwise `Schedule.builder().build()`
would silently give you `null` collections and `null` enums instead of the
defaults. Every field with a default value in these entities already has
`@Builder.Default` applied, so `SomeEntity.builder()...build()` behaves the
same as `new SomeEntity()` for any field you don't explicitly set.

## 3. application.properties (MySQL)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/study_planner?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
```

`ddl-auto=update` is fine for development — Hibernate will create the `users`,
`schedules`, `subjects`, `topics`, `daily_plans`, and `daily_tasks` tables for
you from these entities on startup. Switch to a proper migration tool
(Flyway/Liquibase) before production.

## 4. Files in this folder

All entities use Lombok (`@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder`)
for boilerplate, and every field has an inline comment explaining its purpose.

- `Auditable.java` — base class, gives every entity created_at/updated_at.
  Uses only `@Getter` (no setter, no builder) since these two fields must
  only ever be written by the JPA auditing listener.
- `User.java`, `Schedule.java`, `ScheduleStatus.java`
- `Subject.java`
- `Topic.java`, `TopicStatus.java` — status is a `@Transient` derived method,
  not a stored column, so it can never drift from `hoursCompleted`
- `DailyPlan.java`
- `DailyTask.java`, `TaskStatus.java` — FAILED lives here, not on Topic

Package everything expects: `com.studyplanner.entity` — rename if your
project uses a different base package.
