package com.example.mvvmapplication

import io.reactivex.Observable
import retrofit2.http.GET

interface IPostService {
    @GET("posts")
    fun getData() : Observable<List<PostModel>>
}