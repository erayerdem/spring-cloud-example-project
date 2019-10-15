package com.example.userservice;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@Log
public class UserServiceApplication {


    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @GetMapping("status")
    public String string() {

        return "UP";
    }

    @GetMapping("secret")
    public String secret() {

        return "SECRET";
    }

    @GetMapping("configtest")
    public String string12() {

        return environment.getProperty("gitvalue");
    }

    @GetMapping("getdata/{id}")
    public ExampleModel1 exampleModel1(@PathVariable Long id) {

        System.out.println("hello from"+environment.getProperty("server.port"));
        return new ExampleModel1(id, "samet");
    }
    @GetMapping("/listtest")
    public List<ExampleModel1> exampleModel1s(){

        return Arrays.asList(new ExampleModel1(1L,"da"),new ExampleModel1(2L,"dad"));
    }

}
