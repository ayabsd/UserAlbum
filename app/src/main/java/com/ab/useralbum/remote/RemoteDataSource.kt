package com.ab.useralbum.remote

import com.ab.useralbum.data.ApiService
import javax.inject.Inject

/**
 * Created by Aya Boussaadia on 02,March,2021
 */
class RemoteDataSource @Inject constructor(
    private val service: ApiService
): BaseDataSource() {

    suspend fun getAllUsers() = getResult { service.getUsers() }
}