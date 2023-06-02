package id.ac.unpas.showroom.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.showroom.model.mobil
@Database(entities = [mobil::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mobilDao(): MobilDao
}