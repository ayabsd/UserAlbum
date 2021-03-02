package com.ab.useralbum.repository

import com.ab.useralbum.data.local.Dao
import com.ab.useralbum.remote.RemoteDataSource
import com.ab.useralbum.utils.performGetOperation
import javax.inject.Inject

/**
 * Created by Aya Boussaadia on 01,March,2021
 */


class UserRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: Dao

    ) {

    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getAllUsers() },
        saveCallResult = { localDataSource.insertAllUsers(it) }
    )
}