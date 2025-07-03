package com.example.ping_bot.controller

import com.example.ping_bot.service.BotService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ApiController(
    @Autowired private val botService: BotService
) {

    data class PingRequest(
        val telegramUserId: Long,
        val firstName: String
    )

    @PostMapping("/ping")
    fun pingAdmin(@RequestBody request: PingRequest) {
        botService.notifyAdmins(request.telegramUserId, request.firstName)
    }
}
