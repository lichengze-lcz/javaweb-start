package dao.impl;

import dao.CategoryDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import page.category;

import java.util.List;

public class CategoryDaoimpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(jdbcUtils.getDataSource());


    /**
     * 查询所有
     * @return
     */
    @Override
    //映射 查询category对象
    public List<category> finall() {
        String sql = "select * from category";
        return template.query(sql,new BeanPropertyRowMapper<category>(category.class));
    }
}
