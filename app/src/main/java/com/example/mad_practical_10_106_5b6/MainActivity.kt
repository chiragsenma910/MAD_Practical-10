package com.example.mad_practical_10_106_5b6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mad_practical_10_106_5b6.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtTitle.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val data = HttpRequest().makeServiceCall("https://api.json-generator.com/templates/qjeKFdjkXCdK/data","rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1")
                    withContext(Dispatchers.Main) {
                        try {
                            if(data != null)
                                runOnUiThread{getPersonDetailsFromJson(data.toString())}
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
    private fun getPersonDetailsFromJson(data: String) {
        val personList = ArrayList<PersonListCardModel>()
        try{
            val jsonArray = JSONArray(data)
            for(i in 0 until jsonArray.length()){
                val jsonObject = jsonArray[i] as JSONObject
                val person = PersonListCardModel(jsonObject)
                personList.add(person)
            }
        }catch (e: Exception) {
            e.printStackTrace()
        }
        val adapter = RecyclerPersonlistAdapter(this, personList)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }
}