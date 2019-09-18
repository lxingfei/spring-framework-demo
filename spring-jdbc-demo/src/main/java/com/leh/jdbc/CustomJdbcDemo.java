package com.leh.jdbc;

import com.alibaba.fastjson.JSONObject;
import com.leh.model.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: leh
 * @Date: 2019/9/18 11:32
 * @Description:封装后的JDBC操作
 */
public class CustomJdbcDemo {


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

            /*
                1,2步被封装成了 DataSource 放入到连接池，目的是提高响应速度，提高性能
             */

            //3 创建语句集
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM dh_user");


            //4 执行
            ResultSet rs = pstm.executeQuery();


             /*
                3,4步一般不动
             */

            //5 获取结果集
            int len = rs.getMetaData().getColumnCount();
            List<Object> result = new ArrayList<>();
            while (rs.next()) {

                Class clazz = User.class;
                Object user = clazz.newInstance();
                for (int i = 1; i <= len; i++) {
                    //该ORM过程的前提：实体对象结构和数据库表结果完全一致
                    String colName = rs.getMetaData().getColumnName(i);
                    Field field = clazz.getDeclaredField(colName);
                    field.setAccessible(true);
                    Object type = field.getType();
                    if (type == int.class) {
                        field.set(user, rs.getInt(colName));
                    } else if (type == String.class) {
                        field.set(user, rs.getString(colName));
                    } else {

                    }

                }
                System.out.println(JSONObject.toJSONString(user));
                result.add(user);
            }

            System.out.println(JSONObject.toJSONString(result));

            /*
                第5步 将被封装成一个ORM 过程
                Object Relational Mapping 对象关系映射
                将ResultSet 自动变成一个我们自己定义的实体类
             */

            rs.close();
            pstm.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
