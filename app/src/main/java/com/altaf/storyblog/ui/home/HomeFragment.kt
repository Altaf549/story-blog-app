package com.altaf.storyblog.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavOptions
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.common.extension.gone
import com.altaf.storyblog.common.extension.visible
import com.altaf.storyblog.databinding.FragmentHomeBinding
import com.altaf.storyblog.ui.home.adapter.BannerAdapter
import com.altaf.storyblog.ui.adapter.CategoryAdapter
import com.altaf.storyblog.ui.adapter.StoryAdapter
import com.altaf.storyblog.ui.home.viewmodel.HomeEvent
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
    private lateinit var storyAdapter: StoryAdapter

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupBannerSlider()
        setupCategories()
        setupStories()
        observeViewModel()
    }
    
    override fun onStart() {
        super.onStart()
        observeEvents()
    }
    
    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is HomeEvent.NavigateToCategoryWiseStory -> navigateToCategoryWiseStory()
                    is HomeEvent.NavigateToCategory -> navigateToCategory()
                    is HomeEvent.NavigateToSingleStory -> navigateToSingleStory()
                    is HomeEvent.NavigateToStory -> navigateToStory()
                    else -> {}
                }
            }
        }
    }

    private fun navigateToCategory() {
        // Navigate to category fragment with proper back stack handling
        findNavController().navigate(
            R.id.categoryFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, false) // Keep home in back stack
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .build()
        )
    }
    
    private fun navigateToCategoryWiseStory() {
        // Navigate to category wise story with proper back stack handling
        findNavController().navigate(
            R.id.categoryWiseStoryFragment,
            null,
            NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .build()
        )
    }

    private fun navigateToStory() {
        // Navigate to category fragment with proper back stack handling
        findNavController().navigate(
            R.id.storyFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, false) // Keep home in back stack
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .build()
        )
    }

    private fun navigateToSingleStory() {
        // Navigate to category wise story with proper back stack handling
        findNavController().navigate(
            R.id.singleStoryFragment,
            null,
            NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .build()
        )
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
            viewModel.onCategoriesClicked()
        }
    }

    private fun setupStories() {
        storyAdapter = StoryAdapter()
        
        // Initialize RecyclerView
        binding.rvStories.apply {
            adapter = storyAdapter
            setHasFixedSize(true)
        }
        // Handle story item clicks
        storyAdapter.onItemClick = { story ->
            viewModel.onStoryClicked()
        }

        storyAdapter.onReadMoreClick = { story ->
            viewModel.onStoryClicked()
        }
    }

    private fun observeViewModel() {
        // Observe UI state
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is HomeState.Success -> {
                        val homeData = state.homeData
                        
                        // Update banners
                        val banners = homeData.banners
                        if (banners.isNotEmpty()) {
                            bannerAdapter.submitList(banners)
                            binding.bannerContainer.visible()
                        } else {
                            binding.bannerContainer.gone()
                        }
                        
                        // Update categories
                        val categories = homeData.categories
                        if (categories.isNotEmpty()) {
                            categoryAdapter.submitList(categories)
                            binding.categoriesCard.visible()
                        } else {
                            binding.categoriesCard.gone()
                        }
                        
                        // Update stories
                        val stories = homeData.stories
                        if (stories.isNotEmpty()) {
                            binding.storiesCard.visible()
                            storyAdapter.submitList(stories) {
                                binding.rvStories.post {
                                    binding.rvStories.invalidateItemDecorations()
                                    binding.rvStories.requestLayout()
                                }
                            }
                        } else {
                            binding.storiesCard.gone()
                        }
                    }
                    is HomeState.Error -> {
                        // Handle error
                        binding.bannerContainer.gone()
                        binding.categoriesCard.gone()
                        binding.storiesCard.gone()
                        showMessage(state.message)
                    }
                    is HomeState.Loading -> {
                        // Show loading state if needed
                    }
                    is HomeState.Empty -> {
                        binding.bannerContainer.gone()
                        binding.categoriesCard.gone()
                        binding.storiesCard.gone()
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

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearEvent()
    }
}

