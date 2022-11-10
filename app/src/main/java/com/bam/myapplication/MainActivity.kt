package com.bam.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bam.myapplication.databinding.ActivityMainBinding
import com.bam.myapplication.models.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val postApi = retrofit.create(PostRestApi::class.java)


        binding.button.setOnClickListener {
            getById(postApi)
        }

        binding.button2.setOnClickListener {
            insertPost(postApi)
        }

    }

    private fun insertPost(postApi: PostRestApi) {
        val post = PostResponse(1, 101, "text", "body")

        val result = postApi.insert(post)

        result.enqueue(object : Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        result.enqueue(object : Callback<PostResponse> {
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                binding.textView.text = response.raw().code().toString()
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                binding.textView.text = t.message
            }

        })

    }


    private fun getById(postApi: PostRestApi) {
        val id = binding.editTextTextPersonName.text.toString().toInt()
        val result = postApi.getById(id)
        result.enqueue(object : Callback<PostResponse> {
            override fun onResponse(
                call: Call<PostResponse>,
                response: Response<PostResponse>
            ) {
                binding.textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
            }
        })
    }
}