package com.thoughtworks.training.xueqing.zuulDemo.security;

import com.google.common.net.HttpHeaders;
import com.netflix.zuul.context.RequestContext;
import com.thoughtworks.training.xueqing.zuulDemo.client.UserClient;
import com.thoughtworks.training.xueqing.zuulDemo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
@Slf4j
@Component
public class TodoAuthFilter extends OncePerRequestFilter{

    @Value("${secretkey}")
    private String secretKey;
    @Autowired
    private UserClient userClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("zuul service request---"+request);
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.isEmpty(token)) {
            User user = validateUser(token);
            SecurityContextHolder.getContext()
                    .setAuthentication(
                            new UsernamePasswordAuthenticationToken(user,
                                    null,
                                    Collections.emptyList()));
            System.out.println("user>>>>>>>>>>"+user);
            RequestContext.getCurrentContext().addZuulRequestHeader(HttpHeaders.AUTHORIZATION,
                    String.format("%s:%s",user.getId(),user.getName())
            );


        }
        filterChain.doFilter(request, response);
    }

    private User validateUser(String token) {

        Integer id = (Integer) Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token).getBody().get("cardId");
        String name = (String) Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token).getBody().get("name");
        String innerToken = id + ":" + name;
        System.out.println(">>>innerToken<<<" + innerToken);
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;

//        return userClient.verifyToken(innerToken);
    }
}

