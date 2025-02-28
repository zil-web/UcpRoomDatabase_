package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.validasiApp

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            BarangViewModel(
                validasiApp().containerApp.repositorybrg
            )
        }

        initializer {
            HomebrgViewModel (
                validasiApp().containerApp.repositorybrg
            )
        }
        initializer {
            SuplierViewModel(
                validasiApp().containerApp.repositorySplr
            )
        }

        initializer {
            HomesplrViewmodel (
                validasiApp().containerApp.repositorySplr
            )
        }
    }
}

fun CreationExtras.validasiApp() : validasiApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as validasiApp)