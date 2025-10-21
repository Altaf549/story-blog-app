package com.altaf.storyblog.domain.model.networkModel

/**
 * A wrapper for handling failing requests
 * */
sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null, message: String) : NetworkResult<T>(data, message)
    class Error<T>(message: String) : NetworkResult<T>(null, message)
}

