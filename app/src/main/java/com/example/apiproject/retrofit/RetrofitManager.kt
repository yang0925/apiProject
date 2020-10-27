package com.example.apiproject.retrofit


import android.provider.Telephony.BaseMmsColumns.RESPONSE_STATUS
import com.example.apiproject.model.Photo
import com.google.gson.JsonElement
import com.example.apiproject.utils.API
import com.example.apiproject.utils.RESPONSE_STATUS
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : Service? = RetrofitClient.getClient(API.BASE_URL)?.create(Service::class.java)


    // 사진 검색 api 호출
    fun searchPhotos(searchTerm: String?, completion: (RESPONSE_STATUS, ArrayList<Photo>?) -> Unit){

        val term = searchTerm.let {
            it
        }?: ""

        val call = iRetrofit?.searchPhotos(searchTerm = term).let {
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{

            // 응답 실패시
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(com.example.apiproject.utils.RESPONSE_STATUS.FAIL, null)
            }

            // 응답 성공시
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                when(response.code()){
                    200 -> {

                        response.body()?.let {

                            var parsedPhotoDataArray = ArrayList<Photo>()

                            val body = it.asJsonObject

                            val results = body.getAsJsonArray("results")

                            val total = body.get("total").asInt


                            // 데이터가 없으면 no_content 로 보낸다.
                            if(total == 0) {
                                completion(com.example.apiproject.utils.RESPONSE_STATUS.NO_CONTENT, null)

                            } else { // 데이터가 있다면

                                results.forEach { resultItem ->
                                    val resultItemObject = resultItem.asJsonObject
                                    val user = resultItemObject.get("user").asJsonObject
                                    val username : String = user.get("username").asString
                                    val likesCount = resultItemObject.get("likes").asInt
                                    val thumbnailLink = resultItemObject.get("urls").asJsonObject.get("thumb").asString
                                    val createdAt = resultItemObject.get("created_at").asString
                                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                    val formatter = SimpleDateFormat("yyyy년\nMM월 dd일")

                                    val outputDateString = formatter.format(parser.parse(createdAt))

                                    val photoItem = Photo(
                                        author = username,
                                        likesCount = likesCount,
                                        thumbnail = thumbnailLink,
                                        createdAt = outputDateString
                                    )
                                    parsedPhotoDataArray.add(photoItem)

                                }

                                completion(com.example.apiproject.utils.RESPONSE_STATUS.OKAY, parsedPhotoDataArray)
                            }
                        }
                    }
                }
            }
        })
    }
}