package com.tyza66.essimple.java_kt_springboot.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SimpleController {

    @RequestMapping("/java")
    String test(){
        return "java";
    }
}
