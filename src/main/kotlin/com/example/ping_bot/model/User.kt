package com.example.ping_bot.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    val id: Long = 0,
    val role: String = "USER"
)
