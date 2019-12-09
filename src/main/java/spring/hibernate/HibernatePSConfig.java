package spring.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.net.URI;
import java.util.Properties;

public class HibernatePSConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");

                URI dbUri = new URI("postgres://mutcpxoggvfnyc:ebcb7793c6de62c22b058595b05b8e01ff4f83de6db2f2677481946d22d2ddfd@ec2-54-247-96-169.eu-west-1.compute.amazonaws.com:5432/d1tjug5h1o8q5o");

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                System.out.println(dbUrl);
                settings.put(Environment.URL, dbUrl);
                settings.put(Environment.USER, username);
                settings.put(Environment.PASS, password);

                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
              //  settings.put("hibernate.connection.requireSSL", true);
                //TODO
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Employees.class);
                configuration.addAnnotatedClass(Phones.class);
                configuration.addAnnotatedClass(Cars.class);
                configuration.addAnnotatedClass(Printer.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}