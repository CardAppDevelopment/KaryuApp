package com.example.practice.module.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentChargedoneBinding

class ChargeDoneFragment : BaseFragment<FragmentChargedoneBinding>(FragmentChargedoneBinding::inflate){

    private lateinit var homeViewModel: HomeViewModel

    private val args:ChargeDoneFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()

        view.findViewById<TextView>(R.id.txtName).text="チャージ完了"
        view.findViewById<TextView>(R.id.txtMsg).text=""//args.response.msg
        view.findViewById<TextView>(R.id.txtPrice).text= (args.response.money).toString()
    }
    private fun initData() {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun initView() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        findNavController().popBackStack()
    }
}