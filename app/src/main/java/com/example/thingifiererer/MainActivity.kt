package com.example.thingifiererer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.thingifiererer.ui.layout.NavGraph
import com.example.thingifiererer.ui.theme.ThingifierererTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThingifierererTheme {
                NavGraph()
            }
        }
    }
}
