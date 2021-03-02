package com.ab.useralbum.remote

import com.ab.useralbum.utils.Resource
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Aya Boussaadia on 02,March,2021
 */

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return com.ab.useralbum.utils.Resource.onSuccess(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return com.ab.useralbum.utils.Resource.onFailed("Network call has failed for a following reason: $message", null)
    }

}