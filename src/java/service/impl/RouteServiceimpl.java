package service.impl;

import dao.FavoriteDao;
import dao.RouteDao;
import dao.RouteImgDao;
import dao.SellerDao;
import dao.impl.FavoriteDaoImpl;
import dao.impl.RoutdaoImpl;
import dao.impl.RouteImgDaoImpl;
import dao.impl.SerllerDaoImpl;
import domain.RouteImg;
import domain.Seller;
import domain.pageBean;
import page.Route;
import service.RouteService;

import java.util.List;

//旅游线路实现类
public class RouteServiceimpl implements RouteService {
    //声明调用 接口RouteDao（多态 接口指向实现类）
    private RouteDao routeDao = new RoutdaoImpl();

    //声明调用 接口RouteImgDao  用于展示商品图片详细信息
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();

    //用于展示商品商家详细信息
    private SellerDao sellerDao = new SerllerDaoImpl();

    //用于查看收藏次数
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public pageBean<Route> pageQuery(int cid, int currentpage, int pageSize,String rname) {
        //封装PageBean
        pageBean<Route> pb = new pageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentpage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数,    通过Dao进行查询
        int totalCount = routeDao.findTotalCount(cid,rname);  //带着搜索参数
        pb.setTotalCount(totalCount);

        //设置当前页显示的数据集合  第几页

        //从第条数据开始查
        int start = (currentpage - 1)*pageSize; //当前页码 - 1  *每页显示的条数       例当前页码是2  -1  *每页条数  ==5   从第五页开始查
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);
        System.out.println("现在开始查的页码是"+start);


        //设置总页数 = 总记录数/每页显示条数； 除不尽加个1
        int totalPage = totalCount % pageSize ==0 ? totalCount / pageSize: (totalCount/pageSize) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }

    //使用3个Dao 进行详细商品信息展示
    @Override
    public Route findOne(String rid) {
        //1.根据id 去route表中查询 route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));   //根据id查询 route表 rid对应的信息  包括 rid sid

    //2.详细商品图片信息
        //2.1.根据route的 id 查询图片集合信息 ，加到route对象里
        List<RouteImg> list = routeImgDao.findByRid(route.getRid()); //要强制类型转换   用于上一方法查询出来的route.getRid()
        //2.2将集合设置到route对象      抽象：把查询到的图片信息  放到route Bean对象里面存图片信息的集合里  （用于route序列化返回客户端展示pic）
        route.setRouteImgList(list);

    //3.详细商品卖家的信息
        //3.根据route表中的sid（商家id） 查询商家对象       （Seller表sid 和route表  是一对多的关系）
        Seller seller = sellerDao.finById(route.getSid());
        //3.1 放到route对象里
        route.setSeller(seller);


     //4.查询收藏次数
        int countByRid = favoriteDao.findCountByRid(route.getRid());//根据商品 rid 查询有几个

        route.setCount(countByRid);
        return route;
    }
}
