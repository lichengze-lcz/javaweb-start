package webServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import page.category;
import service.CategoryService;
import service.impl.CategoryServlceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//查询category表所有信息  用于分类条  （redis）
@WebServlet("/category/*")   //匹配请求
public class CategoryServlet extends BaseServlet {
    //public 其他类可以访问到

     //成员变量
     private CategoryService service = new CategoryServlceImpl();

    /**
     * 查询所有分类的的Servlet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void finall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.调用service 查询所有category
        List<category> cs = service.finall();
        //2.序列化json 返回
//        ObjectMapper mapper = new ObjectMapper();
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(),cs);

        //直接调用父类方法
        writeValue(cs,response);
    }


}
