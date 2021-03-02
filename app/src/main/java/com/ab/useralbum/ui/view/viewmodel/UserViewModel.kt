package com.ab.useralbum.ui.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ab.useralbum.repository.UserRespository
import com.ab.useralbum.utils.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Aya Boussaadia on 01,March,2021
 */

    class UserViewModel @ViewModelInject constructor(
        private val repository: UserRespository
    ) : ViewModel() {

        val users = repository.getUsers()
    }
