package com.example.ping_bot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PingBotApplication

fun main(args: Array<String>) {
	runApplication<PingBotApplication>(*args)
}
