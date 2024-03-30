package kr.co.kit452.android.quiztest.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.kit452.android.quiztest.presentation.encyclopedia.WordListScreen
import kr.co.kit452.android.quiztest.presentation.quiz.QuizScreen

enum class Screen {
    Quiz,
    Words,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("야구 퀴즈 백과") })
        },
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentDestination?.route

                NavigationBarItem(
                    selected = currentRoute == Screen.Quiz.name,
                    onClick = {
                        navController.navigate(Screen.Quiz.name)
                    },
                    label = { Text("퀴즈") },
                    icon = {
                        Icon(Icons.Default.Edit, "quiz")
                    }
                )

                NavigationBarItem(
                    selected = currentRoute == Screen.Words.name,
                    onClick = {
                        navController.navigate(Screen.Words.name)
                    },
                    label = { Text("단어장") },
                    icon = {
                        Icon(Icons.AutoMirrored.Filled.List, "words")
                    }
                )
            }
        },
        content = { paddingValues ->
            NavHost(navController, startDestination = Screen.Quiz.name ) {
                composable(Screen.Quiz.name) {
                    QuizScreen(modifier = Modifier.padding(paddingValues))
                }
                composable(Screen.Words.name) {
                    WordListScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }

    )
}