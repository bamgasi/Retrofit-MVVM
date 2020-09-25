package com.cocolab.retrofitexam

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 싱글톤으로 만들기 위해서 object로 생헝하면 됨.
 */
object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    /**
     * 로깅을 활성화 하기 위한 코드
     * 디버깅용
     */
    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
    }.build()

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: AlbumService by lazy {
        retrofitBuilder.build()
            .create(AlbumService::class.java)
    }

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // 로깅이 필요 없으면 이 줄은 필요 없음.
            //.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}