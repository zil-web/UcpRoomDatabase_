package com.example.myapplication.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Suplier::class,
        parentColumns = ["id"],
        childColumns = ["suplierId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Barang(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val deskripsi: String,
    val harga: Double,
    val stok: Int,
    val suplierId: Int
)