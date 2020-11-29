package com.app.marvelcharacters.domain

sealed class CharactersResultFailure {
    data class CommonErrors(val error: CommonFailures)
}