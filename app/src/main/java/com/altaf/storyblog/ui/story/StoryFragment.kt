package com.altaf.storyblog.ui.story

import android.view.LayoutInflater
import android.view.ViewGroup
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentHomeBinding
import com.altaf.storyblog.databinding.FragmentStoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryFragment : BaseFragment<StoryViewModel, FragmentStoryBinding>() {
    override fun getViewModelClass(): Class<StoryViewModel> = StoryViewModel::class.java
    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentStoryBinding = FragmentStoryBinding.inflate(inflater, container, false)
}


