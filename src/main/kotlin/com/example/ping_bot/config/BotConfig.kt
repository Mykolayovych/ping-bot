package com.example.ping_bot.config

import com.example.ping_bot.service.BotService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

@Configuration
class BotConfig {

    @Value("\${ADMIN_IDS}")
    private lateinit var adminIds: String

    @Bean
    fun adminIdList(): List<Long> = adminIds.split(",").map { it.trim().toLong() }

    @Bean
    fun telegramBotsApi(botService: BotService): TelegramBotsApi {
        val botsApi = TelegramBotsApi(DefaultBotSession::class.java)
        botsApi.registerBot(botService)
        return botsApi
    }
}
