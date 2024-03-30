package com.example.bitfit

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {
    @Query("SELECT * FROM health_table")
    fun getAll(): Flow<List<HealthEntity>>

    @Insert
    fun insert(entry: HealthEntity)

    @Query("DELETE FROM health_table")
    fun deleteAll()

    @Query("SELECT AVG(sleepingHours) FROM health_table")
    fun hour_avg():Double
}
