package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;

import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String USER = "postgres";
    private static final String PASS = "Black_3737!?";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    public static Connection connect(){
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty(Environment.URL, URL);
        configuration.setProperty(Environment.USER, USER);
        configuration.setProperty(Environment.PASS, PASS);
        configuration.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty(Environment.SHOW_SQL, "true");
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }
}
