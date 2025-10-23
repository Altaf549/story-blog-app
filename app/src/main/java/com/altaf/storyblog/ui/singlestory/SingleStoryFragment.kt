package com.altaf.storyblog.ui.singlestory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentSingleStoryBinding
import com.altaf.storyblog.ui.singlestory.viewmodel.SingleStoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleStoryFragment : BaseFragment<SingleStoryViewModel, FragmentSingleStoryBinding>() {

    override fun getViewModelClass(): Class<SingleStoryViewModel> = SingleStoryViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSingleStoryBinding = FragmentSingleStoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        

    }
}
