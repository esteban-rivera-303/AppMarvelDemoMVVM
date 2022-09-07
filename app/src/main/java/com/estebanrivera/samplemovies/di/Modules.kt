package com.estebanrivera.samplemovies.di

import android.app.Application
import android.content.Context
import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.data.local.CharacterDataSourceLocal
import com.estebanrivera.samplemovies.data.local.CharacterDataSourceLocalImpl
import com.estebanrivera.samplemovies.data.local.CharacterDatabase
import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemoteImpl
import com.estebanrivera.samplemovies.data.remote.CharacterService
import com.estebanrivera.samplemovies.usecases.GetAllCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
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
        remoteImpl: CharacterDataSourceRemoteImpl,
        localImpl: CharacterDataSourceLocalImpl

    ): CharacterRepository {
        return CharacterRepository(remoteImpl, localImpl)
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
    fun provideCharacterDataSourceRemote(
        service: CharacterService,
        context: Context
    ): CharacterDataSourceRemoteImpl {
        return CharacterDataSourceRemoteImpl(service, Dispatchers.IO, context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(app: Application): CharacterDatabase = CharacterDatabase.getDatabase(app)

    @Provides
    fun provideCharacterDataSourceLocal(
        database: CharacterDatabase
    ): CharacterDataSourceLocal = CharacterDataSourceLocalImpl(database)
}



