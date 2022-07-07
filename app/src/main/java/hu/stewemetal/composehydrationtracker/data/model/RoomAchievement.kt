package hu.stewemetal.composehydrationtracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "achievement")
data class RoomAchievement(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "completion_date") val completionDate: LocalDate?,
)