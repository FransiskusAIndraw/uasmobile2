package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.thingifiererer.viewmodel.StoryDetailViewModel

@Composable
fun DetailDataScreen(navController: NavHostController, storyId: Long) {
    val viewModel: StoryDetailViewModel = viewModel()
    val story = viewModel.getStory(storyId).collectAsState(initial = null).value

    story?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(it.title, style = MaterialTheme.typography.headlineMedium)
            Text("by ${it.by}", style = MaterialTheme.typography.bodySmall)
            Text("Score: ${it.score}", style = MaterialTheme.typography.bodySmall)
            it.url?.let { url ->
                Text("URL: $url", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
