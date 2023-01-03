package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        continueButton.setOnClickListener {
            if (checking())
            {
                var email = emailRegister.text.toString()
                var password = passwordRegister.text.toString()
                var name = name.text.toString()
                var phone = phone.text.toString()

                val user = hashMapOf(
                    "Name" to name,

                    "Phone" to phone,

                    "email" to email
                )
                val Users = db.collection("USERS")
                val query = Users.whereEqualTo("email", email).get()
                    .addOnSuccessListener { tasks ->
                        if (tasks.isEmpty) {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(this) { task ->
                                    if (task.isSuccessful) {
                                        Users.document(email).set(user)
                                        val intent = Intent(this, Logged_in::class.java)
                                        intent.putExtra("email", email)
                                        startActivity(intent)
                                        finish()

                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Authenication Failed",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(this, "User Already Registered", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
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