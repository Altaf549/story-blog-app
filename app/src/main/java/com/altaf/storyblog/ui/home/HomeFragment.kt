package com.altaf.storyblog.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentHomeBinding
import com.altaf.storyblog.ui.home.adapter.BannerAdapter
import com.altaf.storyblog.ui.home.viewmodel.HomeState
import com.altaf.storyblog.ui.home.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var bannerAdapter: BannerAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = homeViewModel
        setupBannerSlider()
        observeViewModel()
    }

    private fun setupBannerSlider() {
        bannerAdapter = BannerAdapter()
        binding.viewPagerBanner.apply {
            adapter = bannerAdapter
            offscreenPageLimit = 1
            
            // Add a PageTransformer that translates the next and previous items horizontally
            val nextItemVisiblePx = resources.getDimension(com.intuit.sdp.R.dimen._10sdp)
            val currentItemHorizontalMarginPx = resources.getDimension(com.intuit.sdp.R.dimen._16sdp)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            
            setPageTransformer { page, position ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
                page.scaleX = 1 - (0.25f * kotlin.math.abs(position))
                page.alpha = 0.25f + (1 - kotlin.math.abs(position))
            }
        }

        // Set up TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayoutBanner, binding.viewPagerBanner) { tab, _ ->
            // We don't need to set any text or icon for the tabs
        }.attach()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            homeViewModel.uiState.collect { state ->
                when (state) {
                    is HomeState.Success -> {
                        val banners = state.homeData.banners
                        if (banners.isNotEmpty()) {
                            bannerAdapter.submitList(banners)
                            binding.bannerContainer.visibility = View.VISIBLE
                        } else {
                            binding.bannerContainer.visibility = View.GONE
                        }
                    }
                    is HomeState.Error -> {
                        // Handle error
                        binding.bannerContainer.visibility = View.GONE
                    }
                    is HomeState.Loading -> {
                        // Show loading state if needed
                    }
                    is HomeState.Empty -> {
                        binding.bannerContainer.visibility = View.GONE
                    }
                }
            }
        }
    }
}

