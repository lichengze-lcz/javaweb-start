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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//  校验验证码
        String check = request.getParameter("vcode");

        //从checkcode类 session获取验证码
        HttpSession session = request.getSession();
        String checkcodeServlet = (String) session.getAttribute("checkCodeSession02");
        session.removeAttribute("checkCodeSession02");
        //checkcode比较
        if(checkcodeServlet == null || !checkcodeServlet.equalsIgnoreCase(check)){
            Resultinfo info = new Resultinfo();
            info.setFlag(false);
            info.setErrormag("验证码错误，请输入正确的验证码!");
            response.setContentType("application/json;charset=utf-8");
            //序列化json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);

            return; //不往下执行
        }





        //1.获取用户名和密码
        Map<String, String[]> map = request.getParameterMap();

        //2.封装成User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用Servlet查询用户名，密码
        UserService service = new UserServletImpl();
        User u = service.Userlogin(user);


        //用于异步提交  提交info数据
        Resultinfo info = new Resultinfo();
        //4.判断用户对象是否为null
        if(u==null) {
            //用户名或密码错误
            info.setFlag(false);
            info.setErrormag("用户名或密码错误");
        }

        //5.判断用户是否激活         &先前注册数据校验不生效
        if(u!=null && !"Y".equals(u.getStatue())) {
            //用户尚未激活
            info.setFlag(false);
            info.setErrormag("您尚未激活，请激活");
        }

        //6.判断登录成功    把数据放在Session域中
        if(u!=null && "Y".equals(u.getStatue())) {
            //登录成功
            info.setFlag(true);
            request.getSession().setAttribute("user",u);//登录成功标记，用于欢迎展示
        }


        //响应数据
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request,response);
    }
}
