package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.myapplication.data.entity.Barang


@Dao
interface BarangDao {
    @Insert
    suspend fun insertBARANG(
        barang: Barang
    )
}