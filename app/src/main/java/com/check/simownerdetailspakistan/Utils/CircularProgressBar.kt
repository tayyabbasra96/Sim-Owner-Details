package com.check.simownerdetailspakistan.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.check.simownerdetailspakistan.ui.theme.Purple80

@Preview(showBackground = true)
@Composable
fun CircularProgressBar(isBackGround:Boolean=false) {
    Box(modifier = Modifier.fillMaxSize().background(if (isBackGround) Color.White else  Color.Transparent),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp)
            , color = Purple80, strokeWidth = 4.dp
        )
    }
}