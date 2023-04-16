package com.example.crudroom.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.crudroom.Activity.PersonalInfo
import com.example.crudroom.Model.NewUser
import com.example.crudroom.R
import com.example.crudroom.Room.UserInfo
import com.example.crudroom.Utils.SharedPrefMethods

class UserInfoAdapter(private val context: Context, private val userList: List<UserInfo>) : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_info_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.userNameTextView.text = user.fName

        holder.itemView.setOnClickListener {
            val temp = NewUser(
                user.fName,
                user.lName,
                user.phoneNumber,
                user.gender,
                user.dob,
                user.employeeName,
                user.designation,
                user.accType,
                user.bankName,
                user.bankBranchName,
                user.AccNo,
                user.IFSCcode,
                user.imagePath,
                user.employeeNo,
                user.workExp
            )
            SharedPrefMethods.user(context, temp)
            context.startActivity(Intent(context, PersonalInfo::class.java))
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTextView: TextView = itemView.findViewById(R.id.user_name_text_view)
    }
}
