package com.example.reddit.ui.base

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable
    val errorMessage = ObservableField<String>("")

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     */
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}