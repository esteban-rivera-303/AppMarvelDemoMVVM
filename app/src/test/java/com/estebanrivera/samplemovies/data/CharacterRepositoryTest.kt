package com.estebanrivera.samplemovies.data

import com.estebanrivera.samplemovies.data.remote.CharacterDataSourceRemote
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
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
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}