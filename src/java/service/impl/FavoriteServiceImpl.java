package service.impl;

import dao.FavoriteDao;
import dao.impl.FavoriteDaoImpl;
import domain.Favorite;
import service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);


        return favorite != null;    //不等于null 返回true  ，对象没值为false；

        //前端页面接收到 false  说明用户没有收藏
    }

    /**
     * 添加用户收藏信息
     * @param rid
     * @param uid
     */
    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
