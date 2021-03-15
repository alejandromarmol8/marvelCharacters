package com.app.marvelcharacters.presentation.di

import com.app.marvelcharacters.presentation.views.characterdetail.CharacterDetailViewModel
import com.app.marvelcharacters.presentation.views.characterslist.CharactersListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class PresentationKoinConfiguration {

    fun getModule() = module {
        viewModel { CharactersListViewModel(get()) }
        viewModel { CharacterDetailViewModel(get()) }
    }
}