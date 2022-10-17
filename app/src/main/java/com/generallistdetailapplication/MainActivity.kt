package com.generallistdetailapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.generallistdetailapplication.ui.theme.GeneralListDetailApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeneralListDetailApplicationTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                ExampleNavHost(navController = navController)
                Surface(color = MaterialTheme.colors.background) {
                    ExampleNavHost()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GeneralListDetailApplicationTheme {
        ExampleListScreen(
            onClickItem = {}
        )
    }
}

