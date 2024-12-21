package com.example.myapplication

import android.app.Application
import com.example.ucp2.dependenciesinjection.ContainerApp

class validasiApp : Application() {
    // Fungsinya untuk menyimpan instance ContainerApp
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //Membuat instance ContainerApp
        containerApp =  ContainerApp(this)
        // instance adalah object yang dibuat dari class
    }
}