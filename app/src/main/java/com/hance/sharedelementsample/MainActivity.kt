@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.hance.sharedelementsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.hance.sharedelementsample.ui.theme.SharedElementSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val showDetails = remember {
                mutableStateOf(false)
            }
            SharedElementSampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(30) {
                                Greeting(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .background(Color(0xFF00BCD4), RoundedCornerShape(0.2f)),
                                    name = "baby"
                                )
                            }
                        }
                        SharedTransitionLayout(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            AnimatedContent(
                                targetState = showDetails.value,
                                label = "shared_elements"
                            ) { targetState ->
                                if (targetState) {

                                } else {
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(CircleShape)
                                            .background(Color.Red)
                                            .align(Alignment.BottomEnd)
                                            .clickable {
                                                showDetails.value = !showDetails.value
                                            },
                                        model = Cat,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

const val Cat =
    "https://images.mypetlife.co.kr/content/uploads/2023/11/18161317/d6c08aa5-dc1c-46a1-97bb-6782641c1624.jpeg"

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Detail(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SharedElementSampleTheme {
        Greeting("Android")
    }
}