package com.example.earthquakelist

import RetrofitHelper
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakelist.databinding.ActivityEarthquakeListBinding
import com.example.earthquakelist.models.FeatureCollection
import com.example.earthquakelist.models.Features
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EarthquakeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var list: MutableList<Features>
    private lateinit var adapter: EarthquakeAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
             R.id.nav_opt1 -> {
                 adapter = EarthquakeAdapter(list.sortedBy{it.properties.mag}.reversed())
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
             }
            R.id.nav_opt2 -> {
                adapter = EarthquakeAdapter(list.sortedBy { it.properties.time })
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            }
            R.id.nav_opt3 -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setTitle("Danger order")
                    .setMessage("Red is the most dangerous, then is Orange, Yellow, and Green is the least dangerous")

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }
    fun change() {
        setContentView(R.layout.legend)
        rebindLayout(R.layout.legend)
    }

    fun rebindLayout(@LayoutRes layoutId: Int) {
        when (layoutId) {
            R.layout.activity_earthquake_list -> { /* rebind views here */ }
            R.layout.legend -> { /* rebind views here */ }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitHelper.getInstance()
        val earthquakeService = retrofit.create(EarthquakeService::class.java)
        val earthquakeCall = earthquakeService.getEarthquakePastDay()




        earthquakeCall.enqueue(object: Callback<FeatureCollection> {
            override fun onResponse(
                call: Call<FeatureCollection>,
                response: Response<FeatureCollection>
            ) {
                //where get data and set up recycler adapter
                Log.d("onResponse:",  "${response.body()}")

                val earthquakes = response.body()?.features?:listOf()
                list = ArrayList()
                for (quake in earthquakes) {
                    if (quake.properties.mag >= 1.0) {
                        list.add(quake)
                    }
                }

                adapter = EarthquakeAdapter(list)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

                //
            }

            override fun onFailure(call: Call<FeatureCollection>, t: Throwable) {
                Log.d("onResponse:",  "${t.message}")
            }
        })
    }


}