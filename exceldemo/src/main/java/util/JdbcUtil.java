/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JdbcUtil
 * Author:   Dell
 * Date:     2018/10/25 15:02
 * Description: 获取数据库连接工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package util;

import java.sql.*;

/**
 * 〈Coding never to stop〉<br>
 * 〈获取数据库连接工具类〉
 *
 * @author zombie
 * @create 2018/10/25
 * @since 1.0.0
 */
public class JdbcUtil {

    // 线程数据库连接局部变量
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();


    // 获得连接对象
    public static synchronized Connection getConn() {
        // 数据库连接对象
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                // 数据库驱动 org.postgresql.Driver \oracle.jdbc.driver.OracleDriver
            /*     String driver = "org.postgresql.Driver";
                String password = "123456";// 数据库连接用户名
                String username = "postgres";//  数据库连接地址 interface_test
                String url = "jdbc:postgresql://192.168.206.130:5432/exceldb";*/

                String driver = "oracle.jdbc.driver.OracleDriver";
                String password = "interface_test";
                String username = "interface_test";
                Class.forName(driver);
                String url = "jdbc:oracle:thin:@//10.10.56.39:1521/orcl";
                 //   String url = "jdbc:oracle:thin:@//10.8.32.188:1521/ORCL";

                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                threadLocal.set(conn);
            }
        }
        return conn;
    }


    //关闭连接
    public static void close() {
        try {
            getConn().close();
            // 防止调用close()后，无法获取数据库连接
            threadLocal.set(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
