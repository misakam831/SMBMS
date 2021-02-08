package com.hby.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 公共类
 *
 * @author 胡博宇
 */
public class BaseDao {
    public static String driver;
    public static String url;
    public static String username;
    public static String password;

    static {
        InputStream in = BaseDao.class.getResourceAsStream("db.properties");
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = p.getProperty("driver");
        url = p.getProperty("url");
        username = p.getProperty("username");
        password = p.getProperty("password");

    }

    /**
     * 连接数据库
     */
    public static Connection getconnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 查询公共类
     */
    public static ResultSet excute(Connection connection, String sql, PreparedStatement statement, ResultSet resultSet, Object... params) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        return preparedStatement.executeQuery();
    }

    public static int update(Connection connection, String sql, PreparedStatement statement, Object... params) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i <params.length ; i++) {
            preparedStatement.setObject(i+1,params[i]);
        }
        return preparedStatement.executeUpdate();
    }
    /**
     * 关闭流
     */
    public static void resclose(Connection connection,PreparedStatement statement,ResultSet resultSet) throws SQLException {
        if (connection!=null){
            connection.close();
        }
        if (statement!=null){
            statement.close();
        }
        if (resultSet!=null){
            resultSet.close();
        }

    }

}
