package webServlet;

import domain.pageBean;
import page.Route;
import page.User;
import service.FavoriteService;
import service.RouteService;
import service.impl.FavoriteServiceImpl;
import service.impl.RouteServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceimpl();

    private FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 分页和商品展示
     */
     public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          //1.接收参数
         String currentPagestr = request.getParameter("currentPage");     //当前页码
         String currentSizesstr = request.getParameter("currentSize");  //每页显示条数
         String cidstr = request.getParameter("cid"); //类别id
         //接收搜索参数值
         String rname = request.getParameter("rname");


         System.out.println("***********获取参数---cid"+cidstr+"当前页码---"+currentPagestr+"每页显示条数---"+currentSizesstr+"***********rname"+rname);
         int cid =0;//默认值 防止空指针
         //2.处理参数 cid 不为空
         if(cidstr != null && cidstr.length() >0 && !"null".equals(cidstr) && !"undefined".equals(cidstr)){
             cid = Integer.parseInt(cidstr);
         }

         int currentPage =0;  //当前页码，如不传，默认第一页
         if(currentPagestr != null && currentPagestr.length() >0){
             currentPage = Integer.parseInt(currentPagestr);
         }else{
             currentPage = 1;
         }

         int currentSize =0;  //当前显示页数，不传默认显示五条记录
         if(currentSizesstr != null && currentSizesstr.length() >0){
             currentSize = Integer.parseInt(currentSizesstr);
         }else{
             currentSize = 5;
         }


         //3. 调用service查询PageBean对象
         pageBean<Route> pb = routeService.pageQuery(cid, currentPage, currentSize,rname);
         System.out.println("pageBean，里面回显到客户端的数据"+pb);
         //4. 将pageBean对象序列化为json，返回
         writeValue(pb,response);
    }


    /**
     * 根据一个 id 查询一个旅游线路详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数id
        String rid = request.getParameter("rid");

        //2.调用service查询route对象
        Route route = routeService.findOne(rid);

        //3.转为json写回客户端
        //父类封装好的方法
        writeValue(route,response);

     }


    /**
     * 判断当前用户是否过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1.获取该新路id
        String rid = request.getParameter("rid");
       //2.获取当前登录的用户 user   user在LoginServlet类中 存储的Session域  里面存用户登录的信息
        //根据user 获取User对象
        User user = (User)request.getSession().getAttribute("user");      //存储时 键为user

        int uid; //用户的id，  标记是否登录
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else{
            //用户已登录
            uid = user.getUid();
        }
       //调用 FavoriteService 查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //将flag 写回
        writeValue(flag,response);
    }

    /**
     * 前端点击收藏按钮，  添加用户收藏信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //1. 获取线路rid
        String rid = request.getParameter("rid");

        //2.在Session域 中获取当前用户信息    是否登录
        //根据user 获取User对象
        User user = (User)request.getSession().getAttribute("user");

        int uid; //用户的id，  标记是否登录
        if(user == null){
            //用户尚未登录 直接返回
            return;
        }else{
            //用户已登录
            uid = user.getUid();
        }

        //调用Service添加用户收藏信息
        favoriteService.add(rid,uid);

    }
}
