package com.check.simownerdetailspakistan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.check.simownerdetailspakistan.ui.checksim.CheckSimScreen
import com.check.simownerdetailspakistan.ui.drawer.DrawerScreen
import com.check.simownerdetailspakistan.ui.main.MainViewModel
import com.check.simownerdetailspakistan.ui.showresult.ShowResultScreen
import com.check.simownerdetailspakistan.ui.splash.SplashScreen

@Composable
fun NavGraph(viewModel: MainViewModel,navController:NavHostController= rememberNavController()) {

    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.DrawerScreen.route) {
            DrawerScreen(navController)
        }
        composable(AppScreen.CherSimScreen.route) {
            CheckSimScreen(viewModel,navController)
        }
        composable(AppScreen.ShowResultScreen.route) {
            ShowResultScreen(navController)
        }
    }


}