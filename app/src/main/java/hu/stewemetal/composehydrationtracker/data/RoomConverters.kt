package hu.stewemetal.composehydrationtracker.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RoomConverters {
    @TypeConverter
    fun fromLocalDateToString(date: LocalDate?): String? =
        date?.format(DateTimeFormatter.ISO_LOCAL_DATE)

    @TypeConverter
    fun fromStringToLocalDate(value: String?): LocalDate? =
        value?.let { LocalDate.parse(it) }
}