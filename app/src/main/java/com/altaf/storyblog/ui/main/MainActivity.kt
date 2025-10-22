package com.altaf.storyblog.ui.main

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseActivity
import com.altaf.storyblog.databinding.ActivityMainBinding
import com.altaf.storyblog.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun setupUI() {
        enableEdgeToEdge()
        setupToolbar()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        
        // Initialize NavController after the view is created
        setupNavigation()
    }
    
    private fun setupToolbar() {
        binding.toolbar.toolbar.let { toolbar ->
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false) // Disable default title
            
            // Set status bar color to match toolbar
            window.statusBarColor = getColor(R.color.colorActionBar)
            
            // Set light status bar for better visibility of icons
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or 
                android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
    
    private fun setupNavigation() {
        binding.main.post {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            val navController = navHostFragment?.navController ?: return@post
            binding.bottomNav.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                binding.toolbar.toolbarTitle.text = destination.label
                /*when (destination.id) {
                    R.id.homeFragment -> updateToolbarTitle("Home")
                    R.id.categoryFragment -> updateToolbarTitle("Search")
                    R.id.storyFragment -> updateToolbarTitle("Add Post")
                    R.id.settingFragment -> updateToolbarTitle("Notifications")
                    R.id.profileFragment -> updateToolbarTitle("Profile")
                }*/
            }
        }
    }
}
