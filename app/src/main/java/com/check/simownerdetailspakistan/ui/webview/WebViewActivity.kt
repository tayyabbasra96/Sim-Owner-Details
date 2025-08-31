package com.check.simownerdetailspakistan.ui.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.check.simownerdetailspakistan.R
import com.check.simownerdetailspakistan.Utils.CircularProgressBar
import com.check.simownerdetailspakistan.Utils.Constant
import com.check.simownerdetailspakistan.ui.theme.BillCheckerTheme
import com.check.simownerdetailspakistan.ui.theme.Green


class WebViewActivity : ComponentActivity() {
    var name = ""
    var url = ""
    var type = ""
    var printWeb: WebView? = null
    var printJob: PrintJob? = null
    var density: Float = 0f
    // a boolean to check the status of printing
    var printBtnPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = intent.extras?.getString("name", "") ?: ""
        url = intent.extras?.getString("url", "") ?: ""
        type= intent.extras?.getString("type","") ?:""

        setContent {
            BillCheckerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {

                    WebViewComponent(name, url)
                }
            }
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    fun WebViewComponent(name: String = "", url: String = "") {
        Surface {
            val context = LocalContext.current
            Scaffold(topBar = {
                TopAppBar(title = {
                    Text(
                        text = name.substring(0, 1).uppercase() + name.substring(1).uppercase(),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                },
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
                            finish()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "menu Icon"
                            )
                        }

                    }, actions = {
                        IconButton(onClick = {
                            if (type=="api"){
                                if (printWeb != null) {
                                    // Calling createWebPrintJob()
                                    PrintTheWebPage(printWeb!!);
                                } else {
                                    // Showing Toast message to user
                                    Toast.makeText(
                                        context,
                                        "WebPage not fully loaded",
                                        Toast.LENGTH_SHORT
                                    ).show();
                                }
                            }


                        }) {
                            Icon(painter = painterResource(R.drawable.printing) , modifier = Modifier.height(24.dp).width(24.dp), contentDescription = "Print PDF", tint = Color.White)
                        }

                        IconButton(onClick = {
                            if (type=="api") {
                                val bitmap = Constant.captureWebViewContent(printWeb!!, density)
                                val uri = Constant.saveBitmapToCache(context, bitmap)

                                val intent = android.content.Intent(Intent.ACTION_SEND).apply {
                                    type = "image/*"
                                    putExtra(Intent.EXTRA_STREAM, uri)
                                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                }
                                context.startActivity(
                                    Intent.createChooser(
                                        intent,
                                        "Share WebView Content"
                                    )
                                )
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Share, contentDescription = "Share PDF" )
                        }
                    }
                )
            })
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize()
                        .padding(it)
                ) {
                    WebViewComponent(url = url)
                }

            }

        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    fun WebViewComponent(url: String) {
        var isLoading by remember {
            mutableStateOf(true)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            AndroidView(modifier = Modifier
                .fillMaxSize(), factory = { context ->

                WebView(context).apply {
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(
                            view: WebView?,
                            url: String?,
                            favicon: Bitmap?
                        ) {
                            super.onPageStarted(view, url, favicon)
                            isLoading = true
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            printWeb = view

                            isLoading = false

                        }
                    }

                    loadUrl(url)
                    settings.javaScriptEnabled = true
                    settings.databaseEnabled = true
                    settings.saveFormData = true
                    settings.allowContentAccess = true
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = false
                    settings.loadsImagesAutomatically = true
                    settings.setSupportZoom(true)
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.loadsImagesAutomatically = true
                    isClickable = true
                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
                    settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                    webChromeClient = WebChromeClient()
                }
            })
            density=LocalDensity.current.density

            if (isLoading) {
                CircularProgressBar(false)

            }
        }


    }



    private fun PrintTheWebPage(webView: WebView) {

        // set printBtnPressed true
        printBtnPressed = true
        // Creating  PrintManager instance
        val printManager = this
            .getSystemService(PRINT_SERVICE) as PrintManager

        // setting the name of job
        val jobName = "Online Duplicate Bill" + webView.url

        // Creating  PrintDocumentAdapter instance
        val printAdapter = webView.createPrintDocumentAdapter(jobName)
        assert(printManager != null)
        printJob = printManager.print(
            jobName, printAdapter,
            PrintAttributes.Builder().build()
        )
    }

    override fun onResume() {
        super.onResume()
        if (printJob != null && printBtnPressed) {
            if (printJob!!.isCompleted) {
                // Showing Toast Message
                Toast.makeText(this, "Completed", Toast.LENGTH_SHORT).show();
            } else if (printJob!!.isStarted) {
                // Showing Toast Message
                Toast.makeText(this, "isStarted", Toast.LENGTH_SHORT).show();

            } else if (printJob!!.isBlocked) {
                // Showing Toast Message
                Toast.makeText(this, "isBlocked", Toast.LENGTH_SHORT).show();

            } else if (printJob!!.isCancelled) {
                // Showing Toast Message
                Toast.makeText(this, "isCancelled", Toast.LENGTH_SHORT).show();

            } else if (printJob!!.isFailed) {
                // Showing Toast Message
                Toast.makeText(this, "isFailed", Toast.LENGTH_SHORT).show();

            } else if (printJob!!.isQueued) {
                // Showing Toast Message
                Toast.makeText(this, "isQueued", Toast.LENGTH_SHORT).show();

            }
            // set printBtnPressed false
            printBtnPressed = false;
        }
    }
}