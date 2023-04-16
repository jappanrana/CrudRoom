package com.example.crudroom.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.crudroom.Model.NewUser
import com.example.crudroom.R
import com.example.crudroom.Room.UserInfo
import com.example.crudroom.Room.UserInfoViewModel
import com.example.crudroom.Utils.SharedPrefMethods
import com.example.crudroom.Utils.SharedPrefMethods.user
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BankInfo : AppCompatActivity() {
    private lateinit var mContext: Context
    private lateinit var bankName: TextInputEditText
    private lateinit var bankBranchName: Spinner
    private lateinit var bankAccountNo: TextInputEditText
    private lateinit var bankIfsc: TextInputEditText
    private lateinit var bankPic: ImageView
    private lateinit var bankPicButton: Button
    private lateinit var bankNext: Button
    private lateinit var currentUser: NewUser
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var path: String
    private lateinit var userInfoViewModel: UserInfoViewModel
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_info)

        mContext = this@BankInfo

        defineVariable()
        
        onClicks()
    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "${applicationContext.packageName}.provider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            var currentPhotoPath: String = absolutePath

            this@BankInfo.path = currentPhotoPath

            val imageFile = File(path)
            val imageUri = Uri.fromFile(imageFile)
            Glide.with(mContext)
                .load(imageUri)
                .into(bankPic)
        }
    }

    private fun defineVariable() {
        currentUser = user(mContext)
        bankName = findViewById(R.id.bankName)
        bankBranchName = findViewById(R.id.bankBranchName)
        bankAccountNo = findViewById(R.id.bankAccountNo)
        bankIfsc = findViewById(R.id.bankIfsc)
        bankPic = findViewById(R.id.bankPic)
        bankPicButton = findViewById(R.id.bankPicButton)
        bankNext = findViewById(R.id.bankNext)
        userInfoViewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]

        bankName.setText(currentUser.bankName.toString())

        val bankBranchList = ArrayList<String>()
        bankBranchList.add("Branch 1")
        bankBranchList.add("Branch 2")
        bankBranchList.add("Branch 3")
        bankBranchList.add("Branch 4")
        bankBranchList.add("Branch 5")

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bankBranchList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bankBranchName.adapter = adapter
        
        bankAccountNo.setText(currentUser.accNo.toString())
        bankIfsc.setText(currentUser.ifsCcode.toString())

        path = currentUser.imagePath
        if(currentUser.imagePath != ""){
            val imageFile = File(currentUser.imagePath)
            val imageUri = Uri.fromFile(imageFile)
            Glide.with(mContext)
                .load(imageUri)
                .into(bankPic)
        }
    }

    private fun onClicks() {
        bankPicButton.setOnClickListener {
            dispatchTakePictureIntent()
        }
        bankNext.setOnClickListener {
            if(bankName.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Name", Toast.LENGTH_SHORT).show()
                bankName.requestFocus()
                bankName.error = "Required!!"
            }else if(bankAccountNo.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Name", Toast.LENGTH_SHORT).show()
                bankAccountNo.requestFocus()
                bankAccountNo.error = "Required!!"
            }else if(bankIfsc.text.toString() == ""){
                Toast.makeText(mContext,"Please Input Name", Toast.LENGTH_SHORT).show()
                bankIfsc.requestFocus()
                bankIfsc.error = "Required!!"
            }else if(path == ""){
                Toast.makeText(mContext,"Image Empty", Toast.LENGTH_SHORT).show()
            } else{
                currentUser.bankName = bankName.text.toString()
                currentUser.bankBranchName = bankBranchName.selectedItem.toString()
                currentUser.accNo = bankAccountNo.text.toString()
                currentUser.ifsCcode = bankIfsc.text.toString()
                currentUser.imagePath = path

                user(mContext, currentUser)

                var temp = UserInfo(
                    0,
                    currentUser.getfName(),
                    currentUser.getlName(),
                    currentUser.phoneNumber,
                    currentUser.gender,
                    currentUser.dob,
                    currentUser.employeeNo,
                    currentUser.employeeName,
                    currentUser.designation,
                    currentUser.accType,
                    currentUser.workExp,
                    currentUser.bankName,
                    currentUser.bankBranchName,
                    currentUser.accNo,
                    currentUser.ifsCcode,
                    currentUser.imagePath
                )

                userInfoViewModel.insert(temp)
                startActivity(Intent(mContext, MainActivity::class.java))
            }
        }
    }
}