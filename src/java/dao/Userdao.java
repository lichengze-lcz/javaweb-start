package dao;

import page.User;

public interface Userdao {

//  1.根据用户名查询用户信息
    public User findByUsername(String username);


//  2.注册完,用户对象保存的功能
    public void save(User user);

    /**
     * 查询code方法
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新登录状态码
     * @param user
     */
    void updateStadu(User user);

    User findByUsernameAndPassword(String username, String password);
}
