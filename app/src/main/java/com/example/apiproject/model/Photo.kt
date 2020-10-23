package com.example.apiproject.model

import java.io.Serializable

data class Photo (
    var thumbnail: String?,
    var author: String?,
    var createdAt: String?,
    var likesCount: Int?
):Serializable