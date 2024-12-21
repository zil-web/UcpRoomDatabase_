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
abstract class KrsDatabase : RoomDatabase() {

    //Mendefinisikan fungsi untuk mengakses data Mahasiswa dan Dosen
    abstract fun BarangDao(): BarangDao
    abstract fun SuplierDao(): SuplierDao

    companion object{
        @Volatile //Memastikan bahwa nilai variabel Instance selalu sama di semua thread
        private var Instance: KrsDatabase? = null

        fun getDatabase(context: Context): KrsDatabase {
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    KrsDatabase::class.java, //Class Database
                    "KrsDatabaseTest" //Nama Database
                )
                    .build().also { Instance = it }
            })
        }
    }





































































}