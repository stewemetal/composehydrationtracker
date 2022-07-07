package hu.stewemetal.composehydrationtracker.domain.model

import java.time.LocalDate

data class HydrationEntry(
    val id: Long?,
    val milliliters: Int,
    val date: LocalDate,
)
