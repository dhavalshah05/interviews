package com.template.app.domain.managers.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manager(
    val id: Long,
    val name: String
) : Parcelable
