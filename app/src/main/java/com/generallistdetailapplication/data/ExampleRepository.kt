package com.generallistdetailapplication.data

interface ExampleRepository {
    suspend fun fetchUserList(): Result<List<User>>
    suspend fun fetchUserDetail(id: Long): Result<User>
    suspend fun fetchUserAccountDetail(id: Long): Result<List<UserAccount>>
}