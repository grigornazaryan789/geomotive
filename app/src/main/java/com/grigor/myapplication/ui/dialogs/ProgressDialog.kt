package com.grigor.myapplication.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.grigor.myapplication.R
import com.grigor.myapplication.databinding.DialogProgressBinding


class ProgressDialog(context:Context) : Dialog(context) {
    var message: String? = null
    private lateinit var mBinding:DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initView()
        setContentView(mBinding.root)
    }

    private fun initDataBinding() {
        DialogProgressBinding.inflate(layoutInflater, null, false).also {
            mBinding = it
        }
    }

    private fun initView() {
        mBinding.progressMessageTV.text = message?:context.getString(R.string.loading)
    }

    override fun onStart() {
        super.onStart()
        initDialogStyle()
    }

    private fun initDialogStyle() {
        window?.apply {
            setCancelable(false)
            val width = (context.resources.displayMetrics.widthPixels * 0.95).toInt()
            setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
}