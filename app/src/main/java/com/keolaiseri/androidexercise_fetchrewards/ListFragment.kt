package com.keolaiseri.androidexercise_fetchrewards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.keolaiseri.androidexercise_fetchrewards.databinding.FragmentListBinding


class ListFragment: Fragment() {


    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentListBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false)



        return binding.root
    }
}