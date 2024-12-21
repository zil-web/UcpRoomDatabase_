package com.example.ucp2.repository


import com.example.myapplication.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySplr {
    suspend fun insertSplr(suplier: Suplier)

    //Dari sini
    fun getAllSplr(): Flow<List<Suplier>>

    fun getSplr(nama: String) : Flow<Suplier>


}