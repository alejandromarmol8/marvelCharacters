package com.app.marvelcharacters.domain.usecases

import com.app.marvelcharacters.data.ParsedResponse
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.domain.CharactersRepository
import com.app.marvelcharacters.domain.CharactersResultFailure.*

class GetCharacters(private val charactersRepository: CharactersRepository) {

    suspend fun getCharacters(): ParsedResponse<CommonErrors, List<CharacterData>> = charactersRepository.getCharacters()
}