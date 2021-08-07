package com.example.quickstore005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.example.quickstore005.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private var splashTime:Long = 3000
    private lateinit var binding:SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding= DataBindingUtil.setContentView(this, R.layout.splash_screen)
        supportActionBar?.hide()

        Handler().postDelayed({
          startActivity(Intent(this, ActivityLogin::class.java))
            finish()
        },splashTime)


    }
}