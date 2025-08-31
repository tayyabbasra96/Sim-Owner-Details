package com.check.simownerdetailspakistan.Utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.webkit.WebView
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream


object Constant {
    var BaseURL="https://echallanpunjab.com"

    fun saveBitmapToCache(context: Context,bitmap: Bitmap): Uri {
        val cacheDir = context.externalCacheDir ?: context.cacheDir
        val file = File(cacheDir, "shared_image.png")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream.close()

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    fun captureWebViewContent(webView: WebView,density: Float):Bitmap {
        val scale = density.toInt()
        val width = (webView.width * scale).toFloat()
        val height = (webView.height * scale).toFloat()

        val bitmap = Bitmap.createBitmap(width.toInt(), height.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        webView.draw(canvas)

        return bitmap
    }

    fun shareApp(context: Context){
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "Sim Owner Details App")
            var sAux =
                "Here is the platform where you will get all information about sim owner details. \nJoin the link to install the app\n"
            sAux = sAux + "https://play.google.com/store/apps/details?id=" +context.packageName
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            context.startActivity(Intent.createChooser(i, "choose one"))
        } catch (e: Exception) {
        }
    }
    fun rateUsApp(context: Context){
        try {
            context.startActivity(
                Intent(
                    "android.intent.action.VIEW",
                    Uri.parse("market://details?id=" + context.packageName)
                )
            )
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    "android.intent.action.VIEW",
                    Uri.parse("https://play.google.com/store/apps/details?id=" + context.packageName)
                )
            )
        }
    }


}