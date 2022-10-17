package com.generallistdetailapplication

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.generallistdetailapplication.data.User
import com.generallistdetailapplication.data.UserAccount
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ExampleDetailScreen(
    viewModel: ExampleDetailViewModel = viewModel(),
    onBackButtonClicked : () -> Unit,
) {
    val userState = viewModel.userState.collectAsState()
    val totalBalance = viewModel.totalBalanceState.collectAsState()
    val accountListState = viewModel.userAccountsState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = { onBackButtonClicked() }) {
                        Icon(painterResource(R.drawable.ic_baseline_arrow_back_24), "menu")
                    }
                }
            )
        }
    ) {
        ExampleDetail(
            userState = userState.value,
            totalBalanceState = totalBalance.value,
            userAccountListState = accountListState.value
        )
    }
}

@Composable
fun ExampleDetail(
    userState: UserDetailUiState,
    totalBalanceState: UserBalanceUiState,
    userAccountListState: UserAccountListUiState,
) {
    Column() {
        if (userState is UserDetailUiState.Success)
            User(user = userState.user)

        Spacer(modifier = Modifier.size(12.dp))

        if (totalBalanceState is UserBalanceUiState.Success)
            TotalBalance(totalBalance = totalBalanceState.balance)


        Spacer(modifier = Modifier.size(120.dp))

        if (userAccountListState is UserAccountListUiState.Success)
            UserAccountList(userAccountList = userAccountListState.list)
    }
}

@Composable
fun User(user:User) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "User:",)
        Text(text = user.name,)
    }
}

@Composable
fun TotalBalance(totalBalance: Long) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Total:")

        Text(
            // format comma
            text = totalBalance.toString() + "円"
        )
    }
}


@Composable
fun UserAccountList(userAccountList: List<UserAccount>) {
    LazyColumn {
        items(userAccountList) { user ->
            UserAccountItem(account = user)
        }
    }

}

@Composable
fun UserAccountItem(
    account: UserAccount
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = account.name,)

            Text(
                // format comma
                text = account.balance.toString() + "円",
            )
        }
        Divider()
    }
}


@Preview
@Composable
fun ExampleAccountItem() {
    val account = UserAccount(1,3,"銀行",1)
    UserAccountItem(account = account)
}
