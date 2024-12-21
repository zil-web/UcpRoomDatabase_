package com.example.ucp2.repository

import com.example.myapplication.data.dao.SuplierDao
import com.example.myapplication.data.entity.Suplier
import kotlinx.coroutines.flow.Flow


class LocalRepositorysplr (
    private val suplierDao: SuplierDao
) : RepositorySplr {
    override suspend fun insertSplr(suplier: Suplier) {
        suplierDao.insertSplr(suplier)
    }

    //Dari sini
    override fun getAllSplr(): Flow<List<Suplier>> {
        return suplierDao.getAllSplr()
    }

    override fun getSplr(nama: String): Flow<Suplier> {
        return suplierDao.getSplr(nama)
    }
}