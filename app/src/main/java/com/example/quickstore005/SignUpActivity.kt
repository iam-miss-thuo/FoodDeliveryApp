package com.example.quickstore005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.quickstore005.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        supportActionBar?.hide()

        binding.login.setOnClickListener{
            startActivity(Intent(this, ActivityLogin::class.java))
            finish()
        }

        binding.signupButton.setOnClickListener{
            var url = "http://10.100.22.100/sales_web/registration.php?mobile=" + binding.userPhone.text.toString() +"&name="+ binding.userName.text.toString()+
                    "&address=" + binding.userAddress.text.toString() + "&password=" + binding.userPassword.text.toString()
            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr = StringRequest(Request.Method.GET,url, Response.Listener { response ->
                if (response.equals("0")){
                    Toast.makeText(this, "Mobile already used", Toast.LENGTH_LONG).show()
                }else
                    startActivity(Intent(this, MainActivity::class.java))
            }, Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })
            rq.add(sr)
        }
    }
}