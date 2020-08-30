package webServlet;
//
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
////统一解决编码问题
//@WebFilter("/user")
//public class CharchateFilter implements Filter {
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
////        将父类接口转为子类接口
//        HttpServletRequest request = (HttpServletRequest)req;
//        HttpServletResponse response = (HttpServletResponse)resp;
//
////        获取请求方法
//        String method = request.getMethod();
////        解决post请求中文乱码问题
//        if(method.equalsIgnoreCase("post")){
//            request.setCharacterEncoding("utf-8");
//        }
////        处理响应乱码
//        response.setContentType("text/html;charset=utf-8");
//        chain.doFilter(req,resp);
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//
//    }
//
//}