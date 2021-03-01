package com.ab.useralbum.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ab.useralbum.repository.MainRepository
import com.ab.useralbum.utils.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Aya Boussaadia on 01,March,2021
 */
class UserViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.onLoad(data = null))
        try {
            emit(Resource.onSuccess(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.onFailed(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}