package com.grigor.myapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.grigor.myapplication.ui.dialogs.ProgressDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

abstract class BaseActivity<DB : ViewBinding> : AppCompatActivity() {

    protected abstract val dataBindingInflater: (LayoutInflater) -> DB
    private lateinit var binding: DB
    protected val mBinding get() = binding
    private val progressDialog:ProgressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
        initListeners()
        initViewModels(null)

    }

    private fun initBinding() {
        binding = dataBindingInflater.invoke(layoutInflater)
        setContentView(mBinding.root)
    }

    open fun initViews() {}
    open fun initListeners() {}

    open fun initViewModels(viewModel: BaseViewModel?) {
        viewModel?.mBaseExceptionSharedFlow?.onEach {
            onError(it)
        }?.launchIn(lifecycleScope)
        viewModel?.mBaseLoadingSharedFlow?.onEach {
            onLoading(it)
        }?.launchIn(lifecycleScope)
    }

    open fun onError(throwable: Throwable?) {
        throwable ?: return
        Snackbar.make(binding.root, throwable.message?:"", Snackbar.LENGTH_SHORT).show()
    }

    open fun onLoading(isLoading: Boolean) {
        if (!isLoading && progressDialog.isShowing) {
            progressDialog.dismiss()
        } else if (isLoading && !progressDialog.isShowing) {
            progressDialog.show()
        }
    }
}