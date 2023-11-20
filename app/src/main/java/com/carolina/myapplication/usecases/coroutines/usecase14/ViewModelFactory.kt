package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carolina.myapplication.usecases.coroutines.usecase14.AndroidVersionRepository

class ViewModelFactory(private val repository: AndroidVersionRepository?) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AndroidVersionRepository::class.java)
            .newInstance(repository)
    }
}