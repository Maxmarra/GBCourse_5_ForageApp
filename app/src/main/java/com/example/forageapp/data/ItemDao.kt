package com.example.forageapp.data

import androidx.room.*
import com.example.forageapp.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("select * from forageable_database order by name asc")
    fun getForageables(): Flow<List<Item>>

    @Query("select * from forageable_database where id = :id")
    fun getForageable(id: Long): Flow<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forageable: Item)

    @Update
    fun update(forageable: Item)

    @Delete
    fun delete(forageable: Item)
}
