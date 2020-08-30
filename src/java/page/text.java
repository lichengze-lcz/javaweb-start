//package page;
//
//import dao.impl.jedisutil;
//import redis.clients.jedis.Jedis;
//
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Properties;
//
//public class text {
//    public static void main( String args[]) throws SQLException {
//
////        testConnection1();
//        textt();
//    }
//    public static void testConnection1() throws SQLException {
//        // 获取Driver实现类对象
//        Driver driver = new com.mysql.jdbc.Driver();
//        // url:http://localhost:8080/gmall/keyboard.jpg
//        // jdbc:mysql:协议
//        // localhost:ip地址
//        // 3306：默认mysql的端口号
//        // test:test数据库
//        String url = "jdbc:mysql://localhost:3306/infoo?serverTimezone=UTC";
//        // 将用户名和密码封装在Properties中
//        Properties info = new Properties();
//        info.setProperty("user", "root");
//        info.setProperty("password", "123456");
//        Connection conn = driver.connect(url, info);
//        System.out.println(conn);
//    }
//     public static void textt(){
//         Jedis jedis = jedisutil.getJedis();
//         jedis.set("hello11","word");
//         System.out.println("111");
//         jedis.close();
//
//     }
//
//
//    public  class ListDemo {
//
//        ArrayList list = new ArrayList();
//            list.add("Jerry");
//            list.add("Annie");
//            list.add("Yuki");
//            list.add("Tom");
//        Iterator it = list.iterator();
//            while(it.hasNext())
//
//        {
//            Object obj = it.next();
//            if ("Annie".equals(obj)) {
//                it.remove();
//            }
//        }
//            System.out.println(list.get(1));
//    }
//}
