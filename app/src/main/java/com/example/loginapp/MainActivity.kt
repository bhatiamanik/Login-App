package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth   //comment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        LoginButton.setOnClickListener {
            if (checking()) {
                val email = Email.text.toString()
                val password = Password.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "login successful", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Wrong details", Toast.LENGTH_LONG).show()

                        }
                    }


            } else {
                Toast.makeText(this, "Enter The Details", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checking(): Boolean {
        if (Email.text.toString().trim { it <= ' ' }.isNotEmpty()
            && Password.text.toString().trim { it < ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false

    }
}