package webServlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Servlet  优化
 */
//不需要被访问到
public class BaseServlet extends HttpServlet {

//   当父类servlet被访问时  执行该方法
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //父类完成方法的分发
        //1.获取请求路径
        String uri = req.getRequestURI();   //返回  虚拟目录/user/'add'

        //2.获取方法名称    'add'
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);

        //3.获取方法对象method（子类）

        try {               //this  谁调用我？我就代表谁  Servletle类名     参数（子类方法参数都相同）
            Method  method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //4.执行方法
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

//    json 工具类

    /**
     * 直接将传入的对象序列化json，并且写回客户端
     * @param obj
     */
      public void writeValue(Object obj,HttpServletResponse response) throws IOException {
          ObjectMapper mapper = new ObjectMapper();
          response.setContentType("application/json;charset=utf-8");
          mapper.writeValue(response.getOutputStream(),obj);
      }

    /**
     * 直接将传入的对象序列化json,返回给调用者
     * @param obj
     */
      public String writeValueAsString(Object obj) throws JsonProcessingException {
          ObjectMapper mapper = new ObjectMapper();
          return mapper.writeValueAsString(obj);

      }
}
