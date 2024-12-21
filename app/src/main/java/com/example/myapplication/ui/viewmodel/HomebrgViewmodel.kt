package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.Barang
import com.example.ucp2.repository.Repositorybrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomebrgViewModel(
    private val repositorybrg: Repositorybrg
) : ViewModel() {

    val homeUIbrgState:StateFlow<HomeUibrgState> = repositorybrg.getAllbrg()
        .filterNotNull()
        .map {
            HomeUibrgState(
                listbrg = it.toList(),
                isbrgLoading = false,
            )
        }
        .onStart {
            emit(HomeUibrgState(isbrgLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUibrgState(
                    isbrgLoading = false,
                    isbrgError = true,
                    errorbrgMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUibrgState(
                isbrgLoading = true,
            )
        )
}

data class HomeUibrgState(
    val listbrg: List<Barang> = listOf(),
    val isbrgLoading: Boolean = false,
    val isbrgError: Boolean = false,
    val errorbrgMessage: String = ""
)