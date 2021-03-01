package com.ab.useralbum.utils

/**
 * Created by Aya Boussaadia on 01,March,2021
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> onSuccess(data: T): Resource<T> =
            Resource(Status.SUCCESS, data = data, message = null)

        fun <T> onFailed( data: T?, message: String): Resource<T> =
            Resource(Status.ERROR, data, message = null)

        fun <T> onLoad( data: T): Resource<T> =
            Resource(Status.LOADING, data, message = null)

    }

}