//package com.thoughtworks.training.xueqing.zuulDemo.security;
//
//import com.google.common.net.HttpHeaders;
//import com.thoughtworks.training.xueqing.zuulDemo.client.UserClient;
//import com.thoughtworks.training.xueqing.zuulDemo.dto.User;
//import io.jsonwebtoken.Jwts;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//
//@Slf4j
//@Component
//public class LoginFilter extends OncePerRequestFilter {
//
//    @Value("${secretkey}")
//    private String secretKey;
//    @Autowired
//    private UserClient userClient;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        System.out.println("login filter"+name + ":" + password);
//
//        //返回字符串
//        response.getOutputStream().print("");


//        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (null != token && !token.equals("")) {
//            Integer tokenId = (Integer) Jwts.parser()
//                    .setSigningKey(secretKey.getBytes())
//                    .parseClaimsJws(token).getBody().get("cardId");
//            String tokenName = (String) Jwts.parser()
//                    .setSigningKey(secretKey.getBytes())
//                    .parseClaimsJws(token).getBody().get("name");
//
//            User tokenUser = new User();
//            tokenUser.setName(tokenName);
//            tokenUser.setId(tokenId);
//
////            User userFromServer = userClient.login(tokenUser);
////            String newToken = userFromServer.getId() + ":" + userFromServer.getName();
////            RequestContext.getCurrentContext().addZuulRequestHeader(HttpHeaders.AUTHORIZATION, newToken);
//
//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken(tokenUser, null, Collections.emptyList()));


//        }
//        filterChain.doFilter(request, response);
//    }
//}


