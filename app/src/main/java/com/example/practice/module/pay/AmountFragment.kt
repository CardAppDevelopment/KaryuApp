package com.example.practice.module.pay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.practice.base.BaseFragment
import com.example.practice.bean.QRData
import com.example.practice.databinding.FragmentAmountBinding
import org.json.JSONObject
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practice.bean.PayRequestBean
import com.example.practice.module.MyData

class AmountFragment: BaseFragment<FragmentAmountBinding>(FragmentAmountBinding::inflate) {

    private val args:AmountFragmentArgs by navArgs()

    private lateinit var payViewModel:PayViewModel

    private var _binding: FragmentAmountBinding? = null
    private val binding get() = _binding!!

    private fun initData() {
        payViewModel =
            ViewModelProvider(this)[PayViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAmountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initData()

        val data=qrData_json_disassembly(args.qrData)


        binding.btnPay.setOnClickListener{
            try{
                PayViewModel().getPayResponse(
                    PayRequestBean(
                        data.date,
                        "",
                        MyData().getID(),
                        data.id,
                        (binding.editPrice.text).toString().toInt(),
                        data.time
                    )
                )
            }
            catch(e:Exception){

            }

        }

        payViewModel.payLiveData.observe(viewLifecycleOwner, Observer {
            var action=AmountFragmentDirections.actionNavigationAmountToNavigationPaydone()
            findNavController().navigate(action)
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun qrData_json_disassembly(data:String): QRData {
        val jsonObject= JSONObject(data)
        return QRData(
            jsonObject.getString("id"),
            jsonObject.getString("name"),
            jsonObject.getString("date"),
            jsonObject.getString("time"),
            jsonObject.getString("hash"))
    }

    fun test(){

    }

}

