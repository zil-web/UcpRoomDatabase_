package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.entity.Barang
import com.example.myapplication.data.entity.Suplier


@Database(entities = [Barang :: class, Suplier::class], version = 2, exportSchema = false)
abstract class BarangDatabase :RoomDatabase() {

}






































































}