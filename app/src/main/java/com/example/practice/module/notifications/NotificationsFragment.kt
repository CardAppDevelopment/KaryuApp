package com.example.practice.module.notifications

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.bean.NotificationData
import com.example.practice.databinding.FragmentNotificationsBinding
import com.example.practice.base.list.BaseRecycleViewAdapter.OnRecyclerItemClickListener
import com.example.practice.bean.NotificationBean
import com.example.practice.utils.LoadingDialogUtils


class NotificationsFragment :  BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var isExpend = false
    private var mLoadingDialog: Dialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView(view)

        val searchview=view.findViewById<SearchView>(R.id.search_str)
        searchview.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            //文字が変更されたとき
            override fun onQueryTextChange(newText: String): Boolean {
                if(searchview.query.toString()==""){
                    initView(view)
                }
                return false
            }
            //検索ボタンを押下したとき
            override fun onQueryTextSubmit(query: String): Boolean {
                initView(view)
                return false
            }
        })
    }
    private fun initData() {
        notificationsViewModel =
            ViewModelProvider(this)[NotificationsViewModel::class.java]
        getActivity().let {notificationsViewModel.getNotificationsList()}
    }

    private fun initView(view:View) {
        val notificationsListView: RecyclerView = viewBinding.nListView
        val titleView: TextView = viewBinding.titleLl.title
        var loadingDialog = LoadingDialogUtils()
        val swipeRefreshLayout: SwipeRefreshLayout= viewBinding.refresh

        titleView.text = getString(R.string.title_notifications)
        notificationsViewModel.notificationsListLiveData.observe(viewLifecycleOwner, Observer {
            var init: (View, NotificationData) -> Unit = { v:View, d:NotificationData ->

                var titleTv = v.findViewById<TextView>(R.id.shop_title)
                var expandableTv=v.findViewById<TextView>(R.id.expandable_text)
                titleTv.text = d.title
                expandableTv.text = d.msg
            }

            var adapter = it.getOrNull()?.let { it1 ->

                var list= mutableListOf<NotificationData>()

                val pattern:String= view.findViewById<SearchView>(R.id.search_str).query.toString()
                it1.notificationList.forEach{
                    if(Regex(pattern).containsMatchIn(it.title)||Regex(pattern).containsMatchIn(it.msg)){
                        list.add(it)
                    }
                }

                NotificationsListViewAdapter(R.layout.notification_item,
                    list,init)
            }
            adapter?.setRecyclerItemClickListener(object : OnRecyclerItemClickListener {
                override fun onRecyclerItemClick(view:View,Position: Int) {
                    var tv =view.findViewById<TextView>(R.id.expandable_text)
                    isExpend = if (!isExpend) {
                        tv.minLines = 0
                        tv.maxLines = Integer.MAX_VALUE
                        true
                    } else {
                        tv.setLines(2)
                        false
                    }
                }
            })
            notificationsListView.layoutManager= LinearLayoutManager(getActivity())
            notificationsListView.adapter=adapter
        })

        notificationsViewModel.loadingLiveData.observe(viewLifecycleOwner,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(getActivity(),"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
            }
        })
        swipeRefreshLayout.setOnRefreshListener {
            notificationsViewModel.getPTRNotificationsList()
        }
        notificationsViewModel.pullToRefreshLiveData.observe(viewLifecycleOwner,{
            swipeRefreshLayout.isRefreshing = it
        })
    }
}