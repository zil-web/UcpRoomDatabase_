package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.entity.Barang
import com.example.myapplication.data.entity.Suplier
import kotlinx.coroutines.flow.Flow


@Dao
interface BarangDao {
    @Insert
    suspend fun insertBARANG(
        barang: Barang
    )

@Query("SELECT * FROM Barang  ORDER BY nama")
fun getAllBarang() : Flow<Suplier>

@Query("SELECT * FROM Barang WHERE deskripsi")
fun getBarang (deskripsi: String) Flow<Barang>

    @Delete
    suspend fun deletebarang(
       barang: Barang
    )

    @Update
    suspend fun updateDosen(
        barang: Barang
    )
}