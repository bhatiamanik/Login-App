package com.example.loginapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Logged_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        val sharedPref=this?.getPreferences(Context.MODE_PRIVATE)?:return
        val spEmail=sharedPref.getString("Email","1")
        val email=intent.getStringExtra("email")


        if (spEmail=="1")
        {

            with(sharedPref.edit())
            {
                putString("Email", email)
                apply()


            }

        } else
        {
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}