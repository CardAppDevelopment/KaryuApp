package com.example.practice.module.pay

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.practice.base.BaseFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practice.R
import com.example.practice.databinding.FragmentPaydoneBinding

class PayDoneFragment : BaseFragment<FragmentPaydoneBinding>(FragmentPaydoneBinding::inflate) {

    private lateinit var payViewModel: PayViewModel

    private val args:PayDoneFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()

        view.findViewById<TextView>(R.id.txtName).text=args.qrdata.name
        view.findViewById<TextView>(R.id.txtMsg).text=args.response.payInfo.msg
        view.findViewById<TextView>(R.id.txtPrice).text= (args.response.payInfo.money).toString()
    }
    private fun initData() {
        payViewModel =
            ViewModelProvider(this)[PayViewModel::class.java]
    }

    private fun initView() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        findNavController().popBackStack()
    }

}