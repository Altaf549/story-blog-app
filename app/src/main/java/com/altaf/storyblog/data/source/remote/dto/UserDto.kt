package com.altaf.storyblog.data.source.remote.dto

import com.altaf.storyblog.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("name")
    val name: String
) {
    fun toUser(): User {
        return User(
            id = id,
            name = name
        )
    }
}
