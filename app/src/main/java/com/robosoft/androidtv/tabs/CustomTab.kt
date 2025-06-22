package com.robosoft.androidtv.tabs

import android.content.Intent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Tab
import androidx.tv.material3.TabRow
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.robosoft.androidtv.catalog.screens.ImmersiveListScreen
import com.robosoft.androidtv.screens.profile.TVProfileScreen
import com.robosoft.androidtv.screens.video.VideoPlayerActivity

val tabs = listOf(
    "Home",
    "Search", "Movies", "Shows", "Library", "Settings"
)

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CustomTabsRow() {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == selectedTabIndex,
                    onFocus = { selectedTabIndex = index },
                ) {
                    Text(
                        text = tab,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 26.dp, vertical = 15.dp)
                    )
                }
            }
        }
    }
    TabPanels(selectedTabIndex = selectedTabIndex)
}

@Composable
private fun TabPanels(selectedTabIndex: Int) {
    AnimatedContent(targetState = selectedTabIndex, label = "") {
        when (it) {
            getTabIndex("Search") -> {
                Column(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("SearchScreen", modifier = Modifier.padding(top = 50.dp))
                }
            }

            getTabIndex("Home") -> {
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImmersiveListScreen()
                }
            }

            getTabIndex("Movies"), getTabIndex("Shows") -> {
                Column(
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .fillMaxSize()
                ) {
                    DisplayCustomGridItems()
                }
            }

            getTabIndex("Library") -> {

            }

            getTabIndex("Settings") -> {
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TVProfileScreen()
                }
            }
        }
    }
}

private fun getTabIndex(text: String): Int {
    return tabs.indexOf(text)
}

@Composable
fun DisplayCustomGridItems() {
    val imageUrls = listOf(
        "https://loremflickr.com/300/200/movie",
        "https://www.gstatic.com/webp/gallery/2.jpg",
        "https://www.gstatic.com/webp/gallery/3.jpg",
        "https://www.gstatic.com/webp/gallery/4.jpg",
        "https://www.gstatic.com/webp/gallery/5.jpg",
        "https://www.gstatic.com/webp/gallery/6.jpg",
        "https://www.gstatic.com/webp/gallery/5.jpg",
        "https://www.gstatic.com/webp/gallery/4.jpg",
        "https://www.gstatic.com/webp/gallery/3.jpg",
        "https://www.gstatic.com/webp/gallery/2.jpg",
        "https://www.gstatic.com/webp/gallery/1.jpg",
        "https://www.gstatic.com/webp/gallery/1.jpg",
        "https://www.gstatic.com/webp/gallery/2.jpg",
        "https://www.gstatic.com/webp/gallery/3.jpg",
        "https://www.gstatic.com/webp/gallery/4.jpg",
        "https://www.gstatic.com/webp/gallery/5.jpg",
        "https://www.gstatic.com/webp/gallery/4.jpg",
        "https://www.gstatic.com/webp/gallery/3.jpg",
        "https://www.gstatic.com/webp/gallery/2.jpg",
        "https://www.gstatic.com/webp/gallery/1.jpg",
        "https://www.gstatic.com/webp/gallery/5.jpg",
        "https://www.gstatic.com/webp/gallery/2.jpg",

        )
    TvLazyVerticalGrid(
        columns = TvGridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(70.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(imageUrls.size) { index ->
            FocusableImageCard(imageUrl = imageUrls[index])
        }
    }
}

@Composable
fun FocusableImageCard(imageUrl: String) {
    val context = LocalContext.current

    Card(
        onClick = {
            val intent = Intent(context, VideoPlayerActivity::class.java).apply {
                putExtra("video_url", "https://samplelib.com/lib/preview/mp4/sample-5s.mp4")
            }
            context.startActivity(intent)
        },
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
    ) {
        val context = LocalContext.current
        val imageRequest = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .allowHardware(false)
            .build()

        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            onError = { println("Image load error: ${it.result.throwable}") },
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}