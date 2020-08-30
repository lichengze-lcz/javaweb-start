package webServlet;

import service.UserService;
import service.impl.UserServletImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.在邮件中获取激活码 code
        String code = request.getParameter("code");
        if (code != null){

            //2.创建Servlet完成激活,验证code
            UserService service = new UserServletImpl();
            //判断用户是否存在完成激活  查询数据库code 和传递参数code是否相同
            boolean flag = service.active(code);

            //3.判断标记， 来响应信息
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功,请 <a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败,请联系管理员!";
            }
            //响应消息写回客户端
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doPost(request,response);
    }
}
