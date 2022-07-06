package com.example.forageapp.data

import com.example.forageapp.model.ForageRepository
import com.example.forageapp.model.Forageable
import kotlinx.coroutines.flow.Flow

class ForageRepositoryImpl(private val forageableDao: ForageableDao)
    :ForageRepository {
    override fun getForageables(): Flow<List<Forageable>> {
        return forageableDao.getForageables()
    }

    override fun getForageable(id: Long): Flow<Forageable> {
        return forageableDao.getForageable(id)
    }

    override fun insert(forageable: Forageable) {
        forageableDao.insert(forageable)
    }

    override fun update(forageable: Forageable) {
        forageableDao.update(forageable)
    }

    override fun delete(forageable: Forageable) {
        forageableDao.delete(forageable)
    }
}