package com.app.marvelcharacters.data

import com.app.marvelcharacters.data.ParsedResponse.*
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.data.charactersdata.CharactersData
import com.app.marvelcharacters.data.charactersdata.ResultsData
import com.app.marvelcharacters.data.datasource.CharactersRemoteDataSourceImpl
import com.app.marvelcharacters.data.detaildata.*
import com.app.marvelcharacters.domain.CharactersResultFailure
import com.app.marvelcharacters.domain.CommonFailures
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CharactersRemoteDataSourceTest {
    private val retrofit: CharactersRetrofit = mock()
    private lateinit var charactersRemoteDataSource: CharactersRemoteDataSourceImpl

    @Before
    fun setUp(){
        charactersRemoteDataSource = CharactersRemoteDataSourceImpl(retrofit)
    }

    //Get Characters Tests
    @Test
    fun `Get Characters - Check if characters list is empty`() = runBlocking {
        val emptyListOfCharacters = emptyList<CharacterData>()
        val resultsData  = ResultsData(emptyListOfCharacters)
        val response = CharactersData(resultsData)

        Mockito.`when`(retrofit.getCharacters()).thenReturn(response)

        val result :ParsedResponse<CharactersResultFailure.CommonErrors, List<CharacterData>> = charactersRemoteDataSource.getCharacters()

        Assert.assertEquals(Error(CharactersResultFailure.CommonErrors(CommonFailures.Unknown)), result)
    }

    @Test
    fun `Get Characters - Check if characters list has a single character`() = runBlocking {
        val listOfCharacters = listOf(CharacterData(0, "", ThumbnailData("photoPath", "ext")))
        val resultsData  = ResultsData(listOfCharacters)
        val response = CharactersData(resultsData)

        Mockito.`when`(retrofit.getCharacters()).thenReturn(response)

        val result :ParsedResponse<CharactersResultFailure.CommonErrors, List<CharacterData>> = charactersRemoteDataSource.getCharacters()

        Assert.assertEquals(Success(listOfCharacters), result)
    }

    @Test
    fun `Get Characters - Check if characters list has several characters`() = runBlocking {
        val listOfCharacters = listOf(CharacterData(0, "", ThumbnailData("photoPath", "ext")),
                CharacterData(1, "", ThumbnailData("photoPath", "ext")),
                CharacterData(2, "", ThumbnailData("photoPath", "ext")))
        val resultsData  = ResultsData(listOfCharacters)
        val response = CharactersData(resultsData)

        Mockito.`when`(retrofit.getCharacters()).thenReturn(response)

        val result :ParsedResponse<CharactersResultFailure.CommonErrors, List<CharacterData>> = charactersRemoteDataSource.getCharacters()

        Assert.assertEquals(Success(listOfCharacters), result)
    }

    //Get character tests
    @Test
    fun `Get Character - Check if one id returns two characters`() = runBlocking {
        val list = listOf(CharacterDetailData("name", "", ThumbnailData(), ComicsData(), SeriesData(), StoriesData(), EventsData()),
                CharacterDetailData("otherName", "", ThumbnailData(), ComicsData(), SeriesData(), StoriesData(), EventsData())
        )
        val resultDetailData = ResultDetailData(list)
        val response = CharactersDetailData(resultDetailData)
        val id = "1"

        Mockito.`when`(retrofit.getCharacter(id)).thenReturn(response)

        val result :ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> = charactersRemoteDataSource.getCharacter(id)

        Assert.assertEquals(Error(CharactersResultFailure.CommonErrors(CommonFailures.Unknown)), result)
    }

    @Test
    fun `Get Character - Check if one id returns zero characters`() = runBlocking {
        val list = emptyList<CharacterDetailData>()
        val resultDetailData = ResultDetailData(list)
        val response = CharactersDetailData(resultDetailData)
        val id = "1"

        Mockito.`when`(retrofit.getCharacter(id)).thenReturn(response)

        val result :ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> = charactersRemoteDataSource.getCharacter(id)

        Assert.assertEquals(Error(CharactersResultFailure.CommonErrors(CommonFailures.Unknown)), result)
    }

    @Test
    fun `Get Character - Check if one id returns one character`() = runBlocking {
        val list =  listOf(CharacterDetailData("name", "", ThumbnailData(), ComicsData(), SeriesData(), StoriesData(), EventsData()))
        val resultDetailData = ResultDetailData(list)
        val response = CharactersDetailData(resultDetailData)
        val id = "1"

        Mockito.`when`(retrofit.getCharacter(id)).thenReturn(response)

        val result :ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> = charactersRemoteDataSource.getCharacter(id)

        Assert.assertEquals(Success(list[0]), result)
    }

}