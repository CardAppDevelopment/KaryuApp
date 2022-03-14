package com.example.practice.module.pay

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.practice.base.BaseFragment
import com.example.practice.bean.QRData
import com.example.practice.databinding.FragmentAmountBinding
import org.json.JSONObject
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.practice.R
import com.example.practice.bean.PayRequestBean
import com.example.practice.bean.PayResponseBean
import com.example.practice.module.MyData
import com.example.practice.utils.LoadingDialogUtils
import org.w3c.dom.Text

class AmountFragment: BaseFragment<FragmentAmountBinding>(FragmentAmountBinding::inflate) {

    private val args:AmountFragmentArgs by navArgs()

    private lateinit var payViewModel:PayViewModel

    private var _binding: FragmentAmountBinding? = null
    private val binding get() = _binding!!
    private var mLoadingDialog: Dialog? = null
    lateinit var loadingDialog:LoadingDialogUtils

    private fun initData() {
        payViewModel =
            ViewModelProvider(this)[PayViewModel::class.java]

        loadingDialog = LoadingDialogUtils()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentAmountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initData()

        val qrdata=qrData_json_disassembly(args.qrData)

        binding.txtName.text=qrdata.name


        binding.btnPay.setOnClickListener{
            try{
                payViewModel.getPayInfo(
                    PayRequestBean(
                        qrdata.date,
                        "hash",
                        MyData().getID(),
                        qrdata.id,
                        (binding.editPrice.text).toString().toInt(),
                        qrdata.time
                    )
                )
            }
            catch(e:Exception){

            }

        }

        payViewModel.loadingLiveData.observe(this,  {
            if(it){
                //mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                val result =payViewModel.paymentInfo
                val redata=result?.getOrNull()
                var action = AmountFragmentDirections.actionNavigationAmountToNavigationPaydone(redata!!,qrdata)
                findNavController().navigate(action)
            }
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

