package com.app.marvelcharacters.data

import com.app.marvelcharacters.data.charactersdata.CharactersData
import com.app.marvelcharacters.data.detaildata.CharactersDetailData
import retrofit2.http.GET
import retrofit2.http.Path


interface CharactersRetrofit{

    @GET("v1/public/characters")
    suspend fun getCharacters(): CharactersData

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: String) : CharactersDetailData
}