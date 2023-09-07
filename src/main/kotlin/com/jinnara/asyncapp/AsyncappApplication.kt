package com.jinnara.asyncapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncappApplication

fun main(args: Array<String>) {
	runApplication<AsyncappApplication>(*args)
}
