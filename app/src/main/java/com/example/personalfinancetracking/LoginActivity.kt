package com.example.personalfinancetracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<TextInputEditText>(R.id.UsernameET_Login)
        val password = findViewById<TextInputEditText>(R.id.PasswordET_Login)
        val loginBtn = findViewById<Button>(R.id.btnLogin)

        loginBtn.setOnClickListener {
            var unm = username.text.toString()
            var pass = password.text.toString()
        }
    }
}