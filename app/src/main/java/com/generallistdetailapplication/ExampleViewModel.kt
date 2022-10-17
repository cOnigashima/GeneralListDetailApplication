package com.generallistdetailapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generallistdetailapplication.data.ExampleRepositoryImpl
import com.generallistdetailapplication.data.Result
import com.generallistdetailapplication.data.User
import com.generallistdetailapplication.data.succeeded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


// TODO DI Repository
class ExampleViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(emptyList<User>())
    val uiState: StateFlow<List<User>> = _uiState

    init {
        fetchList()
    }

    private fun fetchList(){
        viewModelScope.launch {
            val list = ExampleRepositoryImpl().fetchUserList()
            if (list.succeeded){
                val success = list as Result.Success
                _uiState.emit(success.data)
            }
        }
    }
}

