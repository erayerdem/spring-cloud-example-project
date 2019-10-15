package com.example.feignandrest;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Decoder  implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        System.out.println("status= "+response.status());
        return new Exception("hello");
    }
}
