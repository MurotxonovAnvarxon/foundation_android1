package com.devuz.local
import android.content.Context
import androidx.room.Room
import com.devuz.local.dao.CharacterDao
import com.devuz.local.database.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "character.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDetailDao(moviesDatabase: CharacterDatabase): CharacterDao =
        moviesDatabase.getFavoriteCharacter()
}