package com.altaf.storyblog.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentHomeBinding
import com.altaf.storyblog.ui.home.adapter.BannerAdapter
import com.altaf.storyblog.ui.home.adapter.CategoryAdapter
import com.altaf.storyblog.ui.home.viewmodel.HomeState
import com.altaf.storyblog.ui.home.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var categoryAdapter: CategoryAdapter
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
        setupCategories()
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

    private fun setupCategories() {
        categoryAdapter = CategoryAdapter()
        binding.rvCategories.apply {
            adapter = categoryAdapter
            setHasFixedSize(true)
        }

        // Handle category item click
        categoryAdapter.onItemClick = { category ->
            // Handle category click
            // You can navigate to a category detail screen or filter stories by category
            showMessage("Selected category: ${category.name}")
        }

        // Handle see all click
        binding.tvSeeAll.setOnClickListener {
            // Handle see all categories click
            showMessage("See all categories clicked")
            // You can navigate to a full categories list screen
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            homeViewModel.uiState.collect { state ->
                when (state) {
                    is HomeState.Success -> {
                        val homeData = state.homeData
                        
                        // Update banners
                        val banners = homeData.banners
                        if (banners.isNotEmpty()) {
                            bannerAdapter.submitList(banners)
                            binding.bannerContainer.visibility = View.VISIBLE
                        } else {
                            binding.bannerContainer.visibility = View.GONE
                        }
                        
                        // Update categories
                        homeData.categories.let { categories ->
                            if (categories.isNotEmpty()) {
                                categoryAdapter.submitList(categories)
                                binding.categoriesCard.visibility = View.VISIBLE
                            } else {
                                binding.categoriesCard.visibility = View.GONE
                            }
                        }
                    }
                    is HomeState.Error -> {
                        // Handle error
                        binding.bannerContainer.visibility = View.GONE
                        binding.categoriesCard.visibility = View.GONE
                        showMessage(state.message)
                    }
                    is HomeState.Loading -> {
                        // Show loading state if needed
                    }
                    is HomeState.Empty -> {
                        binding.bannerContainer.visibility = View.GONE
                        binding.categoriesCard.visibility = View.GONE
                    }
                }
            }
        }
    }
    
    private fun showMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}

