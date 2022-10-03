package com.example.personalfinancetracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.personalfinancetracking.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLinkLogin.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.EmailETLogin.text.toString()
            val pass = binding.PasswordETLogin.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this,HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Username or Password Incorrect!", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Empty Fields are Not Allowed!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}