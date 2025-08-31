package com.check.simownerdetailspakistan.ui.showresult

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.check.simownerdetailspakistan.R
import com.check.simownerdetailspakistan.business.data.respose.RecordDto
import com.check.simownerdetailspakistan.ui.ads.native_banner_quick.NativeAdView
import com.check.simownerdetailspakistan.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ShowResultScreen(navController: NavController) {
    val navBackStackEntry = navController.previousBackStackEntry
    val savedStateHandle = navBackStackEntry?.savedStateHandle
    val query = savedStateHandle?.get<String>("name")
    val records = savedStateHandle?.get<ArrayList<RecordDto>>("userList")
    Surface {
        val context = LocalContext.current
        Scaffold(topBar = {
            TopAppBar(title = { Text(text = context.getString(R.string.app_name), modifier = Modifier.padding(start = 5.dp)) },
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
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "menu Icon")
                    }

                }
            )
        })
        {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(it)
            ) {
                NativeAdView(adUnitId = LocalContext.current.getString(R.string.admob_native_id))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize() // Ensures scroll works
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Green),
                            border = BorderStroke(1.dp, Green)
                        ) {
                            Text(
                                text = "✅ Found ${records?.size} record(s) for \"$query\"",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.padding(15.dp)
                            )
                        }
                    }

                    itemsIndexed(records?: arrayListOf()) { index, record ->
                        RecordCard(record = record, recordNumber = index + 1)
                    }
                }
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun RecordCard(
    record: RecordDto,
    recordNumber: Int
) {
    var isPressed by remember { mutableStateOf(false) }
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 10.dp else 5.dp,
        label = "elevation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        isPressed = false
                    }
                )
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFf8f9fa))
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "📋 Record #$recordNumber",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Green,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            val fields = listOf(
                "👤 Full Name" to record.name,
                "📱 Phone Number" to record.phone,
                "🆔 CNIC" to record.cnic,
                "📍 Address" to record.address
            )

            fields.forEachIndexed { index, (label, value) ->
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "$label:",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Green,
                            modifier = Modifier.weight(0.4f)
                        )
                        Text(
                            text = value?:"",
                            fontSize = 13.sp,
                            color = Color(0xFF333333),
                            modifier = Modifier.weight(0.6f)
                        )
                    }

                    if (index < fields.size - 1) {
                        Divider(
                            color = Green,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}


