package com.example.pokeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.model.Pokemon
import com.example.pokeapp.network.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(val pokemon: Pokemon? = null, val error: String? = null)

class PokeViewModel : ViewModel() {
    private val repo = PokeRepository(NetworkModule.createService())

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val p = repo.getPokemon(name)
                _uiState.value = UiState(pokemon = p)
            } catch (e: Exception) {
                _uiState.value = UiState(error = e.message)
            }
        }
    }
}
