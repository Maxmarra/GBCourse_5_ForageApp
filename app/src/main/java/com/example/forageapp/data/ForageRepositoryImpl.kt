package com.example.forageapp.data

import com.example.forageapp.model.ForageRepository
import com.example.forageapp.model.Item
import kotlinx.coroutines.flow.Flow

class ForageRepositoryImpl(private val forageableDao: ItemDao)
    :ForageRepository {
    override fun getForageables(): Flow<List<Item>> {
        return forageableDao.getForageables()
    }

    override fun getForageable(id: Long): Flow<Item> {
        return forageableDao.getForageable(id)
    }

    override fun insert(forageable: Item) {
        forageableDao.insert(forageable)
    }

    override fun update(forageable: Item) {
        forageableDao.update(forageable)
    }

    override fun delete(forageable: Item) {
        forageableDao.delete(forageable)
    }
}