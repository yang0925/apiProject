package com.example.apiproject.utils

object Constants {
    const val TAG: String = "로그"
}

enum class SEARCH_TYPE {
    PHOTO, USER
}

enum class RESPONSE_STATE{
    OKAY, FAIL
}
object API {
    const val BASE_URL : String = "https://api.unsplash.com/"

    const val CLIENT_ID : String = "Xx3T9bZHIagGzUN9iBM1YHCPjrl8LVLWL4G7KALwn5M"

    const val SEARCH_PHOTO : String = "search/photos"
    const val SEARCH_USERS : String = "search/users"
}