package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao {
    @Insert
    suspend fun insertSuplier(
        suplier: Suplier
    )

    @Query("SELECT * FROM Suplier ORDER BY nama ASC")
    fun getallSuplier(): Flow<List<Suplier>>

    @Query("SELECT * FROM Suplier WHERE id = :id")
    fun getSuplier (id: String) : Flow<Suplier>
}