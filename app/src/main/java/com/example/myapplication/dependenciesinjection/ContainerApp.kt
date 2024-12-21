package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.myapplication.data.database.BarangDatabase
import com.example.ucp2.repository.LocalRepositorybrg
import com.example.ucp2.repository.LocalRepositorysplr
import com.example.ucp2.repository.RepositorySplr
import com.example.ucp2.repository.Repositorybrg


interface InterfaceContainerApp {
    val repositorybrg : Repositorybrg
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositorybrg: Repositorybrg by lazy {
        LocalRepositorybrg(BarangDatabase.getDatabase(context).BarangDao())
    }

    val repositorySplr: RepositorySplr by lazy {
        LocalRepositorysplr(BarangDatabase.getDatabase(context).SuplierDao())
    }
}