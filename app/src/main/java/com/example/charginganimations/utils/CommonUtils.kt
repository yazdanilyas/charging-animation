package com.example.charginganimations.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.charginganimations.BuildConfig

object CommonUtils {
    fun shareApp(context: Context) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Auto Clicker")
        var shareMessage = "\nHi there, I found a beautiful Charging animation app, try it\n\n"
        shareMessage = """
            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
            
            
            """.trimIndent()
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        context.startActivity(Intent.createChooser(shareIntent, "choose one"))
    }

    fun rateApp(context: Context) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)
            )
        )
    }

    fun moreApp(context: Context) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/developer?id=exleno")
            )
        )

    }

    fun openPrivacyPolicy(context: Context) {
        val url = "https://sites.google.com/site/exlenoprivacypolicy/exleno-privacy-policy"
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        // Verify that the intent will resolve to an activity
        // Verify that the intent will resolve to an activity

        // Here we use an intent without a Chooser unlike the next example
        context.startActivity(intent)
//        } else {
//            Toast.makeText(context, "No app found to open the link.", Toast.LENGTH_SHORT)
//                .show()
//        }
    }
}