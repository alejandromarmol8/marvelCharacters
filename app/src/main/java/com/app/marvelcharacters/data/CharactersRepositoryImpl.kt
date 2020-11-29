package com.app.marvelcharacters.data

import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.data.detaildata.CharacterDetailData
import com.app.marvelcharacters.domain.CharactersRepository
import com.app.marvelcharacters.domain.CharactersResultFailure.CommonErrors

class CharactersRepositoryImpl(private val charactersRemoteDataSource: CharactersRemoteDataSource) :
    CharactersRepository {

    override suspend fun getCharacters(): ParsedResponse<CommonErrors, List<CharacterData>> = charactersRemoteDataSource.getCharacters()
    override suspend fun getCharacter(character: String): ParsedResponse<CommonErrors, CharacterDetailData> = charactersRemoteDataSource.getCharacter(character)
}