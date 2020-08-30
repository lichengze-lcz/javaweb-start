package dao.impl;

import dao.Userdao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import page.User;

public class Userdaoinpl implements Userdao {

//  获取连接池对象
    private JdbcTemplate template = new JdbcTemplate(jdbcUtils.getDataSource());

    /**
     *查询用户名 判断用户名书否存在,防止用户名重复
     * @param username
     * @return
     */
//    @Override
//    public User findByUsername(String username) {
//
//
//        try {
//            //定义sql语句
//            String sql = "select * from userinfo where username=?";
//            //执行sql   用户名只能查出一个对象 so queryForObject
////                                   封装类型              泛型                 参数
//            System.out.println(username+"111111111111");
//            User user = new User();
//
//            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
//            System.out.println(user+"11111111111");
//            return  user;
//        }catch (Exception e){
//            System.out.println("没查到相同的用户名");
//            System.out.println(username);
//        }
//        return null;
//
//    }
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from userinfo where username = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
            System.out.println("没有查询到相同的用户名，满足注册条件");
        }
        return user;
    }


    /**
     * 保存用户注册信息
     * @param user
     */

    @Override
    public void save(User user) {
        String sql = "Insert into userinfo(username,password,name,email,phone,gender,statue,code)"+"values(?,?,?,?,?,?,?,?)";

        template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone(),user.getGender(),user.getStatue(),user.getCode());

    }

    /**
     * 根据激活码查询用户对象  实现
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from userinfo where code =?";
            //明确知道查到的激活码对象，要么只有一个，要么没有
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
            System.out.println("查一下user："+user);
            return user;
        } catch (DataAccessException e) {

        }
        return null;
    }

    /**
     * 修改制定用户激活状态
     * @param user
     */
    @Override
    public void updateStadu(User user) {
        String sql = "update userinfo set statue ='Y' where uid =?";
        template.update(sql,user.getUid());

    }

    /**
     * 用户登录，根据用户名，和密码查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from userinfo where username = ? and password =? ";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {
            System.out.println("用户名和密码不匹配，登陆失败");
        }
        return user;
    }


}




