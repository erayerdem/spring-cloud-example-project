package com.example.feignandrest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",fallback = Fall.class)
public interface UserFeignClient {
    @GetMapping("/getdatas/{id}")
    public ExampleModel getExampleModel(@PathVariable Long id );


}
