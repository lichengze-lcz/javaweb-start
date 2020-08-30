package dao;

import page.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 根据cid 查询总记录数
     */
     public int findTotalCount(int cid,String rname);



    /**
     * 根据cid start（从哪开始查 ）pageSize查询当前数据集合
     */
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);


    /**
     * 旅游商品详细信息   根据id查询 展示
     * @param rid
     * @return
     */
    public Route findOne(int rid);

}
