package com.example.practice.module.pay

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.databinding.FragmentSettingsBinding
import com.example.practice.module.pay.PayViewModel

class PayDoneFragment : BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {

    private lateinit var payViewModel: PayViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    private fun initData() {
        payViewModel =
            ViewModelProvider(this)[PayViewModel::class.java]
    }

    private fun initView() {

    }


}