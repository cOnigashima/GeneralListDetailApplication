package com.generallistdetailapplication

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generallistdetailapplication.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


// TODO DI Repository
class ExampleDetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val userId: Long = checkNotNull(savedStateHandle["userId"])

    init {
        fetchUser(userId)
        fetchUserAccount(userId)
    }

    private val _userState = MutableStateFlow<UserDetailUiState>(UserDetailUiState.Loading)
    val userState: StateFlow<UserDetailUiState> = _userState

    private val _userAccountsState = MutableStateFlow<UserAccountListUiState>(UserAccountListUiState.Loading)
    val userAccountsState: StateFlow<UserAccountListUiState> = _userAccountsState

    val totalBalanceState: StateFlow<UserBalanceUiState> = _userAccountsState
        .filterIsInstance<UserAccountListUiState.Success>()
        .map { data ->
            UserBalanceUiState.Success(balance = data.list.sumOf { it.balance })
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, initialValue = UserBalanceUiState.Loading)

    private fun fetchUser(id: Long) {
        viewModelScope.launch {
            val list = ExampleRepositoryImpl().fetchUserDetail(id)
            if (list.succeeded) {
                val success = list as Result.Success
                _userState.emit(UserDetailUiState.Success(success.data))
            }
        }
    }

    private fun fetchUserAccount(id: Long) {
        viewModelScope.launch {
            val list = ExampleRepositoryImpl().fetchUserAccountDetail(id)
            if (list.succeeded) {
                val success = list as Result.Success
                _userAccountsState.emit(UserAccountListUiState.Success(success.data))
            }
        }
    }
}


// this State Wrapper may be redundant. Integrate with Result.class
sealed class UserDetailUiState {
    object Loading : UserDetailUiState()
    data class Success(val user: User) : UserDetailUiState()
    data class Error(val exception: Throwable) : UserDetailUiState()
}

sealed class UserBalanceUiState {
    object Loading : UserBalanceUiState()
    data class Success(val balance: Long) : UserBalanceUiState()
    data class Error(val exception: Throwable) : UserBalanceUiState()
}

sealed class UserAccountListUiState {
    object Loading : UserAccountListUiState()
    data class Success(val list: List<UserAccount>) : UserAccountListUiState()
    data class Error(val exception: Throwable) : UserAccountListUiState()
}

