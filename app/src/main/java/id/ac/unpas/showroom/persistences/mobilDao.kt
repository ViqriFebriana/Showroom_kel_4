package id.ac.unpas.showroom.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.showroom.model.mobil
@Dao
interface MobilDao {
    @Query("SELECT * FROM mobil")
    fun loadAll(): LiveData<List<mobil>>
    @Query("SELECT * FROM mobil WHERE id = :id")
    fun find(id: String): mobil?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: mobil)
    @Delete
    fun delete(item: mobil)
}