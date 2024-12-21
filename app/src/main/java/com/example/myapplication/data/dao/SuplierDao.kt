package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao {
    @Insert
    suspend fun insertSplr(

        suplier: Suplier
    )

    //Dari sini
    @Query("SELECT * FROM Suplier ORDER BY nama ASC")
    fun getAllSplr(): Flow<List<Suplier>>

    @Query("SELECT * FROM Suplier WHERE nama = :nama")
    fun getSplr(nama: String): Flow<Suplier>
}
