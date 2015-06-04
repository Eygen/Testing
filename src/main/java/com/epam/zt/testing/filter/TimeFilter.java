package com.epam.zt.testing.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TimeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String path = "/testing/";
        if(session != null && !session.isNew()) {
            chain.doFilter(req, resp);
        } else if (!request.getRequestURI().equals(request.getContextPath() + path)) {
            /*String location = request.getContextPath() + "login.jsp";
            response.sendRedirect(location);*/
            response.sendRedirect("/testing/");
        }
        //chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
