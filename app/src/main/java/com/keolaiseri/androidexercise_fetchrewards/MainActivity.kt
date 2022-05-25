package com.keolaiseri.androidexercise_fetchrewards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.annotation.JsonInclude
import com.keolaiseri.androidexercise_fetchrewards.api.ListApiService
import com.keolaiseri.androidexercise_fetchrewards.api.ListItem
import com.keolaiseri.androidexercise_fetchrewards.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*
*Author: Keola Iseri
*Date: Wed, May 25
*Description:
* Please write a native Android app in Kotlin or Java that retrieves the data from
* https://fetch-hiring.s3.amazonaws.com/hiring.json.

Display this list of items to the user based on the following requirements:
Display all the items grouped by "listId"
Sort the results first by "listId" then by "name" when displaying.
Filter out any items where "name" is blank or null.
The final result should be displayed to the user in an easy-to-read list.
* */

class MainActivity : AppCompatActivity() {

    /**
     * Our MainActivity is only responsible for setting the content view because our
     * app only has one screen the MainActivity is the host for our views instead of
     * a fragment.
     */



    /*
    * Here is where we instantiate our viewBinding object and
    * set it up for initialization later on
    * */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding object set as ContentView
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        /**
        * Calling our API service to retrieve a callback of a Mutable List of [ListItem]
         * onFailure gets called if the Api service fails.
        */
        ListApiService().getListItems().enqueue(object : Callback<MutableList<ListItem>>{

            override fun onResponse(
                call: Call<MutableList<ListItem>>,
                response: Response<MutableList<ListItem>>
            ) {
               val listItems = response.body()
                listItems?.let {
                    showListItems(it)
                }
            }

            override fun onFailure(call: Call<MutableList<ListItem>>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }

        })

        }

    /**
     * Exposes our recyclerView to the data provided by [ListItemAdapter]
     */
    private fun showListItems(listItem: MutableList<ListItem>) {
        binding.recyclerListItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerListItems.adapter = ListItemAdapter(listItem)

    }


}


