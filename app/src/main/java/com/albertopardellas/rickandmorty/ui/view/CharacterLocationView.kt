package com.albertopardellas.rickandmorty.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.albertopardellas.rickandmorty.viewModel.CharacterViewModel
import com.google.accompanist.coil.rememberCoilPainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterLocationView(navController: NavController, viewModel: CharacterViewModel) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text("Last known location")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("home")
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
            )
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .padding(
                        bottom = 8.dp, top = 64.dp,
                        start = 16.dp, end = 16.dp
                    ),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        val image = rememberCoilPainter(
                            request = viewModel.selectedCharacter?.image,
                            fadeIn = true
                        )
                        Image(
                            painter = image,
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .width(200.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                    ) {
                        viewModel.selectedCharacter?.name?.let { name ->
                            Text(
                                text = name,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(fontSize = 22.sp),
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                    ) {
                        viewModel.locationDetail.name?.let { name ->
                            Text(
                                text = "Location: $name",
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(fontSize = 18.sp),
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    )
}