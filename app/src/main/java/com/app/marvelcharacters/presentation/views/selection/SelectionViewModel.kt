package com.app.marvelcharacters.presentation.views.selection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.marvelcharacters.data.charactersdata.CharacterData
import com.app.marvelcharacters.domain.CharactersResultFailure
import com.app.marvelcharacters.domain.GetCharacters
import com.app.marvelcharacters.presentation.views.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectionViewModel(private val getRepoCharacters: GetCharacters) : BaseViewModel() {

    val characters = MutableLiveData<List<CharacterData>>()

    fun getCharacters(){
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            getRepoCharacters.getCharacters().fold(::getCharactersError, :: getCharactersSuccess)
        }
    }

    private fun getCharactersSuccess(data: List<CharacterData>) {
        characters.postValue(data)
        loading.postValue(false)
    }

    private fun getCharactersError(error: CharactersResultFailure.CommonErrors){
        handleError(error.error)
    }

    override fun retry() {
        getCharacters()
    }
}