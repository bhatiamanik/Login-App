package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        continueButton.setOnClickListener {
            if (checking()) {
                var email = emailRegister.text.toString()
                var password = passwordRegister.text.toString()
                var name = name.text.toString()
                var phone = phone.text.toString()
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "User Added", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "User Not Added", Toast.LENGTH_LONG).show()
                        }


                    }

            } else {
                Toast.makeText(this, "Enter The Details", Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun checking(): Boolean {                                               /* function for checking ki user ne space hi to ni daal diya as a input */
        if (name.text.toString().trim { it <= ' ' }.isNotEmpty() &&
            phone.text.toString().trim { it <= ' ' }.isNotEmpty() &&
            emailRegister.text.toString().trim { it <= ' ' }.isNotEmpty() &&
            passwordRegister.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }


}