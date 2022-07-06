package com.example.forageapp.data

import androidx.room.*
import com.example.forageapp.model.Forageable
import kotlinx.coroutines.flow.Flow

@Dao
interface ForageableDao {

    @Query("select * from forageable_database order by name asc")
    fun getForageables(): Flow<List<Forageable>>

    @Query("select * from forageable_database where id = :id")
    fun getForageable(id: Long): Flow<Forageable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forageable: Forageable)

    @Update
    fun update(forageable: Forageable)

    @Delete
    fun delete(forageable: Forageable)
}
