package com.keolaiseri.androidexercise_fetchrewards.api

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


//Our Base URL where the app will retrieve the JSON data
private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"


/**
 * A public interface that exposes the [getListItems] method
 */
interface ListApiService {




    /* The @GET annotation indicates that the "hiring.json" endpoint will be requested with the GET
    * HTTP method
    */
    @GET("hiring.json")
    fun getListItems() : Call<MutableList<ListItem>>

    companion object{
        operator fun invoke(): ListApiService{

            val gson = GsonBuilder()
                .setPrettyPrinting()
                .create()

            val callback = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ListApiService::class.java)


            /**
             * Use the Retrofit builder to build a retrofit object using a Gson converter.
             */
            return callback
        }
    }
}

