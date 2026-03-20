# snowflake-client

Minimal multi-datasource Spring Boot 3 example using:
- Spring Data JDBC (Snowflake) - no JPA for Snowflake
- Spring Data JPA (H2) - primary datasource for a small CRUD API

## Project layout
- `com.example.snowflakeclient.customer`
  - `CustomerRepository` (Spring Data JDBC) runs Snowflake queries like:
    - `SELECT PI()`
    - `SELECT CUSTOMER FROM DATA_CLOUD`
- `com.example.snowflakeclient.h2`
  - `TodoItem` (JPA entity), `TodoItemRepository` (JpaRepository), `TodoItemService`, `TodoItemController`

## Configuration
### Snowflake (private key auth)
Edit `src/main/resources/application.yml`:

- `snowflake.url`
- `snowflake.user`
- `snowflake.role`
- `snowflake.warehouse`
- `snowflake.private-key-path` (path to your `.p8` private key file)
- `snowflake.private-key-passphrase`

### H2 (JPA)
In `application.yml`, configure `spring.datasource.*` and `spring.jpa.*` if you want to override defaults.

## Run tests

### H2 integration test (always runs)
There is an integration test for the H2-backed controller:
- `H2TodoItemControllerIntegrationTest`

### Snowflake integration tests (auto-skipped if not configured)
`SnowflakeClientApplicationTests` will skip if `application.yml` still contains placeholders
(or if the configured private key file does not exist).

Run:

```bash
mvn test
```

## REST endpoint (H2)
Once the app is running, you can call:
- `GET /api/todos`
- `POST /api/todos` with JSON like:
  - `{"title":"first"}`
