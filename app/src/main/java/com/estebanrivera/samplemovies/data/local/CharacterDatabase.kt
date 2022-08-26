package com.estebanrivera.samplemovies.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    //region Companion Object

    companion object {

        private const val DATABASE_NAME = "sample_marvel_app"

        @Synchronized
        fun getDatabase(context: Context): CharacterDatabase = Room.databaseBuilder(
            context.applicationContext,
            CharacterDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

}