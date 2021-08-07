package com.example.quickstore005


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.quickstore005.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {
    private lateinit var binding:ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_item)

        var cat: String? = intent.getStringExtra("cat")
        var url = "http://10.100.22.100/sales_web/get_items.php?category="+cat

        var list = ArrayList<Item>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var jar = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener { response ->
            for (x in 0..response.length()-1){
                list.add(Item(response.getJSONObject(x).getInt("id"),
                    response.getJSONObject(x).getString("name"),
                    response.getJSONObject(x).getInt("price"),
                    response.getJSONObject(x).getString("photo")))

                var adp = ItemAdapter(this, list)
                binding.itemRv.layoutManager = LinearLayoutManager(this)
                binding.itemRv.adapter = adp
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
        })
        rq.add(jar)

    }
}