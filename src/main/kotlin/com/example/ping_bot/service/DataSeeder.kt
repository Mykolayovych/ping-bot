package com.example.ping_bot.service

import com.example.ping_bot.model.User
import com.example.ping_bot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DataSeeder(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val adminIds: List<Long>
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        adminIds.forEach { adminId ->
            if (!userRepository.existsById(adminId)) {
                userRepository.save(User(id = adminId, role = "ADMINISTRATOR"))
            }
        }
    }
}
