package com.bam.myapplication

import androidx.lifecycle.LiveData
import com.bam.myapplication.models.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsRepository(private val postRestApi: PostRestApi) {

    var posts: List<PostResponse>? = mutableListOf()
    var error: String = ""

    fun getAll(success: () -> Unit){
        postRestApi.getAll().enqueue(object : Callback<List<PostResponse>>{
            override fun onResponse(
                call: Call<List<PostResponse>>,
                response: Response<List<PostResponse>>
            ) {
                posts = response.body()
                success()
            }

            override fun onFailure(call: Call<List<PostResponse>>, t: Throwable) {
                error = t.message.toString()
            }

        })
    }

}