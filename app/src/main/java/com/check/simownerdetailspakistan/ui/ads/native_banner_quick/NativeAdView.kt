package com.check.simownerdetailspakistan.ui.ads.native_banner_quick

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener


@Composable
fun NativeAdView(adUnitId: String) {
    val context = LocalContext.current
    val adLoader = remember { AdLoader.Builder(context, adUnitId) }

    var nativeAd by remember { mutableStateOf<NativeAd?>(null) }

    DisposableEffect(Unit) {
        val adLoadCallback =
            OnNativeAdLoadedListener { ad -> nativeAd = ad }
        //            override fun onNativeAdFailedToLoad(adError: LoadAdError) {
//                Log.e("AdMob", "Native ad failed to load: ${adError.message}")
//            }

        adLoader.forNativeAd(adLoadCallback).build().loadAd(AdRequest.Builder().build())

        onDispose {
            nativeAd?.destroy()
        }
    }

    nativeAd?.let {
        NativeAdViewContent(it)
    }
}

@Composable
fun NativeAdViewContent(nativeAd: NativeAd) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFFB5B3B3))
            .padding(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            nativeAd.images.firstOrNull()?.let { image ->
                Image(
                    painter = rememberImagePainter(image.uri),
                    contentDescription = null,
                            modifier = Modifier
                            .size(50.dp)
                        .padding(start = 8.dp),
                    contentScale = ContentScale.Crop)
            }


            Spacer(modifier = Modifier.width(8.dp))

            // Ad content (Headline and Body)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                // Ad headline
                Text(
                    text = nativeAd.headline ?: "No Headline",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF101010),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Ad body
                Text(
                    text =  nativeAd.body ?: "No Body Text",
                    fontSize = 12.sp,
                    color = Color(0xFF050505),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Ad attribute ("Ad" label)
                Text(
                    text = nativeAd.advertiser?:"Ad" ,
                    fontSize = 10.sp,
                    color = Color(0xFFF8F5F5),
                    modifier = Modifier
                        .background(Color.Gray, shape = RoundedCornerShape(2.dp))
                        .padding(horizontal = 5.dp)
                )
            }

            // Call to action button
            Button(
                onClick = { nativeAd.recordImpression(Bundle()) },
                modifier = Modifier
                    .padding(horizontal = 28.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA0606))
            ) {
                Text(
                    text = nativeAd.callToAction?:"Open" ,
                    fontSize = 14.sp,
                    color = Color(0xFFF6F5F5),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
