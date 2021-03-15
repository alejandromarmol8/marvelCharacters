package com.app.marvelcharacters.domain

import com.app.marvelcharacters.data.ParsedResponse
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.data.detaildata.CharacterDetailData
import com.app.marvelcharacters.domain.CharactersResultFailure.*

interface CharactersRepository {
    suspend fun getCharacters(): ParsedResponse<CommonErrors, List<CharacterData>>
    suspend fun getCharacter(character: String) : ParsedResponse<CommonErrors, CharacterDetailData>
}