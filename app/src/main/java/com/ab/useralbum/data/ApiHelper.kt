package com.mindorks.retrofit.coroutines.data.api

import com.ab.useralbum.data.ApiService

class ApiHelper(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}