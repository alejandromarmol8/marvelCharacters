package com.app.marvelcharacters.data

sealed class ParsedResponse<out Error, out Success> {
    data class Success<out Success>(val success: Success) : ParsedResponse<Nothing, Success>()
    data class Error<out Error>(val error: Error) : ParsedResponse<Error, Nothing>()

    fun fold(leftError: (Error) -> Any, rightSuccess: (Success) -> Any): Any =
        when(this){
            is ParsedResponse.Error -> leftError(error)
            is ParsedResponse.Success -> rightSuccess(success)
        }
}