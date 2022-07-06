package com.example.forageapp.data

import android.app.Application
import androidx.room.Room
import com.example.forageapp.model.ForageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideForageDatabase(app: Application): ForageDatabase {
        return Room.databaseBuilder(
            app,
            ForageDatabase::class.java,
            "item_database"
        ).fallbackToDestructiveMigration()
         .build()
    }

    @Provides
    @Singleton
    fun provideForageRepository(db: ForageDatabase):
            ForageRepository {
        return ForageRepositoryImpl(db.foragableDao)
    }
}