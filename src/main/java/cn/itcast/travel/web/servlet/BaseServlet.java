package cn.itcast.travel.web.servlet;

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

@WebServlet(name = "BaseServlet", urlPatterns = {"/BaseServlet"})
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("baseservlet被访问了");
        //完成方法分发
        //获取请求路径
        String uri = req.getRequestURI();  //travel/user/add
        //System.out.println("请求uri："+uri);//travel/user/find
        //获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        //System.out.println("方法名称"+methodName);
        //获取对象的方法名称
        //System.out.println(this);//代表对应的user servlet对象
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            //method.setAccessible(true);
            method.invoke(this,req,resp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //执行对象

    }

    /**
     * 直接将传入的json序列化传回客户端
     * @param object
     * @param response
     */
    public void writeValue(Object object,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),object );
    }

    /**
     *将传入的json序列化返回
     * @param object
     * @param response
     */
    public String writeValueAsString(Object object,HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        return mapper.writeValueAsString(object );
    }
}

