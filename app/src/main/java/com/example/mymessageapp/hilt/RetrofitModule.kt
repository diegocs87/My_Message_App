package com.example.mymessageapp.hilt

import com.example.mymessageapp.model.network.PostsAPIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun retrofitProvider(): Retrofit{
        return Retrofit.Builder().
        baseUrl("https://jsonplaceholder.typicode.com/").
        addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun APIClientProvider(retrofit: Retrofit): PostsAPIClient {
        return retrofit.create(PostsAPIClient::class.java)
    }
}