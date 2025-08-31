package com.check.simownerdetailspakistan.ui.navigation

sealed class AppScreen(val route:String){
     object SplashScreen : AppScreen(Routes.splashScreen)
     object DrawerScreen : AppScreen(Routes.drawerScreen)
     object CherSimScreen : AppScreen(Routes.checkSimScreen)
     object ShowResultScreen : AppScreen(Routes.showResultScreen)
}
