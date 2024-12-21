package com.example.ucp2.repository

import com.example.myapplication.data.dao.BarangDao
import com.example.myapplication.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositorybrg (
    private val barangDao: BarangDao
) : Repositorybrg {
    override suspend fun insertbrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllbrg(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getbrg(suplierId: String): Flow<Barang> {
        return barangDao.getBarang(suplierId)

    }

    override suspend fun deletebrg(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updatebrg(barang: Barang) {
        barangDao.updateBarang(barang)
    }

}