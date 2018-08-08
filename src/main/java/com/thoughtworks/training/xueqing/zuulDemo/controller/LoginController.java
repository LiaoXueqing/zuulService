package com.thoughtworks.training.xueqing.zuulDemo.controller;

import com.thoughtworks.training.xueqing.zuulDemo.client.UserClient;
import com.thoughtworks.training.xueqing.zuulDemo.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Controller
public class LoginController {
@Autowired
    private UserClient userClient;
    @Value("${secretkey}")
    private String secretKey;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        System.out.println("login controller");
        try{
            User loggedUser = userClient.login(user);
            if(loggedUser!=null){
                HashMap<String,Object> claims = new HashMap<>();
                claims.put("cardId",loggedUser.getId());
                claims.put("name",loggedUser.getName());

                String strToken = Jwts.builder()
                        .addClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                        .compact();

                return ResponseEntity.ok(strToken);
            }
            System.out.println(">>>>--------"+user);

        }catch(Exception e){
            System.out.println("generate token error");
            return null;
        }
        return null;
    }
}
