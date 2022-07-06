package com.example.forageapp.model

import kotlinx.coroutines.flow.Flow

interface ForageRepository {

    fun getForageables(): Flow<List<Item>>

    fun getForageable(id: Long): Flow<Item>

    fun insert(forageable: Item)

    fun update(forageable: Item)

    fun delete(forageable: Item)
}