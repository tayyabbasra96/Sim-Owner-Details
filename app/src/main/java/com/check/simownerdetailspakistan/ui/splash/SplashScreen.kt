package com.check.simownerdetailspakistan.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.check.simownerdetailspakistan.R
import com.check.simownerdetailspakistan.ui.navigation.AppScreen
import com.check.simownerdetailspakistan.ui.theme.Green
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Green),
        contentAlignment = Alignment.Center
    ){
        Column(
            Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.icon),
                contentDescription = "splash",
                modifier = Modifier
                    .size(200.dp)
            )
            Text(
                text = "e Bill Checker",
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
    LaunchedEffect(key1 = true ){
        delay(3000)
        navHostController.navigate(AppScreen.DrawerScreen.route){
            popUpTo(AppScreen.SplashScreen.route){
                inclusive=true
            }

        }
    }
}