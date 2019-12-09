package spring.hibernate;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConfig {
    private Connection connection;

    public static void main(String[] args) throws URISyntaxException, SQLException {
        PostgresqlConfig PostgresqlConfig = new PostgresqlConfig();
        PostgresqlConfig.getConnection();
    }

    private Connection getConnection() throws URISyntaxException, SQLException {
        if(connection != null){
            return connection;
        }

        URI dbUri = new URI("postgresql://jpleadtbxxfbyd:a0264654d3a3d2b2a211244b18f9cfc548d21646a087dea16f403eb658ce21ed@ec2-46-137-120-243.eu-west-1.compute.amazonaws.com:5432/d4mb0i32pjb4pi");

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

}