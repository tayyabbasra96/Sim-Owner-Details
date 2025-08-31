package com.check.simownerdetailspakistan.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.check.simownerdetailspakistan.ui.theme.Green


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CustomAlertDialog(
    isDisclaimer:Boolean=false,
    title: String="",
    description: String = "",
    onDismiss: () -> Unit,
    onExit: () -> Unit
) {
    var showDialog by remember {
        mutableStateOf(true)
    }

    if (showDialog){
        Column(modifier =  Modifier.wrapContentSize()) {
            Dialog(
                onDismissRequest = { onDismiss()
                    showDialog = false
                }, properties = DialogProperties(
                    dismissOnBackPress = false, dismissOnClickOutside = false
                )
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                        .height(IntrinsicSize.Min),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier
                                .padding(8.dp, 16.dp, 8.dp, 2.dp)
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(), fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Green

                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = description,
                            modifier = Modifier
                                .padding(8.dp, 2.dp, 8.dp, 16.dp)
                                .align(Alignment.CenterHorizontally)
                                .fillMaxWidth(),
                            textAlign = if (isDisclaimer) TextAlign.Start else TextAlign.Center,
                            color = Green,
                            fontSize =if (isDisclaimer) TextUnit(12f, TextUnitType.Sp) else TextUnit(16f, TextUnitType.Sp),

                            )
                        Divider(
                            color = Green, modifier = Modifier
                                .fillMaxWidth()
                                .width(1.dp)
                        )
                        Row(Modifier.padding(top = 0.dp)) {
                            CompositionLocalProvider(
                                LocalMinimumTouchTargetEnforcement provides false,
                            ) {
                                TextButton(
                                    onClick = { onDismiss()
                                              showDialog=false},
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp)
                                        .weight(1F)
                                        .border(0.dp, Color.Transparent)
                                        .height(48.dp),
                                    elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp),
                                    shape = RoundedCornerShape(0.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(text = "Cancel", color = Green)
                                }
                            }
                            Divider(
                                color = Green, modifier =
                                Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            CompositionLocalProvider(
                                LocalMinimumTouchTargetEnforcement provides false,
                            ) {
                                TextButton(
                                    onClick = {
                                        showDialog=false
                                        onExit.invoke()
                                    },
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp)
                                        .weight(1F)
                                        .border(0.dp, color = Color.Transparent)
                                        .height(48.dp),
                                    elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp),
                                    shape = RoundedCornerShape(0.dp),
                                    contentPadding = PaddingValues()
                                ) {
                                    Text(text = "Ok", color = Green)
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}


