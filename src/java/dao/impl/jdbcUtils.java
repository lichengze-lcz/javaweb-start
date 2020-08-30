package dao.impl;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbcUtils {
    public static DataSource getDataSource;
    //定义静态成员变量DataSource
    private  static  DataSource ds;

    //在静态成员变量中对DataSource赋值  （只有静态变量才才能进入访问静态代码块)  ***获取数据源
    static {
        //加载配置文件
        Properties pro = new Properties();
        try {          //在本类字节码文件 中加载  配置文件     返回一字节输入流对象 读取文件
            pro.load(jdbcUtils.class.getClassLoader().getResourceAsStream("prodruid"));
            System.out.println("222222222");
            //在文件中 获取Datesource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接对象方法
    public static Connection getConnection() throws SQLException {
        //从连接池里取一个连接对象
        return ds.getConnection();
        //return DriverManager.getConnection(url, user, password);
    }

    //获取连接池方法   (返回连接池对象)
    public static DataSource getDataSource(){
        return  ds;

    }
}