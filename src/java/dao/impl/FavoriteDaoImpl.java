package dao.impl;

import dao.FavoriteDao;
import domain.Favorite;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(jdbcUtils.getDataSource());

    /**
     * 根据rid uid 查询 用户是否收藏
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite findByRidAndUid(int rid,int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from favorite where rid =? and uid =?";

            favorite = template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        } catch (DataAccessException e) {
            System.out.println("findByRidAndUid---"+"没查到");
        }
        return favorite;
    }

    /**
     * 根据线路rid 查收藏次数（每个线路都有固定的rid  ，查看该rid有几个用户收藏）
     * @param rid
     * @return
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from favorite where rid =?";

        return template.queryForObject(sql,Integer.class,rid);
    }

    /**
     * 添加用户收藏信息 Dao接口实现类
     * @param rid
     * @param uid
     */
    @Override
    public void add(int rid, int uid) {
        String sql = "insert into favorite values(?,?,?)";

        template.update(sql,rid,new Date(),uid);
    }
}
