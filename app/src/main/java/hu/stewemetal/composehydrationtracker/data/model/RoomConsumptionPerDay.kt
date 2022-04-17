package hu.stewemetal.composehydrationtracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import hu.stewemetal.composehydrationtracker.domain.model.ConsumptionPerDay
import java.time.LocalDate

@Entity(tableName = "hydration_entry")
data class RoomConsumptionPerDay(
    @ColumnInfo(name = "milliliters") val milliliters: Int,
    @ColumnInfo(name = "date_time") val dateTime: String,
)

fun RoomConsumptionPerDay.toDomainModel() =
    ConsumptionPerDay(
        milliliters = milliliters,
        dateTime = LocalDate.parse(dateTime)
    )
