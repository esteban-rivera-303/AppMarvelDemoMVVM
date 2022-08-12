package com.estebanrivera.samplemovies.usecases

import com.estebanrivera.samplemovies.data.CharacterRepository
import com.estebanrivera.samplemovies.domain.Character
import com.estebanrivera.samplemovies.domain.CharacterDetails

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


import org.junit.runner.RunWith
import org.junit.Assert.*;
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given

import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetAllCharactersUseCaseTest {

    @Mock
    private lateinit var repository: CharacterRepository

    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    @Before
    fun setUp() {
        getAllCharactersUseCase = GetAllCharactersUseCase(repository)
    }

    @Test
    suspend fun `get all character use case should return a list of characters given a offset and limit`() = runBlocking<Unit>  {
        val expectedResult = listOf(mockedCharacter.copy(id = 1))
        given(repository.getAllCharacter(any(), any())).willReturn(expectedResult)

        val response = getAllCharactersUseCase.invoke(1,1)

        assertEquals(mockedCharacter,response)

    }

    @After
    fun tearDown() {
    }
}

val mockedCharacter = Character(
    0,
    "Name",
    "Description",
    "thumbnail.jpg"

)


val mockedCharacterDetails = CharacterDetails(
    0,
    "Name",
    "Description",
    "thumbnail.jpg",
)
