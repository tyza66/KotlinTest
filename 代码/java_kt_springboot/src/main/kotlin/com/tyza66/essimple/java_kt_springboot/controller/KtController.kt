package com.tyza66.essimple.java_kt_springboot.controller

import com.tyza66.essimple.java_kt_springboot.model.JavaStudent
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class KtController {
    @RequestMapping("/kotlin")
    fun test(): String {
        var s = JavaStudent("kotlin")
        return s.name
    }
}