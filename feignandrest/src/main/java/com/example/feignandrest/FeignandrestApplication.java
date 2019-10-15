package com.example.feignandrest;

import feign.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class FeignandrestApplication {
    @Autowired
    Environment environment;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserFeignClient userFeignClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignandrestApplication.class, args);
    }


    @GetMapping("/rest/{id}")
    public ExampleModel exampleModel(@PathVariable Long id) {
        ExampleModel forObject = null;
        if (id == 1) {
            forObject = restTemplate.getForObject(String.format("http://user-service/getdata/%s", id), ExampleModel.class);
            ResponseEntity<ExampleModel> exchange = restTemplate.exchange(String.format("http://user-service/getdata/%s", id), HttpMethod.GET, null, new ParameterizedTypeReference<ExampleModel>() {

            });
        } else {

            forObject = userFeignClient.getExampleModel(id);
        }
        return forObject;
    }

    @GetMapping("/restmore")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ExampleModel> exampleModels() {
        ResponseEntity<List<ExampleModel>> exchange = restTemplate.exchange("http://user-service/listtest", HttpMethod.GET, null, new ParameterizedTypeReference<List<ExampleModel>>() {
        });


        return exchange.getBody();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Bean
    public Logger.Level level() {

        return Logger.Level.FULL;
    }
}
