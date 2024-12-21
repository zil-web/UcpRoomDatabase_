package com.example.myapplication.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.myapplication.data.entity.Suplier
import com.example.ucp2.repository.RepositorySplr
import kotlinx.coroutines.launch

class SuplierViewModel (private val repositorySplr: RepositorySplr) : ViewModel(){
    var uisplrState by mutableStateOf(splrUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(suplierEvent: SuplierEvent) {
        uisplrState = uisplrState.copy(
            suplierEvent = suplierEvent,

            )
    }

    //Validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uisplrState.suplierEvent
        val errorState =  FormErrorsplrState(
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong"
        )

        uisplrState = uisplrState.copy(issplrEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data ke repository
    fun saveData(){
        val currentEvent = uisplrState.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySplr.insertSplr(currentEvent.toSuplierEntity())
                    uisplrState = uisplrState.copy(
                        snackDsnBarMessage = "Data Berhasil disimpan",
                        suplierEvent = SuplierEvent(), // Reset input form
                        issplrEntryValid = FormErrorsplrState(), // Reset error state
                    )
                } catch (e: Exception) {
                    uisplrState = uisplrState.copy(
                        snackDsnBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uisplrState = uisplrState.copy(
                snackDsnBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }

    // Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uisplrState = uisplrState.copy(snackDsnBarMessage = null)
    }
}



data class splrUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val issplrEntryValid: FormErrorsplrState = FormErrorsplrState(),
    val snackDsnBarMessage: String? = null,
)

data class FormErrorsplrState(
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isValid(): Boolean {
        return nama == null && kontak == null && kontak == null && alamat == null
    }
}

//Menyimpan input form ke dalam entity
fun SuplierEvent.toSuplierEntity(): Suplier = Suplier  (
    id = id,
    nama = nama,
    kontak = kontak,
    alamat = alamat,

)

// data class variabel yang menyimpan data input form
data class SuplierEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Anotasi @PrimaryKey di sini
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = ""
)