package com.example.crudroom.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [UserInfo::class],
    version = 2,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase(){

    abstract fun userInfoDao(): UserInfoDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance as AppRoomDatabase
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()          // TODO: migration
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}