package hu.stewemetal.composehydrationtracker.domain.model

import java.time.LocalDate

data class ConsumptionPerDay(
    val milliliters: Int,
    val dateTime: LocalDate,
)
