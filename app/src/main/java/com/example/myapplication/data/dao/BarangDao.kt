package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.data.entity.Barang
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {
    @Insert
    suspend fun insertBarang(
        barang: Barang
    )

    //Dari sini
    @Query("SELECT * FROM barang ORDER BY nama ASC")
    fun getAllBarang() : Flow<List<Barang>>

    @Query("SELECT * FROM Barang WHERE suplierId = :suplierId")
    fun getBarang (suplierId: String) : Flow<Barang>

    @Delete
    suspend fun deleteBarang(
        barang: Barang
    )

    @Update
    suspend fun updateBarang(
        barang: Barang
    )}