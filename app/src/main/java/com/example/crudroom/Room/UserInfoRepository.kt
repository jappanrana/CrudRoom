package com.example.crudroom.Room

import androidx.lifecycle.LiveData

class UserInfoRepository(private val userInfoDao: UserInfoDao) {
    val allItems: LiveData<List<UserInfo>> = userInfoDao.getall()

    suspend fun update(item: UserInfo) {
        userInfoDao.update(item)
    }

    suspend fun insert(item: UserInfo) {
        userInfoDao.insert(item)
    }

    suspend fun delete(item: UserInfo) {
        userInfoDao.delete(item)
    }
}