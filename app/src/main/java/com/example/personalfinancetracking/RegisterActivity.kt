package com.example.personalfinancetracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.personalfinancetracking.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    lateinit var binding:ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register)


        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnLinkRegister.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.EmailETRegister.text.toString()
            val pass = binding.PasswordETRegister.text.toString()
            val confirmPass = binding.RePasswordETRegister.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,HomeActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Username or Password Incorrect!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password Does Not Match!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields are Not Allowed!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}