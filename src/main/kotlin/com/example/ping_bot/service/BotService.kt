package com.example.ping_bot.service

import com.example.ping_bot.model.User
import com.example.ping_bot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo

@Service
class BotService(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val adminIds: List<Long>,
    @Value("\${telegram.bot.token}") private val botToken: String,
    @Value("\${telegram.bot.username}") private val botUsername: String,
    @Value("\${telegram.webapp.url}") private val webAppUrl: String,
) : TelegramLongPollingBot(botToken) {

    override fun getBotUsername(): String = botUsername

    override fun onUpdateReceived(update: Update) {
        val message = update.message ?: return
        val chatId = message.chatId
        val user = message.from

        if (!userRepository.existsById(user.id)) {
            userRepository.save(User(id = user.id, role = "CLIENT"))
        }

        if (message.text == "/start") {
            val webAppButton = InlineKeyboardButton("Open Mini App").apply {
                webApp = WebAppInfo(webAppUrl)
            }
            val markup = InlineKeyboardMarkup(listOf(listOf(webAppButton)))
            val response = SendMessage(chatId.toString(), "Вітаю! Натисніть, щоб відкрити Mini App").apply {
                replyMarkup = markup
            }
            execute(response)
        }
    }

    fun notifyAdmins(userId: Long, firstName: String) {
        adminIds.forEach { adminId ->
            val msg = SendMessage(
                adminId.toString(),
                "Користувач $firstName ($userId) натиснув кнопку Ping!"
            )
            execute(msg)
        }
    }
}
