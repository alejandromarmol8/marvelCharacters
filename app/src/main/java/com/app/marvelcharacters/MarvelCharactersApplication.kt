package com.app.marvelcharacters

import android.app.Application
import com.app.marvelcharacters.data.di.DataKoinConfiguration
import com.app.marvelcharacters.domain.di.DomainKoinConfiguration
import com.app.marvelcharacters.presentation.di.PresentationKoinConfiguration
import org.koin.core.context.startKoin

class MarvelCharactersApplication: Application() {

    companion object{
        const val BASE_URL = "https://gateway.marvel.com/"
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    DataKoinConfiguration(BASE_URL).getModule(),
                    DomainKoinConfiguration().getModule(),
                    PresentationKoinConfiguration().getModule()
                )
            )
        }
    }
}