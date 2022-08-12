package com.estebanrivera.samplemovies.usecases

import com.estebanrivera.samplemovies.data.CharacterRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetACharacterUseCasesTest {
    @Mock
    private lateinit var repository: CharacterRepository

    private lateinit var getACharacterUseCases: GetACharacterUseCases

    @Before
    fun setUp() {
        getACharacterUseCases = GetACharacterUseCases(repository)
    }

    @Test
    suspend fun `get a character use case should return a only one item`()  {

       given(repository.getACharacterById(ArgumentMatchers.any()))
            .willReturn(mockedCharacterDetails)

        val response = getACharacterUseCases.invoke("1234")

        assertEquals(mockedCharacterDetails,response)

    }
}