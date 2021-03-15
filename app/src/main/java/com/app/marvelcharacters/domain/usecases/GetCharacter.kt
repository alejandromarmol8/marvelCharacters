package com.app.marvelcharacters.domain.usecases

import com.app.marvelcharacters.data.ParsedResponse
import com.app.marvelcharacters.data.detaildata.CharacterDetailData
import com.app.marvelcharacters.domain.CharactersRepository
import com.app.marvelcharacters.domain.CharactersResultFailure

class GetCharacter(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacter(character: String): ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> =
        charactersRepository.getCharacter(character)
}