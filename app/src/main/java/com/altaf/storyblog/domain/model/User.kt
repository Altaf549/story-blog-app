package com.altaf.storyblog.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Long = -1,
    val name: String = ""
) : Parcelable
