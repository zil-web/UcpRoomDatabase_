package com.example.ucp2.repository

import com.example.myapplication.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface Repositorybrg {
    suspend fun insertbrg(barang: Barang)

    //Dari sini
    fun getAllbrg(): Flow<List<Barang>>

    fun getbrg(suplierId: String) : Flow<Barang>

    suspend fun  deletebrg(barang: Barang)

    suspend fun  updatebrg(barang: Barang)
}