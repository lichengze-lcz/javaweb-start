package dao;

import domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    /**
     * 根据线路 id 查询图片
     * @param rid
     * @return
     */
    //返回值放routeImg对象
    public List<RouteImg> findByRid(int rid);
}
