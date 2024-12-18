package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.data.entity.Suplier

@Dao
interface SuplierDao {
    @Insert
    suspend fun insertSuplier(suplier: Suplier)

    @Query("SELECT * FROM Suplier")
    fun getAllSupliers(): LiveData<List<Suplier>>
}