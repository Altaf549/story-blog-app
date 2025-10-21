package com.altaf.storyblog.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentSettingBinding
import com.altaf.storyblog.ui.setting.viewmodel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>() {
    override fun getViewModelClass(): Class<SettingViewModel> = SettingViewModel::class.java
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentSettingBinding = FragmentSettingBinding.inflate(inflater, container, false)
}


