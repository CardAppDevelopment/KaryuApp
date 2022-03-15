package com.example.practice.module.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.bean.ChargeRequestBean
import com.example.practice.bean.QRData
import com.example.practice.databinding.FragmentChargeBinding
import com.example.practice.module.pay.AmountFragmentArgs
import com.example.practice.utils.LoadingDialogUtils
import org.json.JSONObject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChargeFragment : BaseFragment<FragmentChargeBinding>(FragmentChargeBinding::inflate){
    // TODO: Rename and change types of parameters

    private val args: AmountFragmentArgs by navArgs()

    private var _binding: FragmentChargeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel:HomeViewModel
    lateinit var loadingDialog:LoadingDialogUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        /*val titleView: TextView = viewBinding.titleCharge.title
        val backIBtn: ImageView = viewBinding.titleCharge.titleBtn
        val inputChargeEdt: EditText = viewBinding.chargeInput

        backIBtn.visibility = View.VISIBLE
        backIBtn.setImageResource(R.mipmap.white_back)
        titleView.text = "チャージ"
        inputChargeEdt.setRawInputType(EditorInfo.TYPE_CLASS_NUMBER)


        backIBtn.setOnClickListener {
            getFragmentManager()?.popBackStack();
        }*/

    }

    private fun initData() {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        loadingDialog = LoadingDialogUtils()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentChargeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initData()

//        val qrdata=qrData_json_disassembly(args.qrData)

//        binding.txtName.text=qrdata.name



        binding.chargeBtn.setOnClickListener{
//            val a=payViewModel.paymentInfo
            try{
                //送信関係処理
                homeViewModel.getchargeInfo(
                    ChargeRequestBean(
                        "2022/3/16",
                        "hash",
                        //送信元
                        "0",
                        //送信先
                        "1234567890",
                        (binding.editPrice.text).toString().toInt(),
                        "13:00"
                    )
                )
            }
            catch(e:Exception){

            }

        }

        homeViewModel.loadingLiveData.observe(this,  {
            if(it){
                //mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                //  通信での処理
                val result =homeViewModel.chargeInfo
                val redata=result?.getOrNull()
                var action = ChargeFragmentDirections.actionNavigationChargeToNavigationChargedone(redata!!)
                //画面遷移
                findNavController().navigate(action)
            }
        })

        return root
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

