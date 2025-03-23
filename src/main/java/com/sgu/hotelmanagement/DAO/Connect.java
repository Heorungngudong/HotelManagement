package com.sgu.hotelmanagement.DAO;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Getter
@Slf4j

public class Connect {
    private static volatile Connect instance ;
    private static final String DATABASE = "test";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
    protected Connection connection = null;

    private Connect() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL + DATABASE, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            log.error("Failed to connect to database: ", e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Failed to close connection: ", e);
        }
    }

    public static Connect getInstance() {
        if (instance == null) {
            synchronized (Connect.class) {
                if (instance == null) {
                    instance = new Connect();
                }
            }
        }
        return instance;
    }

}
