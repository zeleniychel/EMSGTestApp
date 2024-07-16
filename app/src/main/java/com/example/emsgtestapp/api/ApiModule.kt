package com.example.emsgtestapp.api

import com.example.emsgtestapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    companion object {
        private const val BASE_URL = "${BuildConfig.BASE_URL}"
    }

    @Singleton
    @Provides
    fun provideLogging() = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp (
        logging:HttpLoggingInterceptor,
    ) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val newRequestBuilder = chain.request().newBuilder()
            newRequestBuilder.addHeader("Api-Key", "c1378193-bc0e-42c8-a502-b8d66d189617")
            val newRequest = newRequestBuilder.build()
            return@addInterceptor chain.proceed(newRequest)
        }
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    @Singleton
    @Provides
    fun provideUsersApi(
        retrofit: Retrofit
    ): UsersApi = retrofit.create()
}