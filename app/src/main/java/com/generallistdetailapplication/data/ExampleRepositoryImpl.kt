package com.generallistdetailapplication.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit

// TODO Inject Service / retrofit /moshi
class ExampleRepositoryImpl : ExampleRepository {
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(getClient())
        .build()

    private fun getClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val service = retrofit.create(ExampleRemoteDataSource::class.java)

    override suspend fun fetchUserList(): Result<List<User>> {
        return Result.Success(service.getList())
    }

    override suspend fun fetchUserDetail(id:Long): Result<User> {
        return Result.Success(service.getUserDetail(id))
    }

    override suspend fun fetchUserAccountDetail(id:Long): Result<List<UserAccount>> {
        return Result.Success(service.getUserAccountList(id))
    }

    companion object {
        private const val BASE_URL = ""
    }
}