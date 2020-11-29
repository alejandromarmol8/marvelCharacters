package com.app.marvelcharacters.presentation.views.bases

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.marvelcharacters.R
import com.app.marvelcharacters.domain.CommonFailures

abstract class BaseViewModel: ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<Int>()

    protected fun handleError(error: CommonFailures){
        loading.postValue(false)
        errorMsg.postValue(when(error){
            is CommonFailures.NoInternet -> R.string.no_connection_error
            is CommonFailures.Unknown -> R.string.generic_error
        })
    }

    abstract fun retry()
}