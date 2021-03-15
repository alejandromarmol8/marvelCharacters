package com.app.marvelcharacters.domain

import com.app.marvelcharacters.data.ParsedResponse
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.domain.usecases.GetCharacters
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetCharactersTest {

    private var repo: CharactersRepository = mock()
    private lateinit var getCharacters: GetCharacters

    @Before
    fun setUp(){
        getCharacters = GetCharacters(repo)
    }

    @Test
    fun `Get characters`() = runBlocking {
        val repoResult: ParsedResponse<CharactersResultFailure.CommonErrors, List<CharacterData>> = mock()

        Mockito.`when`(repo.getCharacters()).thenReturn(repoResult)
        val result: ParsedResponse<CharactersResultFailure.CommonErrors, List<CharacterData>> =
            getCharacters.getCharacters()

        Assert.assertEquals(repoResult, result)
    }

    @Test
    fun `Common error`() = runBlocking {
        Mockito.`when`(repo.getCharacters()).thenReturn(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.Unknown)))
        val result: ParsedResponse<CharactersResultFailure.CommonErrors, List<CharacterData>> = getCharacters.getCharacters()

        Assert.assertEquals(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.Unknown)), result)
    }

    @Test
    fun `Connexion Error`() = runBlocking {
        Mockito.`when`(repo.getCharacters()).thenReturn(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.NoInternet)))
        val result: ParsedResponse<CharactersResultFailure.CommonErrors, List<CharacterData>> = getCharacters.getCharacters()

        Assert.assertEquals(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.NoInternet)), result)
    }
}