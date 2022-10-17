package com.generallistdetailapplication.data

import retrofit2.http.GET
import retrofit2.http.Path


// TODO wrap Flow
// TODO wrap Result corresponding to status
interface ExampleRemoteDataSource {
    @GET("/users")
    suspend fun getList(): List<User>

    @GET("/users/{id}")
    suspend fun getUserDetail(@Path("id") id: Long): User

    @GET("/users/{id}/accounts")
    suspend fun getUserAccountList(@Path("id") id: Long): List<UserAccount>

    @GET("/users/accounts/{id}")
    suspend fun getAccountDetail(@Path("id") id: Long): UserAccount
}
