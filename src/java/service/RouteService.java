package service;

import domain.pageBean;
import page.Route;

public interface RouteService {
  /**
   * 根据类别cid 进行分页查询
   * @param cid
   * @param currentpage
   * @param pageSize
   * @param rname
   * @return
   */
  public pageBean<Route> pageQuery(int cid,int currentpage,int pageSize,String rname);

  /**
   * 根据id查询
   * @param rid
   * @return
   */
  Route findOne(String rid);
}
