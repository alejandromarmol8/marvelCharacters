package com.app.marvelcharacters.presentation.di

import com.app.marvelcharacters.presentation.views.details.DetailViewModel
import com.app.marvelcharacters.presentation.views.selection.SelectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class PresentationKoinConfiguration {

    fun getModule() = module {
        viewModel { SelectionViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }
}