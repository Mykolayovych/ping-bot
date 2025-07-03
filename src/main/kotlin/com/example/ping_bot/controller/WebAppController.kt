package com.example.ping_bot.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/webapp")
class WebAppController {

    @GetMapping
    fun getWebApp(): String = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Ping Admin</title>
        </head>
        <body>
            <button onclick="ping()">Ping Admin</button>
            <script src="https://telegram.org/js/telegram-web-app.js"></script>
            <script>
                const tg = window.Telegram.WebApp;
                tg.ready();

                function ping() {
                    fetch("/api/ping", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        body: JSON.stringify({
                            telegramUserId: tg.initDataUnsafe.user.id,
                            firstName: tg.initDataUnsafe.user.first_name
                        })
                    })
                    .then(() => alert("Пінг надіслано адміністратору!"))
                    .catch(err => alert("Не вдалося надіслати пінг!"));
                }
            </script>
        </body>
        </html>
    """.trimIndent()
}
