package domain;


import java.util.UUID;

/**
 *
 * @作者: chencong
 * @项目: mail--cc.ccoder.mail.utils
 * @时间: 2017年6月7日下午7:05:14
 * @TODO： 生成随机字符串的工具类 uuid
 */
public class UuidUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println("格式前的UUID ： " + UUID.randomUUID().toString());
        System.out.println("格式化后的UUID ：" + getUUID());
    }
}
