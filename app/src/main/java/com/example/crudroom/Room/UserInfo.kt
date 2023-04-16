package com.example.crudroom.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserInfo (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val fName: String,
    val lName: String,
    val phoneNumber: String,
    val gender: String,
    val dob: String,
    val employeeNo: Long,
    val employeeName: String,
    val designation: String,
    val accType: String,
    val workExp: Int,
    val bankName: String,
    val bankBranchName: String,
    val AccNo: String,
    val IFSCcode: String,
    val imagePath: String
)