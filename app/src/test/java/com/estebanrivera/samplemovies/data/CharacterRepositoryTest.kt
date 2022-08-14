package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import com.estebanrivera.samplemovies.data.remote.ResultWrapper
import com.estebanrivera.samplemovies.usecases.mockedCharacterDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @Mock
    private lateinit var dataSourceRemote: CharacterDataSourceRemote

    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        repository = CharacterRepository(dataSourceRemote)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun addition_isCorrect() = runTest{
        BDDMockito.given(dataSourceRemote.getCharacterById(ArgumentMatchers.any()))
            .willReturn(ResultWrapper.Success(mockedCharacterDetails))

        val response = repository.getACharacterById("1234")

        assertEquals(mockedCharacterDetails,response)

    }
}