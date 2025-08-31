package com.check.simownerdetailspakistan.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.check.simownerdetailspakistan.Utils.AdsHandling
import com.check.simownerdetailspakistan.Utils.AppOpenManager
import com.check.simownerdetailspakistan.ui.navigation.NavGraph
import com.check.simownerdetailspakistan.ui.theme.BillCheckerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var appOpenManager: AppOpenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AdsHandling.getInstance().initAds(this)
        appOpenManager = AppOpenManager(application)
        setContent {
            LaunchedEffect(Unit) {
                appOpenManager.showAdIfAvailable()
            }
            BillCheckerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    NavGraph(viewModel)
                }
            }
        }


    }

}
