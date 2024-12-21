package com.example.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.dao.BarangDao
import com.example.myapplication.data.dao.SuplierDao
import com.example.myapplication.data.entity.Barang
import com.example.myapplication.data.entity.Suplier


//Mendefinisikan database dengan tabel mahasiswa
@Database(entities = [Barang::class, Suplier ::class], version = 2, exportSchema = false)
abstract class BarangDatabase : RoomDatabase() {

    //Mendefinisikan fungsi untuk mengakses data Mahasiswa dan Dosen
    abstract fun BarangDao(): BarangDao
    abstract fun SuplierDao(): SuplierDao

    companion object{
        @Volatile //Memastikan bahwa nilai variabel Instance selalu sama di semua thread
        private var Instance: BarangDatabase? = null

        fun getDatabase(context: Context): BarangDatabase {
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    BarangDatabase::class.java, //Class Database
                    "BarangDatabaseTest" //Nama Database
                )
                    .build().also { Instance = it }
            })
        }
    }





































































}