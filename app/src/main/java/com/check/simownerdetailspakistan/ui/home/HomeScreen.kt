package com.check.simownerdetailspakistan.ui.home

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.check.simownerdetailspakistan.R
import com.check.simownerdetailspakistan.Utils.AdsHandling
import com.check.simownerdetailspakistan.ui.theme.Green
import com.check.simownerdetailspakistan.business.domain.model.ItemList
import com.check.simownerdetailspakistan.ui.checksim.CheckSimScreen
import com.check.simownerdetailspakistan.ui.main.MainViewModel
import com.check.simownerdetailspakistan.ui.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavController) {
    var itemList: ArrayList<ItemList> = arrayListOf()
    itemList.add(ItemList(1,"Check by Phone Number",R.drawable.icon,""))
    itemList.add(ItemList(2,"Check by CNIC",R.drawable.icon,""))
    val activity = (LocalContext.current as? Activity)
    val lazyGridState = rememberLazyGridState()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 24.dp, top = 24.dp, start = 24.dp, end = 24.dp),
        state = lazyGridState
    ) {
        items(itemList.size,
            span =
            { item ->
                GridItemSpan(1)
            }) { item ->
            Card(shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .height(190.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .shadow(0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
                , onClick = {
                    navController.navigate(AppScreen.CherSimScreen.route) {
                        popUpTo(AppScreen.CherSimScreen.route) { inclusive = false }
                        launchSingleTop = true
                    }
//                    activity?.let {
//                        AdsHandling.getInstance().showInterstitialAds(activity = activity)
//
//                    }

                }
            ) {
                Column( verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier= Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 10.dp)
                        .verticalScroll(rememberScrollState())

                )
                {
                    AsyncImage(
                        model =
                        itemList[item].icon,
                        contentDescription = "Type",
                        placeholder = painterResource(id = R.drawable.icon)
                        , modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .align(Alignment.CenterHorizontally) )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = itemList[item].tittle.substring(0,1).uppercase()+itemList[item].tittle.substring(1).uppercase(), modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize()
                            .padding(end = 12.dp, start = 12.dp),
                        color= Green,
                        fontSize = TextUnit(16f, TextUnitType.Sp),
                        fontWeight = FontWeight.Normal,textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                }

            }
        }
    }
}
