package com.app.marvelcharacters.domain

import com.app.marvelcharacters.data.ParsedResponse
import com.app.marvelcharacters.data.detaildata.CharacterDetailData
import com.app.marvelcharacters.domain.usecases.GetCharacter
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetCharacterTest {

    private var repo: CharactersRepository = mock()
    private lateinit var getCharacter: GetCharacter

    @Before
    fun setUp(){
        getCharacter = GetCharacter(repo)
    }

    @Test
    fun `Get character`() = runBlocking{
        val repoResult: ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> = mock()
        val character = "1"

        Mockito.`when`(repo.getCharacter(character)).thenReturn(repoResult)
        val result : ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> = getCharacter.getCharacter(character)

        Assert.assertEquals(repoResult, result)
    }


    @Test
    fun `Common error`() = runBlocking {
        val character = "1"

        Mockito.`when`(repo.getCharacter(character)).thenReturn(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.Unknown)))
        val result: ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> = getCharacter.getCharacter(character)

        Assert.assertEquals(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.Unknown)), result)
    }

    @Test
    fun `Connexion error`() = runBlocking {
        val character = "1"

        Mockito.`when`(repo.getCharacter(character)).thenReturn(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.NoInternet)))
        val result: ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> = getCharacter.getCharacter(character)

        Assert.assertEquals(ParsedResponse.Error(CharactersResultFailure.CommonErrors(CommonFailures.NoInternet)), result)
    }
}