package com.example.personalfinancetracking

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.personalfinancetracking.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        firebaseAuth = FirebaseAuth.getInstance()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.NavHostFragment) as NavHostFragment

        navController = navHostFragment.navController
        binding.bottomNavBar.setupWithNavController(navController)
//        binding.btnLogout.setOnClickListener {
//
//            firebaseAuth.signOut()
//            startActivity(Intent(this,LoginActivity::class.java))
//            finish()
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
         R.id.btnLogout->logout()
        }
        return true
    }
    fun logout()
    {
        firebaseAuth.signOut()
        val intent = Intent(this@HomeActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }


}