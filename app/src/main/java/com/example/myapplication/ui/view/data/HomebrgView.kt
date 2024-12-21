package com.example.myapplication.ui.view.data

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.entity.Barang
import com.example.myapplication.ui.viewmodel.HomeUibrgState
import com.example.myapplication.ui.viewmodel.HomebrgViewModel
import com.example.myapplication.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.costumwidget.TopAppBar
import kotlinx.coroutines.launch

@Composable
fun HomebrgView(
    viewModel: HomebrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddbrg: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Daftar barang",
                showBackButton = false,
                onBack = { },
                modifier = modifier
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddbrg,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah barang",
                )
            }
        }
    ) { innerPadding ->
        val homeUibrgState by viewModel.homeUIbrgState.collectAsState()

        BodyHomebrgView(
            homeUibrgState = homeUibrgState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomebrgView(
    homeUibrgState: HomeUibrgState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() } // Snackbar state
    when {
        homeUibrgState.isbrgLoading -> {
            // Menampilkan indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUibrgState.isbrgError -> {
            //Menampilkan pesarn error
            LaunchedEffect(homeUibrgState.errorbrgMessage) {
                homeUibrgState.errorbrgMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message) //Tampilkan Snackbar
                    }
                }
            }
        }

        homeUibrgState.listbrg.isEmpty() -> {
            // Menampilkan pesan jika data kosong
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data barang.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            //Menampilkan daftar mahasiswa
            ListBarang(
                listbrg = homeUibrgState.listbrg,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarang(
    listbrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn (
        modifier = modifier
    ) {
        items(
            items = listbrg,
            itemContent = { brg ->
                Cardbrg(
                    brg = brg,
                    onClick = { onClick(brg.id.toString()) } // Ubah menjadi String
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cardbrg(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card (
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = brg.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Deskripsi: ${brg.deskripsi}",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Harga: ${brg.harga}",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Stok: ${brg.stok}",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "Supplier ID: ${brg.suplierId}",
                fontSize = 16.sp
            )
        }
    }
}