package com.example.personalfinancetracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.personalfinancetracking.databinding.ActivityHomeBinding
import com.example.personalfinancetracking.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)

        firebaseAuth = FirebaseAuth.getInstance()
//        binding.btnLogout.setOnClickListener {
//
//            firebaseAuth.signOut()
//            startActivity(Intent(this,LoginActivity::class.java))
//            finish()
//        }
    }
}