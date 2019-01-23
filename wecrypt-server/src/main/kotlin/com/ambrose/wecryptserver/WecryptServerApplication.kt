package com.ambrose.wecryptserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WecryptServerApplication

fun main(args: Array<String>) {
	runApplication<WecryptServerApplication>(*args)
}

