package hu.stewemetal.composehydrationtracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.stewemetal.composehydrationtracker.data.model.RoomConsumptionPerDay
import hu.stewemetal.composehydrationtracker.data.model.RoomHydrationEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface HydrationEntryDao {

    @Query("SELECT * FROM hydration_entry ORDER BY id DESC")
    fun getAll(): Flow<List<RoomHydrationEntry>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entry: RoomHydrationEntry)

    @Query("SELECT SUM(milliliters) as milliliters, date_time FROM hydration_entry GROUP BY date_time")
    fun getConsumptionPerDay(): Flow<List<RoomConsumptionPerDay>>
}