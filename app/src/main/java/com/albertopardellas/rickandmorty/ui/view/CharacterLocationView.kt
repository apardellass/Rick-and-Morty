package com.albertopardellas.rickandmorty.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.albertopardellas.rickandmorty.R
import com.albertopardellas.rickandmorty.viewModel.CharacterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterLocationView(navController: NavController, viewModel: CharacterViewModel) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
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
                    .fillMaxSize()
                    .padding(top = 64.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Text("Detail view")
            }
        }
    )
}