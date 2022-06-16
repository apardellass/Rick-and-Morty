package com.albertopardellas.rickandmorty.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.albertopardellas.rickandmorty.model.Result
import com.albertopardellas.rickandmorty.ui.theme.RickAndMortyTheme
import com.albertopardellas.rickandmorty.viewModel.CharacterViewModel
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.flow.Flow

class MainActivity : ComponentActivity() {
    private val characterViewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp, bottom = 8.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharacterList(viewModel = characterViewModel, context = this)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterItem(character: Result, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                bottom = 8.dp, top = 8.dp,
                start = 16.dp, end = 16.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row {
            Surface(
                modifier = Modifier.size(100.dp),
            ) {
                val image = rememberCoilPainter(
                    request = character.image,
                    fadeIn = true
                )
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                character.name?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 22.sp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterList(viewModel: CharacterViewModel, context: Context) {
    CharaterInfoList(characterList = viewModel.characters, context)
}

@Composable
fun CharaterInfoList(
    characterList: Flow<PagingData<Result>>,
    context: Context
) {
    val characterListItems: LazyPagingItems<Result> = characterList.collectAsLazyPagingItems()

    LazyColumn {
        items(characterListItems) { item ->
            item.let {
                it?.let { result ->
                    CharacterItem(character = result, onClick = {
                        Toast.makeText(context, result.id.toString(), Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }

        characterListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    //You can add modifier to manage load state when first time response page is loading
                }
                loadState.append is LoadState.Loading -> {
                    //You can add modifier to manage load state when next response page is loading
                }
                loadState.append is LoadState.Error -> {
                    //You can use modifier to show error message
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RickAndMortyTheme {}
}