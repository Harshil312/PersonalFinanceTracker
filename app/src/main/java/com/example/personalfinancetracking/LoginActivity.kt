package com.example.personalfinancetracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<TextInputEditText>(R.id.EmailET_Login)
        val password = findViewById<TextInputEditText>(R.id.PasswordET_Login)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val LoginTextView = findViewById<TextView>(R.id.TextView_Login)

        loginBtn.setOnClickListener {
            var unm = username.text.toString()
            var pass = password.text.toString()
        }
        LoginTextView.setOnClickListener {
            var intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}