package com.estebanrivera.samplemovies.di

import android.app.Application
import android.content.Context
import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import com.estebanrivera.samplemovies.data.remote.CharacterService
import com.estebanrivera.samplemovies.usecases.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}

@Module
@InstallIn(SingletonComponent::class)
object UsesCasesModule {
    @Provides
    fun provideGetAllCharacterUseCase(
        repository: CharacterRepository
    ): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(repository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideCharacterRepository(
        dataSource: CharacterDataSourceRemote
    ): CharacterRepository {
        return CharacterRepository(dataSource)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCharacterService(
    ): CharacterService {
        return CharacterService.getInstance()
    }


    @Provides
    fun provideDataSourceRemote(
        service: CharacterService
    ): CharacterDataSourceRemote {
        return CharacterDataSourceRemote(service)
    }
}



