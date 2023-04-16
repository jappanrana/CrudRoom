package com.example.crudroom.Room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


private const val TAG = "UserInfoViewModel "

class UserInfoViewModel(application: Application) : AndroidViewModel(application)  {
    private val repository: UserInfoRepository
    val allItems: LiveData<List<UserInfo>>

    init {
        Log.d(TAG, "Inside ViewModel init")
        val dao = AppRoomDatabase.getDatabase(application).userInfoDao()
        repository = UserInfoRepository(dao)
        allItems = repository.allItems
    }

    fun insert(item: UserInfo) = viewModelScope.launch {
        repository.insert(item)
    }

    fun update(item: UserInfo) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: UserInfo) = viewModelScope.launch {
        repository.delete(item)
    }

}