package com.app.marvelcharacters.presentation.views.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.marvelcharacters.data.detaildata.CharacterDetailData
import com.app.marvelcharacters.domain.CharactersResultFailure
import com.app.marvelcharacters.domain.GetCharacter
import com.app.marvelcharacters.presentation.views.bases.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val getRepoCharacters: GetCharacter) : BaseViewModel() {

    val descriptionCharacter = MutableLiveData<CharacterDetailData>()
    private var lastCharacterTried : Int = 0

    fun getCharacter(character: Int){
        loading.value = true
        lastCharacterTried = character
        viewModelScope.launch(Dispatchers.IO) {
            getRepoCharacters.getCharacter(character.toString()).fold(::getCharacterError, :: getCharacterSuccess)
        }
    }

    private fun getCharacterSuccess(data: CharacterDetailData){
        descriptionCharacter.postValue(data)
        loading.postValue(false)
    }

    private fun getCharacterError(error: CharactersResultFailure.CommonErrors){
        handleError(error.error)
    }

    override fun retry() {
        getCharacter(lastCharacterTried)
    }
}