package com.example.forageapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.forageapp.model.ForageRepository
import com.example.forageapp.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForageableViewModel @Inject constructor(
    private val repository: ForageRepository
): ViewModel() {

    val allData: LiveData<List<Item>> = repository.getForageables().asLiveData()
    fun retrieveForageable(id:Long) = repository.getForageable(id).asLiveData()

    fun addForageable(
        name: String,
        address: String,
        inSeason: Boolean,
        notes: String
    ) {
        val forageable = Item(
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )
        viewModelScope.launch {
            repository.insert(forageable)
        }
    }

    fun updateForageable(
        id: Long,
        name: String,
        address: String,
        inSeason: Boolean,
        notes: String
    ) {
        val forageable = Item(
            id = id,
            name = name,
            address = address,
            inSeason = inSeason,
            notes = notes
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(forageable)
        }
    }

    fun deleteForageable(forageable: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(forageable)
        }
    }

    fun isValidEntry(name: String, address: String): Boolean {
        return name.isNotBlank() && address.isNotBlank()
    }
}


