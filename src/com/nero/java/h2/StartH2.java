package com.nero.java.h2;

import org.h2.tools.Server;

import java.sql.SQLException;

public class StartH2 {
    public static void main(String[] args) throws InterruptedException, SQLException {
        System.out.println("正在启动h2数据库...");
        //使用org.h2.tools.Server这个类创建一个H2数据库的服务并启动服务，由于没有指定任何参数，那么H2数据库启动时默认占用的端口就是8082
        Server server = Server.createTcpServer(
                new String[]{"-tcp", "-tcpAllowOthers", "-tcpPort",
                        "8089"}).start();
        System.out.println("h2数据库启动成功..." + server.getStatus());

        while (true) {
            Thread.sleep(10000);
        }
    }
}
