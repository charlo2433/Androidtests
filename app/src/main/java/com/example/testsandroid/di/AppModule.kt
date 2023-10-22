package com.example.testsandroid.di

import android.content.Context
import androidx.room.Room
import com.example.testsandroid.data.local.database.ProfileDatabase
import com.example.testsandroid.data.remote.ApiInterface
import com.example.testsandroid.util.Constants.BASE_URL
import com.example.testsandroid.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideProfileDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, ProfileDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideProfileDao(database: ProfileDatabase)= database.profileDao()

    @Singleton
    @Provides
    fun provideApiInterface(): ApiInterface{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
    }
}