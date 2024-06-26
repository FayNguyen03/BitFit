package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="health_table")
data class HealthEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name="date") var date:String?,
    @ColumnInfo(name="sleepingHours") var sleepingHour:Double?,
    @ColumnInfo(name="mood") var mood: Double?,
    @ColumnInfo(name="note") var note: String?
)