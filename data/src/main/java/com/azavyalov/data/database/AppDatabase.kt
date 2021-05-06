package com.azavyalov.data.database

import androidx.room.Database
import com.azavyalov.data.models.CharacterDetails

@Database(entities = [CharacterDetails::class], version = 1, exportSchema = false)
abstract class AppDatabase {
}