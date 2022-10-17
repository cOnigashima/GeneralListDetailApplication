package com.generallistdetailapplication.data

//TODO　modify snake case
data class User(
    val id: Long,
    val name: String,
    val account_ids: List<Long>,
)

data class UserAccount(
    val id: Long,
    val user_id: Long,
    val name: String,
    val balance: Long,
)

// TODO create Adapter to parse value class
data class UserId(val value: Long)
data class AccountId(val value: Long)

