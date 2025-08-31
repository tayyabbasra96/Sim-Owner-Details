package com.check.simownerdetailspakistan.Utils
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.check.simownerdetailspakistan.R

class AdsHandling private constructor() {
    private var context: Activity? = null
    private var mInterstitialAd: InterstitialAd? = null

    companion object {
        private var instance: AdsHandling? = null

        fun getInstance(): AdsHandling {
            if (instance == null) {
                instance = AdsHandling()
            }
            return instance!!
        }
    }

    fun initAds(context1: Activity) {
        context = context1
        InterstitialAdmob(context1)
    }

    private fun InterstitialAdmob(context: Context) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            context,
            context.resources.getString(R.string.admob_interstitial),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    mInterstitialAd = null
                }
            })
    }

    fun showInterstitialAds(activity: Activity) {
        mInterstitialAd?.show(activity)
        InterstitialAdmob(activity)
    }


}