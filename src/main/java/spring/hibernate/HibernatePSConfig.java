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

                URI dbUri = new URI("postgresql://jpleadtbxxfbyd:a0264654d3a3d2b2a211244b18f9cfc548d21646a087dea16f403eb658ce21ed@ec2-46-137-120-243.eu-west-1.compute.amazonaws.com:5432/d4mb0i32pjb4pi");

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