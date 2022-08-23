package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.usecases.mockedCharacterDetails
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    // /Users/esteban.rivera01/Library/Java/JavaVirtualMachines/liberica-1.8.0_345/jre
    @Mock
    private lateinit var dataSourceRemote: CharacterDataSourceRemote

    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this).close()
        repository = CharacterRepository(dataSourceRemote)
    }

    @Test
    fun shouldCallCharacterBydId() {
        runBlocking {
            whenever(dataSourceRemote.getCharacterById("1234"))
                .thenReturn(ResultWrapper.Success(mockedCharacterDetails))

            val response = repository.getACharacterById("1234")

            verify(dataSourceRemote).getCharacterById("1234")
            assertEquals(mockedCharacterDetails, (response as ResultWrapper.Success).value)
        }
    }
}