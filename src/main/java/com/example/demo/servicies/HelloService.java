package com.example.demo.servicies;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getMessage(){
        return "hello";
    }

    public String getSecretMessage(){
        return "hello secret";
    }
}
