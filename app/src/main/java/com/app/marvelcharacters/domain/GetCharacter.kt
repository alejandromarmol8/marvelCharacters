package com.app.marvelcharacters.domain

import com.app.marvelcharacters.data.ParsedResponse
import com.app.marvelcharacters.data.detaildata.CharacterDetailData

class GetCharacter(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacter(character: String): ParsedResponse<CharactersResultFailure.CommonErrors, CharacterDetailData> =
        charactersRepository.getCharacter(character)
}