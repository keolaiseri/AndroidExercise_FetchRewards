package com.keolaiseri.androidexercise_fetchrewards

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.annotation.JsonInclude
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.keolaiseri.androidexercise_fetchrewards.api.ListApiService
import com.keolaiseri.androidexercise_fetchrewards.api.ListItem
import com.keolaiseri.androidexercise_fetchrewards.databinding.ActivityMainBinding
import com.keolaiseri.androidexercise_fetchrewards.databinding.FragmentListBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log


/**
 * This class implements a [RecyclerView] [RecyclerView.Adapter] which uses Data Binding to present [List]
 * data. The adapters also checks for null and blank values. This adapter class is designed to receive data
 * and then apply it to each item in a recycler view.
 */

class ListItemAdapter(val listItem : MutableList<ListItem>)
    : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    /*
    * Here is where we instantiate our viewBinding object and
    * set it up for initialization later on
    * */
    private lateinit var binding: FragmentListBinding

    /**
     * The ListItemViewHolder constructor takes the binding variable from the associated
     * ListView, which nicely gives it access to the full [ListItem] information.
     */
    class ListItemViewHolder(private var binding: FragmentListBinding) :

        RecyclerView.ViewHolder(binding.root){
            fun bind(listItem: ListItem){


                binding.items = listItem
                // This is important, because it forces the data binding to execute immediately,
                // which allows the RecyclerView to make the correct view size measurements
                binding.executePendingBindings()

                //Sets the id text from the data class/JSON object to our view.
                binding.idText.text = "ID: ${listItem.id}"
                //Sets the listId text from the data class/JSON object to our view.
                binding.listIdText.text = "List ID: ${listItem.listId}"

                binding.nameText.text = listItem.name

                //Checks for null or blank values and sets the name text accordingly.
                //when (listItem.name){
               // null -> binding.nameText.text = "Name: "
                //"" -> binding.nameText.text = "Name: "
                //else -> binding.nameText.text = "Name: ${listItem.name}"
                //}
            }

        }


    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder
                (parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(FragmentListBinding.inflate(LayoutInflater.from(parent.context)))

    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        listItem.sortBy { it.listId }
        val item = listItem[position]
        holder.bind(item)

    }

    //Returns the size of the listItem object
    override fun getItemCount() = listItem.size


}