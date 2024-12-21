package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.Suplier
import com.example.ucp2.repository.RepositorySplr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomesplrViewmodel(
    private val repositorySplr: RepositorySplr
) : ViewModel() {

    val HomeUisplrState:StateFlow<HomeUisplrState> = repositorySplr.getAllSplr()
        .filterNotNull()
        .map {
            HomeUisplrState( // Gunakan HomeUisplrState di sini
                listsplr = it.toList(),
                issplrLoading = false,
            )
        }
        .onStart {
            emit(HomeUisplrState(issplrLoading = true)) // Gunakan HomeUisplrState di sini
            delay(900)
        }
        .catch {
            emit(
                HomeUisplrState( // Gunakan HomeUisplrState di sini
                    issplrLoading = false,
                    issplrError = true,
                    errorsplrMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUisplrState( // Gunakan HomeUisplrState di sini
                issplrLoading = true,
            )
        )
}

data class HomeUisplrState(
    val listsplr: List<Suplier> = listOf(),
    val issplrLoading: Boolean = false,
    val issplrError: Boolean = false,
    val errorsplrMessage: String = ""
)