package com.altaf.storyblog.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentHomeBinding
import com.altaf.storyblog.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override fun getViewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)
}


