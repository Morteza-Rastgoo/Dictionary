@file:OptIn(ExperimentalTextApi::class)

package com.khosousi.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.khosousi.dictionary.feature.search.presentation.MeaningItem
import com.khosousi.dictionary.feature.search.presentation.SearchViewModel
import com.khosousi.dictionary.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                val localCoroutineScope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest {
                        when (it) {
                            is SearchViewModel.UiEvent.ShowSnackBar -> {
                                localCoroutineScope.launch { snackbarHostState.showSnackbar(it.message) }
//                                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT)
//                                    .show()
                            }
                        }
                    }
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    Box(
                        modifier = Modifier
                            .imePadding()
                            .padding(it)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(20.dp),
                                placeholder = {
                                    Text("Search...")
                                }, singleLine = true

                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(viewModel.state.value.wordInfoItems.size) { i ->
                                    val wordInfo = state.wordInfoItems[i]
                                    if (i > 0) {
                                        Spacer(modifier = Modifier.size(10.dp))
                                    }
                                    Text(
                                        wordInfo.phonetic
                                            ?: "",
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            brush = Brush.linearGradient(
                                                colors = listOf(Black, Blue)
                                            )
                                        )
                                    )
                                    wordInfo.meanings?.map {
                                        MeaningItem(
                                            modifier = Modifier,
                                            meaning = it
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
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}