package com.beepbeep

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            this.window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            this.window.navigationBarColor =
                ContextCompat.getColor(this, android.R.color.transparent)
            MainView()
        }
    }
}