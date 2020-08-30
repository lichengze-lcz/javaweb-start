package dao.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.druid.support.json.JSONUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

//工具类
// 加载配置文件
//提供获取连接的方法
public class jedisutil {



    private static JedisPool jedisPool;

    static {
        //读取配置文件


            InputStream is = jedisutil.class.getClassLoader().getResourceAsStream("jedisprodurid");
            Properties pro = new Properties();
            //关联文件
        try {
            pro.load(is);

        } catch (Exception e) {

            e.printStackTrace();
        }
//        获取数据 设置  读取设置一个
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));

        //初始化JedisPool
        jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));

    }


    //	获取连接的方法
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }


}
