package com.example.crudroom.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserInfoDao {

    @Query("Select * from user_table")
    fun getall(): LiveData<List<UserInfo>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: UserInfo)

    @Update
    suspend fun update(vararg items: UserInfo)

    @Delete
    suspend fun delete(vararg items: UserInfo)
}