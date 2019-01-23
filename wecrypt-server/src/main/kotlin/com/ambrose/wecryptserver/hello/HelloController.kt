package com.ambrose.wecryptserver.hello

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class HelloController {

    @GetMapping("/hello")
    fun hello() = "hello"

}