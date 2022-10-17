package com.generallistdetailapplication

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.generallistdetailapplication.data.User

@Composable
fun ExampleListScreen(
    viewModel: ExampleViewModel = viewModel(),
    onClickItem : (Long) -> Unit,
) {
    val userList = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User") },
            )
        }
    ) {
        ExampleList(
            list = userList.value,
            onClickItem,
        )
    }
}

@Composable
fun ExampleList(
    list: List<User>,
    onClickItem : (Long) -> Unit,
) {
    LazyColumn {
        items(list) { user ->
            ExampleItem(
                user = user,
                onClickItem = onClickItem
            )
        }
    }
}

@Composable
fun ExampleItem(
    user: User,
    onClickItem : (Long) -> Unit,
) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { onClickItem(user.id) }
            ),
    ) {
        Text(
            text = user.name,
            modifier = Modifier.padding(start = 12.dp),
        )
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExampleItem() {
    ExampleItem(
        user = User(1,"Bob", listOf(1,1)),
        onClickItem = {}
    )
}