package me.maiz.project.mblog.component;

import me.maiz.project.mblog.common.AppConstants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
        Object loginFlag = request.getSession().getAttribute(AppConstants.USER_INFO_SK);
        if(loginFlag==null){
            request.setAttribute("msg","您还没有登录( ⊙ o ⊙ )啊！");
            request.getRequestDispatcher("notLogin").forward(request,response);
        }
        return loginFlag!=null;
    }
}
