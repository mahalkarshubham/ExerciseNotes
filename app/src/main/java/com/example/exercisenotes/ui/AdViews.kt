package com.example.exercisenotes.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(adId: String) {
    val context = ContextAmbient.current
    val customView = remember { AdView(context) }
    AndroidView(viewBlock = { customView }) { view ->
        view.apply {
            adUnitId = adId
            adSize = AdSize.BANNER
            loadAd(AdRequest.Builder().build())
        }
    }
}