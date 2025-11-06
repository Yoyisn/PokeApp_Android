package com.example.pokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
//import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeApp()
        }
    }
}

@Composable
fun PokeApp(viewModel: PokeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(topBar = { TopAppBar(title = { Text("PokeAPI demo") }) }) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
            .fillMaxSize()) {
            Button(onClick = { scope.launch { viewModel.fetchPokemon("pikachu") } }) {
                Text("Load Pikachu")
            }
            state.pokemon?.let {
                Text("Name: ${'$'}{it.name}")
                Text("Height: ${'$'}{it.height}")
            }
            state.error?.let { Text("Error: ${'$'}it") }
        }
    }
}