package home.match_betting_server;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

public class PostgreSQLTestContainer {
    private static final DockerImageName POSTGRESQL_IMAGE_NAME = DockerImageName.parse("postgres");

    private static final PostgreSQLContainer<?> container =
            new PostgreSQLContainer<>(POSTGRESQL_IMAGE_NAME)
                    .withDatabaseName("testdb")
                    .withUsername("user")
                    .withPassword("password");

    static {
        container.start();
    }

    public static PostgreSQLContainer<?> getInstance() {
        return container;
    }
}
