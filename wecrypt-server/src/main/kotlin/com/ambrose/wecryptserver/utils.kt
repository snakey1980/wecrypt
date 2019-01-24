package com.ambrose.wecryptserver

val allCodes = (1..4).flatMap { first -> (1..4).minus(first).flatMap { second -> (1..4).minus(setOf(first, second)).map { third -> "$first$second$third" } } }.toSet()