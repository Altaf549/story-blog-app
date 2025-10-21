package com.altaf.storyblog.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding> : Fragment() {

    private var _binding: DB? = null
    protected val binding get() = _binding!!
    protected lateinit var viewModel: VM

    /** Must return your ViewModel class */
    protected abstract fun getViewModelClass(): Class<VM>

    /** Must provide your binding inflater */
    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): DB

    protected open fun setupUI() {}
    protected open fun setupObservers() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(inflater, container)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        setupUI()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
