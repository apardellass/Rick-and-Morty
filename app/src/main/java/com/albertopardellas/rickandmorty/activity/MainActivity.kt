package com.albertopardellas.rickandmorty.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albertopardellas.rickandmorty.ui.theme.RickAndMortyTheme
import com.albertopardellas.rickandmorty.ui.view.CharacterLocationView
import com.albertopardellas.rickandmorty.ui.view.MainView
import com.albertopardellas.rickandmorty.viewModel.CharacterViewModel

class MainActivity : ComponentActivity() {
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                BuildNavGraph(navController)
            }
        }
    }

    @Composable
    fun BuildNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable(route = "home") {
                MainView(
                    viewModel = characterViewModel,
                    navController = navController,
                )
            }

            composable(route = "detail/{id}") {
                CharacterLocationView(
                    viewModel = characterViewModel,
                    navController = navController,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyTheme {}
}