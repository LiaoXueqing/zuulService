//package com.thoughtworks.training.xueqing.zuulDemo.controller;
//
//import com.thoughtworks.training.xueqing.zuulDemo.client.UserClient;
//import com.thoughtworks.training.xueqing.zuulDemo.dto.User;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.HashMap;
//
//public class LoginController {
//
//    private UserClient userClient;
//    @Value("${secretkey}")
//    private String secretKey;
//
//    @PostMapping("api/login")
//    public String login(@RequestBody User user){
//        try {
//            User loggedUser = userClient.login(user);
//
//            HashMap<String,Object> claims = new HashMap<>();
//            claims.put("cardId",loggedUser.getId());
//            claims.put("name",loggedUser.getName());
//
//            String strToken = Jwts.builder()
//                    .addClaims(claims)
//                    .signWith(SignatureAlgorithm.HS512, secretKey)
//                    .compact();
//            return strToken;
//        }catch (Exception e){
//            return "";
//        }
//
//
//    }
//}
