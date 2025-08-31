package com.check.simownerdetailspakistan.ui.checksim

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.NavController
import com.check.simownerdetailspakistan.R
import com.check.simownerdetailspakistan.Utils.CircularProgressBar
import com.check.simownerdetailspakistan.Utils.CustomAlertDialog
import com.check.simownerdetailspakistan.ui.ads.native_banner_quick.NativeAdView
import com.check.simownerdetailspakistan.ui.main.MainUIState
import com.check.simownerdetailspakistan.ui.main.MainViewModel
import com.check.simownerdetailspakistan.ui.navigation.AppScreen
import com.check.simownerdetailspakistan.ui.theme.Green

var isShowData=true
var refNumber=""
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CheckSimScreen(viewModel:MainViewModel,navController: NavController) {
    var credentials by remember { mutableStateOf(MainViewModel.Credentials()) }
    val mainUIState by viewModel.mainUIState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Header
                HeaderSection()
                // Form Section
                FormSection(viewModel)
                when(mainUIState){
            is MainUIState.Loading->{
                CircularProgressBar(false)
            }
            is MainUIState.Success->{
                val apiResponse= (mainUIState as MainUIState.Success).simData
               if (isShowData){

                   navController.currentBackStackEntry?.savedStateHandle?.apply {
                       set("name", credentials.referenceNumber)
                       set("userList", apiResponse?.data?.records ?: arrayListOf())
                   }
                   navController.navigate(AppScreen.ShowResultScreen.route)
                   isShowData=false

               }

            }
            is MainUIState.Error->{
                CustomAlertDialog(
                    false,
                    "Error",(mainUIState as MainUIState.Error).errorMessage.toString(),
                    onDismiss = {
                        /*do something*/
                    },
                    onExit = {
                        /*do something*/
                    })
            }

            else -> {}
        }


                }
    }

}
fun checkCredentials(creds: MainViewModel.Credentials, context: Context): Boolean {
    return return if (creds.isNotEmpty()) {
        true
    } else {
        Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
        false
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormSection(viewModel: MainViewModel) {
    var credentials by remember { mutableStateOf(MainViewModel.Credentials()) }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.padding(0.dp)
    ) {
        NativeAdView(adUnitId = context.getString(R.string.admob_native_id))
        Text(
            text = "  📱 Enter Phone Number or 🆔 CNIC:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Green,
            modifier = Modifier.padding(bottom = 8.dp, top = 25.dp)
        )

        LoginField(
            value = credentials.referenceNumber,
            onChange = { data -> credentials = credentials.copy(referenceNumber = data) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            {
            if (checkCredentials(credentials, context = context)){
                keyboardController?.hide()
                isShowData=true
                refNumber= credentials.referenceNumber
                viewModel.getSimOwnerDetailData(credentials.referenceNumber)
            }
            },
            enabled = credentials.isNotEmpty(),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .background(Green),

            ) {
            Text(text =  "🔍 Search Records"
                , color = Color.White)
        }
    }
}
@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Green,
                        Green
                    )
                )
            )
            .padding(20.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "🔍 SIM Owner Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Search by Phone Number or CNIC",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginField(
    value: String = "",
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "e.g., 03485141552 or 3420212345678"
) {

    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier.padding(horizontal = 16.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder, color = Color.LightGray) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Green,
            unfocusedBorderColor = Color.LightGray,
            unfocusedLabelColor =Color.LightGray,
            focusedLabelColor = Green,
            containerColor = Color.White,
            textColor = Green
        )

    )
}

