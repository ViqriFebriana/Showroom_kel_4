package id.ac.unpas.showroom.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.showroom.model.mobil
import id.ac.unpas.showroom.persistences.AppDatabase
import id.ac.unpas.showroom.persistences.MobilDao
import kotlinx.coroutines.flow.MutableStateFlow
@Composable
fun PengelolaanMobil() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pengelolaanmobil"
    ).build()

    val mobilDao = db.mobilDao()

    val list : LiveData<List<mobil>> = mobilDao.loadAll()
    val items: List<mobil> by list.observeAsState(initial = listOf())
    Column(modifier = Modifier.fillMaxWidth()) {
        FormMobil { item -> val newList = ArrayList(list)
            newList.add(item)_list.value = newList
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "merk", fontSize = 14.sp)
                        Text(text = item.merk, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "model", fontSize = 14.sp)
                        Text(text = item.model, fontSize = 16.sp, fontWeight =
                        FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "bahan bakar", fontSize = 14.sp)
                        Text(text = "${item.bahan_bakar} ", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "dijual", fontSize = 14.sp)
                        Text(text = "${item.dijual} ", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "deskripsi", fontSize = 14.sp)
                        Text(text = "${item.deskripsi} ", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}