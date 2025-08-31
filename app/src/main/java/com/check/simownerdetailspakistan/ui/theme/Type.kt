package com.check.simownerdetailspakistan.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
//val inclusiveSansFontFamily = FontFamily(
//    Font(R.font.inclusive_sans_regular, FontWeight.Normal),
//    Font(R.font.opensans_bold, FontWeight.Bold),
//    Font(R.font.inclusive_sans_italic, FontWeight.Normal, FontStyle.Italic),
//)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge= TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontStyle = FontStyle.Italic,
        fontSize = 48.sp
    )
)

val TextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 14.sp,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight.Normal,
    lineHeight = 20.sp,
    letterSpacing = 0.5.sp,
    color = Green,
    textAlign = TextAlign.Center
)