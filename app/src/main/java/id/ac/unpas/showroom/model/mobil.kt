package id.ac.unpas.showroom.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class mobil(
    @PrimaryKey
    val merk: String,
    val model: String,
    val bahan_bakar:String,
    val dijual: String,
    val deskripsi: String,
)