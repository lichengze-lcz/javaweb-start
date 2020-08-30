package webServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Resultinfo;
import org.apache.commons.beanutils.BeanUtils;
import page.User;
import service.UserService;
import service.impl.UserServletImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


//校验注册是否成功
@WebServlet("/RegistUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");

//  校验验证码
        String check = request.getParameter("vcode");

        //从checkcode类 session获取验证码
        HttpSession session = request.getSession();
        String checkcodeServlet = (String) session.getAttribute("checkCodeSession02");

        //避免复用
        session.removeAttribute("checkCodeSession02");

        //checkcode比较
        if(checkcodeServlet == null || !checkcodeServlet.equalsIgnoreCase(check)){
            //验证码错误，提示信息；
            Resultinfo info = new Resultinfo();
            info.setFlag(false);
            info.setErrormag("验证码错误，请输入正确的验证码!");
            response.setContentType("application/json;charset=utf-8");
            //序列化json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);

            return;
        }




//      1.获取数据
        Map<String,String[]>map = request.getParameterMap();
//      2.Bean封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);

//      3.调用Servlet完成注册
        UserService servive = new UserServletImpl();
        boolean flag = servive.regist(user);  //成功与否响应结果

//      4.响应结果
        Resultinfo info = new Resultinfo();  //把结果信息封装成了对象返回到 html
        if(flag){
//            注册成功
              info.setFlag(true);

        }else{
//            注册失败
              info.setFlag(false);
              info.setErrormag("注册失败! 用户名已存在");
        }

System.out.println("查看一下回调结果信息---------------------------------"+info);
//       将info对象序列化为json键值对，返回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
//       设置content-type mime类型
        response.setContentType("application/json;charset=utf-8");

//       将json数据写回客户端 字符流
        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);



    }
}
