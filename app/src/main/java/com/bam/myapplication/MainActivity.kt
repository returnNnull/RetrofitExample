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

        val repository = PostsRepository(postApi)
        val adapter = PostsListAdapter()
        binding.postListRecyclerView.adapter = adapter


        repository.getAll{
            adapter.setPosts(repository.posts)
        }

    }
}