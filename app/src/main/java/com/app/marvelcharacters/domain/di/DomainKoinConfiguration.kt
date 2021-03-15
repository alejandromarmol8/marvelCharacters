package com.app.marvelcharacters.domain.di

import com.app.marvelcharacters.domain.usecases.GetCharacter
import com.app.marvelcharacters.domain.usecases.GetCharacters
import org.koin.dsl.module

class DomainKoinConfiguration {

    fun getModule() = module {
        factory { GetCharacters(get()) }
        factory { GetCharacter(get()) }
    }
}