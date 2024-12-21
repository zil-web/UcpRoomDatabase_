package com.example.myapplication.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entity.Barang
import com.example.ucp2.repository.Repositorybrg
import kotlinx.coroutines.launch

class BarangViewModel (private val repositorybrg: Repositorybrg) : ViewModel(){
    var uiState by mutableStateOf(BrgUIState())

    //Memperbarui state berdasarkan input pengguna
    fun updateState(barangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = barangEvent,

            )
    }

    //Validasi data input pengguna
    private fun validateFields(): Boolean{
        val event = uiState .barangEvent
        val errorState =  FormErrorbrgState(
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "stok tidak boleh kosong",
            suplierId = if (event.suplierId.isNotEmpty()) null else "suplierId tidak boleh kosong",

            )

        uiState = uiState.copy(isbrgEntryValid = errorState)
        return errorState.isValid()
    }

    //Menyimpan data ke repository
    fun saveData(){
        val currentEvent = uiState .barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorybrg.insertbrg(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil disimpan",
                        barangEvent = BarangEvent(), // Reset input form
                        isbrgEntryValid = FormErrorbrgState(), // Reset error state
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }

    // Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(), // Inisialisasi dengan BarangEvent()
    val isbrgEntryValid: FormErrorbrgState = FormErrorbrgState(),
    val snackBarMessage: String? = null,
)

data class FormErrorbrgState(
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val suplierId: String? = null,
) {
    fun isValid(): Boolean {
        return nama == null && deskripsi == null && harga == null && stok == null && suplierId == null
    }
}

//Menyimpan input form ke dalam entity
fun BarangEvent.toBarangEntity(): Barang = Barang (
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    suplierId = suplierId
)

// data class variabel yang menyimpan data input form
data class BarangEvent(
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "" ,
    val stok: String = "" ,
    val suplierId: String = ""
)