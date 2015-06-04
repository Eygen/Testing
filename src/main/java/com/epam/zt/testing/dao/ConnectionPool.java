package com.epam.zt.testing.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = new ConnectionPool();
    private DaoProperties properties = new DaoProperties();
    private String url = properties.getProperty("url");
    private String driver = properties.getProperty("driver");
    private String username = properties.getProperty("username");
    private String password = properties.getProperty("password");
    private int size = Integer.parseInt(properties.getProperty("maxActive"));
    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(size);

    private ConnectionPool() {
        init();
    }

    private void init() {
        try {
            Class.forName(driver);
            for (int i = 0; i < size; i++) {
                pool.offer(createConnection());
            }
            logger.info("Connection pool created");
        } catch (Exception e) {
            logger.error("Can not create connection pool");
            throw new DaoException("Can not create connection pool ", e);
        }
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("Unable create connection to database ", e);
        }
        return connection;
    }

    public Connection getConnection() {
        Connection connection;
        do {
            connection = pool.poll();
            if (connection == null) {
                pool.offer(createConnection());
            }
            if (!validConnection(connection)) {
                connection = null;
            }
        } while (connection == null);
        return connection;

    }

    private boolean validConnection(Connection connection) {
        try {
            boolean status = connection.getAutoCommit();
            connection.setAutoCommit(!status);
            connection.setAutoCommit(status);
        } catch (SQLException e) {
            try {
                connection.close();
                logger.debug("Dead connection detected");
                return false;
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        }
        return true;
    }

    public void releaseConnection(Connection connection) {
        if (getCurrentSize() >= size) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        } else if (validConnection(connection)) {
            pool.offer(connection);
        }
    }

    private int getCurrentSize() {
        return pool.size();
    }

    public static ConnectionPool getInstance() {
        return instance;
    }
}
