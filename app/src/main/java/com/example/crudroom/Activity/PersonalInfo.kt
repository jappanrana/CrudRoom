package com.example.crudroom.Activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.example.crudroom.Model.NewUser
import com.example.crudroom.R
import com.example.crudroom.Utils.Constances
import com.example.crudroom.Utils.SharedPrefMethods.user
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class PersonalInfo : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var personalFirstName: TextInputEditText
    private lateinit var personalLastName: TextInputEditText
    private lateinit var personalPhoneNo: TextInputEditText
    private lateinit var personalDOB: TextInputEditText
    private lateinit var personalDOBButton: Button
    private lateinit var personalMale: RadioButton
    private lateinit var personalFemale: RadioButton
    private lateinit var personalNext: Button
    private lateinit var currentUser: NewUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        mContext = this@PersonalInfo

        defineVariable()

        onClicks()
    }

    private fun defineVariable() {
        currentUser = user(mContext);
        personalFirstName = findViewById(R.id.personalFirstName)
        personalLastName = findViewById(R.id.personalLastName)
        personalPhoneNo = findViewById(R.id.personalPhoneNo)
        personalDOB = findViewById(R.id.personalDOB)
        personalDOBButton = findViewById(R.id.personalDOBButton)
        personalMale = findViewById(R.id.personalMale)
        personalFemale = findViewById(R.id.personalFemale)
        personalNext = findViewById(R.id.personalNext)

        personalFirstName.setText(currentUser.getfName())
        personalLastName.setText(currentUser.getlName())
        personalPhoneNo.setText(currentUser.phoneNumber)
        personalDOB.setText(currentUser.dob)
        if(currentUser.gender == Constances.female){
            personalFemale.isChecked = true
            personalMale.isChecked = false
        }else{
            personalFemale.isChecked = false
            personalMale.isChecked = true
        }
    }

    private fun onClicks() {
        personalDOBButton.setOnClickListener{
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                    personalDOB.setText(selectedDate)
                }, year, month, dayOfMonth)

            datePickerDialog.show()
        }

        personalNext.setOnClickListener {
            if(personalFirstName.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Name",Toast.LENGTH_SHORT).show()
                personalFirstName.requestFocus()
                personalFirstName.error = "Required!!"
            } else if(personalLastName.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Name",Toast.LENGTH_SHORT).show()
                personalLastName.requestFocus()
                personalLastName.error = "Required!!"
            } else if(personalPhoneNo.text.toString() == "" || personalPhoneNo.text.toString().length < 10){
                Toast.makeText(mContext,"Please Input Phone Number",Toast.LENGTH_SHORT).show()
                personalPhoneNo.requestFocus()
                personalPhoneNo.error = "Required!!"
            } else if(personalDOB.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Date Of Birth",Toast.LENGTH_SHORT).show()
                personalDOB.requestFocus()
                personalDOB.error = "Required!!"
            } else{
                currentUser.setfName(personalFirstName.text.toString())
                currentUser.setlName(personalLastName.text.toString())
                currentUser.phoneNumber = personalPhoneNo.text.toString()
                currentUser.dob = personalDOB.text.toString()
                if(personalMale.isChecked){
                    currentUser.gender = Constances.male
                }else{
                    currentUser.gender = Constances.female
                }

                user(mContext,currentUser)
                startActivity(Intent(mContext, EmployeeInfo::class.java))
            }
        }
    }
}