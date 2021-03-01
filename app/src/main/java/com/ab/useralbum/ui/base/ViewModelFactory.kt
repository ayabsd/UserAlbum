package com.ab.useralbum.ui.base

/**
 * Created by Aya Boussaadia on 01,March,2021
 */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ab.useralbum.repository.MainRepository
import com.ab.useralbum.ui.view.viewmodel.UserViewModel
import com.mindorks.retrofit.coroutines.data.api.ApiHelper

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

