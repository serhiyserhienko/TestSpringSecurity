package com.example.demo.controllers;

import com.example.demo.servicies.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser() {
        return new ResponseEntity<>(helloService.getMessage(), HttpStatus.OK);
    }


}
