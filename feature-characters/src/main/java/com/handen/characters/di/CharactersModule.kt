package com.handen.characters.di

import com.google.gson.Gson
import com.handen.characters.data.remote.RemoteApi
import com.handen.characters.data.remote.RemoteDataService
import com.handen.characters.data.remote.RemoteDataServiceImpl
import com.handen.characters.data.usecases.GetCharactersUseCaseImpl
import com.handen.characters.domain.usecases.GetCharactersUseCase
import com.handen.characters.util.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersModule {

    @Binds
    @Singleton
    abstract fun bindGetCharactersUseCaseImpl(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    @Singleton
    abstract fun bindRemoteDataService(remoteDataServiceImpl: RemoteDataServiceImpl): RemoteDataService

    companion object {
        @Provides
        fun provideOkHttp(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
        }

        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(BASE_URL).build()

        @Provides
        fun provideRemoteApi(retrofit: Retrofit): RemoteApi =
            retrofit.create(RemoteApi::class.java)
    }
}