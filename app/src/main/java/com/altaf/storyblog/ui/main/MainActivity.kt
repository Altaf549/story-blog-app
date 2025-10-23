package com.altaf.storyblog.ui.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseActivity
import com.altaf.storyblog.common.extension.gone
import com.altaf.storyblog.common.extension.visible
import com.altaf.storyblog.databinding.ActivityMainBinding
import com.altaf.storyblog.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun setupUI() {
        
        // Initialize NavController after the view is created
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.main.post {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            val navController = navHostFragment?.navController ?: return@post
            binding.bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                binding.toolbar.toolbarTitle.text = destination.label
                when (destination.id) {
                    R.id.homeFragment, R.id.categoryFragment, R.id.storyFragment, R.id.settingFragment, R.id.profileFragment-> binding.bottomNav.visible()
                    R.id.categoryWiseStoryFragment -> binding.bottomNav.gone()
                }
            }
        }
    }
}
