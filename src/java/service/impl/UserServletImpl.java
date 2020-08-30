package service.impl;

import dao.Userdao;
import dao.impl.Userdaoinpl;
import domain.MailUtils;
import domain.UuidUtils;
import page.User;
import service.UserService;
public class UserServletImpl implements UserService {

    /**
     * 用户注册 后台Servlet
     * @param user
     * @return
     */

    private Userdao userDao = new Userdaoinpl();
    @Override
    public boolean regist(User user) {
//       确保用名不重复,根据用户名查询用户对象
//       返回个User对象     用户名不存在username 为null
         User U =userDao.findByUsername(user.getUsername());


//       2.保存用户信息
         if(U != null){
          //该用户名存在，注册失败
             return false;
         }
//       保存用户信息(激活..)
//        2.1设置激活码   唯一字符串  使用UuidUtils工具类
          user.setCode(UuidUtils.getUUID());
//        2.2设置激活状态
          user.setStatue("N");
          userDao.save(user);
//        3.邮件激活发送，邮件正文
          String MailContent = "<a href = 'http://localhost:9999/mvnmiddle/activeUserServlet?code="+user.getCode()+"'> 点击激活【黑马旅游网】</a>";
//        发送
         System.out.println("查看当前用户邮箱------------------------"+user.getEmail());
         MailUtils.sendMail(user.getEmail(),MailContent,"激活邮件");
         return true;
    }

    /**
     * 激活用户
     * @param code
     * @return
     */

    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
         User user = userDao.findByCode(code);
        if (user != null){
            //用户存在
            userDao.updateStadu(user);
            return true;
        }else{
            return false;
        }

        //2.调用dao 修改激活状态的方法
    }

    /**
     * 用户登录Servlet，查询数据库u p
     * @param user
     * @return
     */
    @Override
    public User Userlogin(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}