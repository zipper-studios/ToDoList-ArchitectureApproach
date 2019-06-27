package com.example.kotlinpractice.roomtodolist.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "tasks")
class Task(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "priority") val priority: String
) {
    @Ignore
    constructor() : this("", "")
}