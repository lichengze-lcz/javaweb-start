package dao;

import domain.Seller;

public interface SellerDao {
    /**
     * 根据id 查询 商家
     * @param id
     * @return
     */
    public Seller finById(int id);

}
