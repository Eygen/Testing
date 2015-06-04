package com.epam.zt.testing.filter;

import com.epam.zt.testing.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String login = "/testing/login";
        String registry = "/testing/registry";
        String registrySuccess = "/testing/registrySuccess";
        if ((!request.getRequestURI().equals(request.getContextPath() + login)) &&
                (!request.getRequestURI().equals(request.getContextPath() + registry)) &&
                (!request.getRequestURI().equals(request.getContextPath() + registrySuccess))) {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/pages/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
