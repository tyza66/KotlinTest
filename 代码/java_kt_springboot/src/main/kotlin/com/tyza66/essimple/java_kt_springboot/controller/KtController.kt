package com.tyza66.essimple.java_kt_springboot.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class KtController {
    @RequestMapping("/kotlin")
    fun test(): String {
        return "kotlin"
    }
}