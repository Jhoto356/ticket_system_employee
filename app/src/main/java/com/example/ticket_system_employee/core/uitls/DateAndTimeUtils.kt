package com.example.ticket_system_employee.core.uitls

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateAndTimeUtils {
    private val dateFormat = "yyyy-MM-dd HH:mm:ss"
    fun getDateAndTime(): String {
        return try {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(dateFormat)
            current.format(formatter)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    }
}