package com.thoughtworks.training.xueqing.zuulDemo.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtworks.training.xueqing.zuulDemo.dto.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "user-service",fallback = HystrixCommand.class)
public interface UserClient {
    @PostMapping("/users/verifications")
    User verifyToken(String token);
    @PostMapping(value = "/login")
    User login(User user);
}
