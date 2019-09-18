package com.leh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Auther: leh
 * @Date: 2019/9/18 11:32
 * @Description:原生的JDBC操作
 */
public class OriginJdbcDemo {


    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&rewriteBatchedStatements=true";
    private static final String name = "root";
    private static final String pwd = "123456";

    public static void main(String[] args) {


        try {

            //1 加载驱动类
            Class.forName(driver);

            //2 建立连接
            Connection connection = DriverManager.getConnection(url, name, pwd);

            //3 创建语句集

            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM dh_user");


            //4 执行

            ResultSet rs = pstm.executeQuery();

            //5 获取结果集

            while (rs.next()){

                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
            }

            rs.close();
            pstm.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
