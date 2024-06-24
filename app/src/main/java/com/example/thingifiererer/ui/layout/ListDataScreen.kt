package com.example.thingifiererer.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thingifiererer.model.Story
import com.example.thingifiererer.viewmodel.StoriesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListDataScreen(navController: NavHostController) {
    val viewModel: StoriesViewModel = koinViewModel()
    val stories = viewModel.stories.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(stories.value) { story ->
            StoryItem(story, navController)
        }
    }
}

@Composable
fun StoryItem(story: Story, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/${story.id}")
            }
            .padding(8.dp)
    ) {
        Text(story.title, style = MaterialTheme.typography.titleLarge)
        Text("by ${story.by}", style = MaterialTheme.typography.bodySmall)
        Text("Score: ${story.score}", style = MaterialTheme.typography.bodySmall)
    }
}
