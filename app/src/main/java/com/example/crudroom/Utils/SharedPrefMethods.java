package com.example.crudroom.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.crudroom.Model.NewUser;
import com.google.gson.Gson;

public class SharedPrefMethods {

    public static void user(Context context, NewUser value){
        SharedPreferences sp = context.getSharedPreferences("storage", Context.MODE_PRIVATE);
        Gson gs = new Gson();
        sp.edit().putString("user",gs.toJson(value)).apply();
    }

    public static NewUser user(Context context){
        SharedPreferences sp = context.getSharedPreferences("storage", Context.MODE_PRIVATE);
        Gson gs = new Gson();
        String savedVal = sp.getString("user","");
        return gs.fromJson(savedVal,NewUser.class);
    }
}
