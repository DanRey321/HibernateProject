package com.project1.util;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@Deprecated
public class ConnectionUtil {

    public static Connection getConnection() throws SQLException{

        String url = "jdbc:postgresql://samplecar.csuhfolohico.us-west-1.rds.amazonaws.com:5432/postgres";
        String username = "danrey321";
        String password = "password";

        Connection connection = null;
        //Class.forName("org.postgresql.Driver");
        DriverManager.registerDriver(new Driver());
        connection = DriverManager.getConnection(url,username, password);


        return connection;
    }

}
