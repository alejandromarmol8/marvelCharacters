package com.app.marvelcharacters.data.datasource

import com.app.marvelcharacters.data.ParsedResponse
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.data.detaildata.CharacterDetailData
import com.app.marvelcharacters.domain.CharactersResultFailure.*

interface CharactersRemoteDataSource {
    suspend fun getCharacters() : ParsedResponse<CommonErrors, List<CharacterData>>
    suspend fun getCharacter(character: String) : ParsedResponse<CommonErrors, CharacterDetailData>
}