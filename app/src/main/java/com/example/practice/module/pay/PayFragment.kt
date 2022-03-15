package com.example.practice.module.pay

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentPayBinding
import com.example.practice.module.ScanQRActivity
import android.app.Activity.RESULT_OK
import android.content.Context
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts


class PayFragment : BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate) {

    private lateinit var payViewModel: PayViewModel

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!

    private lateinit var gdata:String

    private val _qrlauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult(),ActivityResultCallbackFromQR())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentPayBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val intent = Intent(this.activity, ScanQRActivity().javaClass)
        startActivityForResult(intent,1201)*/

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _qrlauncher.launch(Intent(this.activity,ScanQRActivity().javaClass))

        /*val data="{\n" +
                "  \"id\":2345678901,\n" +
                "  \"name\":\"View商店\",\n" +
                "  \"date\":\"2022/01/01\",\n" +
                "  \"time\":\"12:00\",\n" +
                "  \"hash\":\"8018155fe6dca2ef3e713e6ecbc4e6b5649facd6fe12306f8f9d1c38dae0ea79\"\n" +
                "}"
        val action=PayFragmentDirections.actionNavigationPayToNavigationAmount(data)

        try{
            findNavController().navigate(action)
        }
        catch(e:Exception){
            findNavController().navigate(R.id.navigation_amount)
        }*/

    }



    private inner class ActivityResultCallbackFromQR : ActivityResultCallback<ActivityResult> {
        override fun onActivityResult(result: ActivityResult?) {
            if(result?.resultCode == RESULT_OK) {
                var data= result.data?.getStringExtra("qrdata").toString()
                //var bundle = bundleOf("qrdate" to data?.getStringExtra("qrdata").toString())
                //findNavController().navigate(R.id.navigation_amount,bundle)
                var action=PayFragmentDirections.actionNavigationPayToNavigationAmount(data)
                findNavController().navigate(action)
            }
            else{
                findNavController().popBackStack()
            }
        }
    }


}