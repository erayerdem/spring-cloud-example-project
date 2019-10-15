package com.example.userservice;


import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequestModel implements Serializable {

    private String email;
    private String password;
}
