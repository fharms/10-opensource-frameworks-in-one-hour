import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.function.Consumer;

import static dk.presentation.generated.tables.Customer.CUSTOMER;

public abstract class JooqApplication {
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/tenframeworks";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "verysecret";

    protected void with(Consumer<DSLContext> consumer) {
        loadDriver();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME,PASSWORD)) {

            DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);
            consumer.accept(create);

        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    private void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new  RuntimeException("Unable to find PostgreSQL database driver", e);

        }
    }
}
