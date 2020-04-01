package com.nero.java.h2;

import java.sql.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // write your code here
        //数据库连接URL，当前连接的是用户目录下的nero数据库
//        final String JDBC_URL = "jdbc:h2:~/nero";
//        final String JDBC_URL = "jdbc:h2:tcp://localhost:8089/./h2/nero";//连接locahost:8089的H2数据库服务器，连接的数据库是服务器启动的当前文件夹目录下的h2/nero数据库
//        final String JDBC_URL = "jdbc:h2:./h2/nero";//连接程序启动目录下的h2/nero数据库
        final String JDBC_URL = "jdbc:h2:mem:nero";//连接的内存数据库
        //连接数据库时使用的用户名
        final String USER = "nero";
        //连接数据库时使用的密码
        final String PASSWORD = "123456";
        //连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
        final String DRIVER_CLASS = "org.h2.Driver";

        // 加载H2数据库驱动
        Class.forName(DRIVER_CLASS);
        // 根据连接URL，用户名，密码获取数据库连接
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        //如果存在USER_INFO表就先删除USER_INFO表
        stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        //创建USER_INFO表
        stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        //新增
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','tom','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','hery','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','jerry','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','july','女')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','tony','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','snow','男')");
        //删除
        stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='snow'");
        //修改
        stmt.executeUpdate("UPDATE USER_INFO SET name='tony stark' WHERE name='tony'");
        //查询
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        //遍历结果集
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name") + "," + rs.getString("sex"));
        }
        //释放资源
        stmt.close();
        //关闭连接
        conn.close();
    }
}
