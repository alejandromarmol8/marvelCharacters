package com.app.marvelcharacters.domain

sealed class CommonFailures {
    object NoInternet : CommonFailures()
    object Unknown : CommonFailures()
}