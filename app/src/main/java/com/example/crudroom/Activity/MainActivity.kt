package com.example.crudroom.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudroom.Adapter.UserInfoAdapter
import com.example.crudroom.Model.NewUser
import com.example.crudroom.R
import com.example.crudroom.Room.UserInfoViewModel
import com.example.crudroom.Utils.SharedPrefMethods.user
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var mContext:Context
    private lateinit var adapter:UserInfoAdapter
    private lateinit var mainScrRecycler:RecyclerView
    private lateinit var mainScrFAB:FloatingActionButton
    private lateinit var userInfoViewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this@MainActivity

        defineVariable()

        onClicks()
    }

    private fun defineVariable() {
        mainScrFAB = findViewById(R.id.mainScrFAB)
        mainScrRecycler = findViewById(R.id.mainScrRecycler)
        mainScrRecycler.layoutManager = LinearLayoutManager(mContext)

        userInfoViewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]
        userInfoViewModel.allItems.observe(this, Observer {
            adapter = UserInfoAdapter(mContext,it)
            mainScrRecycler.adapter = adapter
        })
    }

    private fun onClicks() {
        mainScrFAB.setOnClickListener{
            val temp = NewUser()
            user(mContext,temp)
            startActivity(Intent(mContext, PersonalInfo::class.java))
        }
    }
}
