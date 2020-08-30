package dao;

import domain.Favorite;

public interface FavoriteDao {
    /**
     * 根据rid 和uid查询收藏信息
     * @param rid
     * @param uid
     * @return
     */
    public Favorite findByRidAndUid(int rid, int uid);

    /**
     * 根据rid 查询收藏次数
     * @param rid
     * @return
     */
    public int findCountByRid(int rid);

    /**
     * 添加用户收藏信息 Dao接口
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);
}
