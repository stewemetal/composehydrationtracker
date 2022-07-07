package hu.stewemetal.composehydrationtracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import hu.stewemetal.composehydrationtracker.domain.model.HydrationEntry
import java.time.LocalDate

@Entity(tableName = "hydration_entry")
data class RoomHydrationEntry(
    @PrimaryKey val id: Long?,
    @ColumnInfo(name = "milliliters") val milliliters: Int,
    @ColumnInfo(name = "date_time") val date: LocalDate,
)

fun RoomHydrationEntry.toDomainModel() =
    HydrationEntry(
        id = id,
        milliliters = milliliters,
        date = date
    )
