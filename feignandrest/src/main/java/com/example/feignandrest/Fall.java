package com.example.feignandrest;


import org.springframework.stereotype.Component;

@Component
public class Fall implements UserFeignClient{
    @Override
    public ExampleModel getExampleModel(Long id) {
        return null;
    }
}
