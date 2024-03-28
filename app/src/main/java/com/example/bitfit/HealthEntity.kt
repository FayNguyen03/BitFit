package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName="health_table")
data class HealthEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name="date") val date:Date?,
    @ColumnInfo(name="sleepingHours") var sleepingHour:Number?,
    @ColumnInfo(name="note") var note: String?,
    @ColumnInfo(name="mood") var mood: Number?
)