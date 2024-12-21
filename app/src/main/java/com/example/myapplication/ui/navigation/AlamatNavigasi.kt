package com.example.myapplication.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomebrg : AlamatNavigasi {  // Ubah menjadi DestinasiHomebrg
    override val route = "home-brg"  // Ubah menjadi "home-brg"
}

object DestinasiDetailbrg : AlamatNavigasi {  // Ubah menjadi DestinasiDetailbrg
    override val route = "detail-brg"  // Ubah menjadi "detail-brg"
    const val ID = "id"  // Ubah menjadi ID
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdatebrg : AlamatNavigasi {  // Ubah menjadi DestinasiUpdatebrg
    override val route = "update-brg"  // Ubah menjadi "update-brg"
    const val ID = "id"  // Ubah menjadi ID
    val routesWithArg = "$route/{$ID}"
}

object DestinasiHomesplr : AlamatNavigasi {  // Ubah menjadi DestinasiHomesplr
    override val route = "home-splr"  // Ubah menjadi "home-splr"
}

object DestinasiAwal : AlamatNavigasi {
    override val route = "awalan"
}