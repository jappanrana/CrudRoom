package com.example.crudroom.Activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudroom.Model.NewUser
import com.example.crudroom.R
import com.example.crudroom.Utils.SharedPrefMethods
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.exp


class EmployeeInfo : AppCompatActivity() {
    private lateinit var mContext: Context
    private lateinit var employeeNo: TextInputEditText
    private lateinit var employeeName: TextInputEditText
    private lateinit var employeeDesignation: TextInputEditText
    private lateinit var employeeAccountType: Spinner
    private lateinit var employeeWorkExperience: Spinner
    private lateinit var employeeNext: Button
    private lateinit var currentUser: NewUser
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var adapter2: ArrayAdapter<String>
    private lateinit var expList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_info)

        mContext = this@EmployeeInfo

        defineVariable()
        onClicks()
    }

    private fun defineVariable() {
        currentUser = SharedPrefMethods.user(mContext);
        employeeNo = findViewById(R.id.employeeNo)
        employeeName = findViewById(R.id.employeeName)
        employeeDesignation = findViewById(R.id.employeeDesignation)
        employeeAccountType = findViewById(R.id.employeeAccountType)
        employeeWorkExperience = findViewById(R.id.employeeWorkExperience)

        if(currentUser.employeeNo.toString()!="0") {
            employeeNo.setText(currentUser.employeeNo.toString())
        }
        employeeName.setText(currentUser.employeeName.toString())
        employeeDesignation.setText(currentUser.designation.toString())

        val accTypeList = ArrayList<String>()
        accTypeList.add("Saving A/C")
        accTypeList.add("Salaried A/C")

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accTypeList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        employeeAccountType.adapter = adapter

        expList = ArrayList<String>()
        expList.add("1")
        expList.add("2")
        expList.add("3")
        expList.add("5")
        expList.add("8")
        expList.add("10")

        adapter2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expList)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        employeeWorkExperience.adapter = adapter2

        employeeNext = findViewById(R.id.employeeNext)
    }

    private fun onClicks() {
        employeeNext.setOnClickListener {
            if(employeeNo.text.toString() == ""){
                Toast.makeText(mContext,"Please Input No", Toast.LENGTH_SHORT).show()
                employeeNo.requestFocus()
                employeeNo.error = "Required!!"
            }else if(employeeName.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Name", Toast.LENGTH_SHORT).show()
                employeeName.requestFocus()
                employeeName.error = "Required!!"
            }else if(employeeDesignation.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Designation", Toast.LENGTH_SHORT).show()
                employeeDesignation.requestFocus()
                employeeDesignation.error = "Required!!"
            } else{
                currentUser.employeeNo = employeeNo.text.toString().toLong()
                currentUser.employeeName = employeeName.text.toString()
                currentUser.designation = employeeDesignation.text.toString()
                currentUser.accType = employeeAccountType.selectedItem.toString()
                currentUser.workExp = employeeWorkExperience.selectedItem.toString().toInt()

                SharedPrefMethods.user(mContext, currentUser)
                startActivity(Intent(mContext, BankInfo::class.java))
            }
        }
    }
}