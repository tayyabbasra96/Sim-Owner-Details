package com.check.simownerdetailspakistan.ui.drawer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.check.simownerdetailspakistan.R
import com.check.simownerdetailspakistan.business.domain.model.DrawerItems
import com.check.simownerdetailspakistan.ui.ads.native_banner_quick.NativeAdView
import com.check.simownerdetailspakistan.ui.home.HomeScreen
import com.check.simownerdetailspakistan.ui.theme.Green
import com.check.simownerdetailspakistan.Utils.Constant.rateUsApp
import com.check.simownerdetailspakistan.Utils.Constant.shareApp
import com.check.simownerdetailspakistan.Utils.CustomAlertDialog
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DrawerScreen(navController: NavController) {
    val context = LocalContext.current

    val drawerItem = listOf(
        DrawerItems(Icons.Default.Home, "Home", 0, false),
        DrawerItems(Icons.Filled.Info, "Disclaimer", 0, true),
    )
    val drawerItem2 = listOf(
        DrawerItems(Icons.Default.Share, "Share", 0, false),
        DrawerItems(Icons.Filled.Star, "Rate", 0, false)
    )
    var selectedItem by remember {
        mutableStateOf(drawerItem[0])
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {

            Column(Modifier.wrapContentSize(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Green),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        Modifier.wrapContentSize(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.drawable.icon),
                            contentDescription = "profile pic",
                            modifier = Modifier
                                .size(130.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = context.getString(R.string.app_name),
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            color = Color.White,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Divider(
                        Modifier.align(Alignment.BottomCenter), thickness = 1.dp,
                        Color.DarkGray
                    )

                }
                drawerItem.forEach {
                    NavigationDrawerItem(label = { Text(text = it.text) },
                        selected = it == selectedItem,
                        onClick = {
                            selectedItem = it
                            scope.launch {
                                drawerState.close()
                            }

                        },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        icon = {
                            Icon(imageVector = it.icon, contentDescription = it.text)
                        }
                    )
                }
                Divider(
                    thickness = 1.dp,
                    color = Color.DarkGray
                )
                drawerItem2.forEach {
                    NavigationDrawerItem(label = { Text(text = it.text) },
                        selected = it == selectedItem,
                        onClick = {
                            selectedItem = it

                            scope.launch {
                                drawerState.close()
                            }
                            if (selectedItem.text=="Share"){
                                shareApp(context = context)
                            }
                            else if (selectedItem.text=="Rate"){
                                rateUsApp(context = context)
                            }

                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                        , icon = {
                            Icon(imageVector = it.icon, contentDescription = it.text)
                        }
                    )
                }
            }
        }
    }, drawerState = drawerState) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = context.getString(R.string.app_name)) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = Green,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White,
                        scrolledContainerColor = Color.White


                    ),
                    modifier = Modifier.background(Green),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu Icon")
                        }

                    }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier.wrapContentSize(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    NativeAdView(adUnitId = LocalContext.current.getString(R.string.admob_native_id))
                    HomeScreen(navController)

                }
            }
        }


    }
    if (selectedItem.text=="Disclaimer") {
        CustomAlertDialog(
            true,
            title = "Disclaimer",
            description = context.getString(R.string.disclaimer),
            onDismiss = {
                /*do something*/
            },
            onExit = {
                /*do something*/
            })


    }


}


