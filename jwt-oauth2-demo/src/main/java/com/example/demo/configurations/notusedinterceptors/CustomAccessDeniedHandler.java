//package com.example.demo.configurations.notusedinterceptors;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.logging.Logger;
//
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//    public static final Logger log
//            = Logger.getLogger("CustomAccessDeniedHandler");
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        Authentication auth
//                = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            log.info("User: " + auth.getName()
//                    + " attempted to access the protected URL: "
//                    + request.getRequestURI());
//        }
//
//    }
//}
