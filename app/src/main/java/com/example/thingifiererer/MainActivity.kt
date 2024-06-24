package com.example.thingifiererer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.thingifiererer.di.appModule
import com.example.thingifiererer.ui.layout.NavGraph
import com.example.thingifiererer.ui.theme.ThingifierererTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Koin
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            ThingifierererTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph()
                }
            }
        }
    }
}
