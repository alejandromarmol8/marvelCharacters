package com.app.marvelcharacters.presentation.views.bases

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.app.marvelcharacters.MainActivity
import com.app.marvelcharacters.R
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment<B: ViewBinding> : Fragment() {

    protected lateinit var binding: B

    protected abstract val viewModel: BaseViewModel
    abstract fun getFragmentBinding(): B

    private var progressBar : ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
        initViews()
        initListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loading.observe(this, {  isLoading ->
            showLoading(isLoading)
        })

        viewModel.errorMsg.observe(this, { msg ->
            AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton(getString(R.string.reintantar)) { _, _ ->
                    viewModel.retry()
                }
                .setCancelable(false)
                .create().show()
        })
    }

    protected open fun initObservers(){
        //Maybe you want or not to override it
    }

    protected open fun initViews(){
        //Maybe you want or not to override it
    }

    protected open fun initListeners(){
        //Maybe you want or not to override it
    }

    private fun showLoading(isLoading: Boolean){
        val progressBarConstraint = (activity as MainActivity).progressBarConstraint
        progressBarConstraint?.let {
            it.visibility = if (isLoading && it.isGone) View.VISIBLE else View.GONE
        }
    }

    fun navigateTo(action: Int, bundle: Bundle = bundleOf()){
        findNavController().navigate(action, bundle)
    }
}