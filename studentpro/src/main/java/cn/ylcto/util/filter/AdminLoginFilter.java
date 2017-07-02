package cn.ylcto.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kangkang on 2017/7/2.
 */
@WebFilter({"/pages/back/index.jsp","/pages/back/classes/*","/pages/back/student/*"})
public class AdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession ses = request.getSession();
        if (ses.getAttribute("email") != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            request.setAttribute("msg","你是非法用户，不能操作");
            request.setAttribute("path", "/login.jsp");
            request.getRequestDispatcher("/pages/forward.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}