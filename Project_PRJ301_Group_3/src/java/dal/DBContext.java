/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author : GiangLT
 * @date : 7/2/2026
 * @description : Chịu trách nhiệm thiết lập kết nối đến cơ sở dữ liệu, với việc
 * các thông tin cấu hình từ ConnectDB.properties. Kết nối được khởi tạo khi tạo
 * mới đối tượng DBContext và có thể truy xuất thông qua phương thức
 * getConnection().
 */
public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            Properties properties = new Properties();       
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("../ConnectDB.properties");
            try {
                properties.load(inputStream);
            } catch (IOException ex) {
                Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            String user = properties.getProperty("userID");
            String pass = properties.getProperty("password");
            String url = properties.getProperty("url");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
