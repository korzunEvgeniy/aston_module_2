package org.example.dao;

import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoIntegrationTest {

    private static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:18")
            .withDatabaseName("user_db")
            .withUsername("postgres")
            .withPassword("postgres");

    private SessionFactory sessionFactory;
    private UserDao userDao;

    @BeforeAll
    public void setup() {
        postgresContainer.start();

        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", postgresContainer.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgresContainer.getUsername());
        configuration.setProperty("hibernate.connection.password", postgresContainer.getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.addClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
        userDao = new UserDaoImpl();
    }

    @AfterAll
    public void teardown() {
        if (sessionFactory != null) sessionFactory.close();
        if (postgresContainer != null) postgresContainer.stop();
    }

    @Test
    public void testCreateReadUpdateDeleteUser() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setAge(30);

        userDao.save(user);

        User retrieved = userDao.findById(user.getId()).orElseThrow();
        Assertions.assertNotNull(retrieved);
        Assertions.assertEquals("Test User", retrieved.getName());

        retrieved.setAge(35);
        userDao.update(retrieved);

        User updated = userDao.findById(retrieved.getId()).orElseThrow();
        Assertions.assertEquals(35, updated.getAge());

        userDao.delete(updated);

        User deleted = userDao.findById(updated.getId()).orElseThrow();
        Assertions.assertNull(deleted);
    }
}
