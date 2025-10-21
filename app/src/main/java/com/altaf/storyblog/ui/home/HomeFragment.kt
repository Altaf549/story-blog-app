package com.altaf.storyblog.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentHomeBinding
import com.altaf.storyblog.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
}

