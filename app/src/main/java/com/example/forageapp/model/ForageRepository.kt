package com.example.forageapp.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface ForageRepository {

    fun getForageables(): Flow<List<Forageable>>

    fun getForageable(id: Long): Flow<Forageable>

    fun insert(forageable: Forageable)

    fun update(forageable: Forageable)

    fun delete(forageable: Forageable)
}