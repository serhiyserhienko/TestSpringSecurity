package com.example.demo.controllers;

import com.example.demo.servicies.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(helloService.getMessage(), HttpStatus.OK);
    }
}
