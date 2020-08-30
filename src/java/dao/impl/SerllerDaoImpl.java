package dao.impl;

import dao.SellerDao;
import domain.RouteImg;
import domain.Seller;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SerllerDaoImpl implements SellerDao {


    private JdbcTemplate template = new JdbcTemplate(jdbcUtils.getDataSource());

    /**
     * 接口实现类
     * 根据 id 查询商家信息   用于展示商品详细信息
     * @param id
     * @return
     */
    @Override
    public Seller finById(int id) {
        String sql = "select * from seller where sid =?";
        //查询一个
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
