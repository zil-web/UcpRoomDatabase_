package com.example.myapplication.ui.view.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PilihanHomeView(
    onBarangClick: () -> Unit,
    onSuplierClick: () -> Unit,
    onDetailSuplierClick: () -> Unit,
    onDetailBarangClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "selamat datang sayang",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Tombol untuk Home Dosen
            androidx.compose.material3.Button(
                onClick = onBarangClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(text = "tambah barang")
            }

            // Tombol untuk Home Mahasiswa
            androidx.compose.material3.Button(
                onClick = onSuplierClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "tambah suplier")
            }

            // Tombol untuk Home Mahasiswa
            androidx.compose.material3.Button(
                onClick = onDetailBarangClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "detail Barang")
            }

            // Tombol untuk Home Mahasiswa
            androidx.compose.material3.Button(
                onClick = onDetailSuplierClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Detail Suplier")
            }
        }
    }
}
