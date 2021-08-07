 package com.example.quickstore005

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.quickstore005.databinding.ActivityMainBinding

 class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)

        var url = "http://10.100.22.100/sales_web/category.php"
        var list = ArrayList<String>()
        var rq:RequestQueue= Volley.newRequestQueue(this)
        var jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response->
            for(x in 0..response.length()-1){
                list.add(response.getJSONObject(x).getString("category"))
                var adp = ArrayAdapter(this, R.layout.cat_text,list)
                binding.homeCat.adapter= adp
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,"Connect to the internet",Toast.LENGTH_LONG).show()
        })
        rq.add(jar)


        binding.homeCat.setOnItemClickListener{ adapterView, view, i, id ->
            var cat:String = list[i]
            var obj = Intent(this, ItemActivity::class.java)
            obj.putExtra("cat", cat)
            startActivity(obj)

        }
    }
}