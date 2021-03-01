package com.ab.useralbum.repository

import com.mindorks.retrofit.coroutines.data.api.ApiHelper

/**
 * Created by Aya Boussaadia on 01,March,2021
 */
class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}