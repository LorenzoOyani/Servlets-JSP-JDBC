//package org.servers;
//
//import jakarta.servlet.*;
//
//import java.io.IOException;
//import java.util.Date;
//
//public class LogFilter implements Filter {
//
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        String testParams = filterConfig.getInitParameter("test-param");
//        System.out.println(STR."test-params \{testParams}");
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        String IpAddress = servletRequest.getRemoteAddr();
//        System.out.println(STR."Ip-address \{IpAddress},time \{new Date().toString()}");
//        filterChain.doFilter(servletRequest, servletResponse);
//
//
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
