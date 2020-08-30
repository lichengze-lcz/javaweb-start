package service;

import page.User;

//用户servlet的接口
public interface UserService {
    boolean regist(User user);

    boolean active(String code);

    User Userlogin(User user);
}
