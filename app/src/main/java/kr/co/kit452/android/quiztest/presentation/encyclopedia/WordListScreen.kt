package kr.co.kit452.android.quiztest.presentation.encyclopedia

import WordDetailScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.kit452.android.quiztest.ui.theme.Dimens

enum class WordScreen {
    Word,
    Detail,
    Search
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordListScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "단어 목록") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        content = { contentPadding ->
            NavHost(navController, startDestination = WordScreen.Word.name ) {
                composable(WordScreen.Word.name) {
                    WordList(modifier = Modifier.padding(contentPadding), navController = navController)
                }
                composable("${WordScreen.Detail.name}/{word}") { backStackEntry ->
                    val word = backStackEntry.arguments?.getString("word")
                    word?.let { WordDetailScreen(word = it,navController) }
                }
            }
        }
    )
}



@Composable
fun WordList(modifier: Modifier = Modifier, navController: NavController){
    // 야구 용어 목록
    val words = remember { getBaseballTerms() }

    // 검색어 상태
    val searchKeyword = remember { mutableStateOf("") }

    LazyColumn(modifier) {
        // 검색 박스
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextFieldWithSearchIcon(
                    value = searchKeyword.value,
                    onValueChange = { searchKeyword.value = it },
                    label = { Text("검색") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }

        // 야구 용어 목록 표시
        items(words.filter { it.contains(searchKeyword.value, ignoreCase = true) }) { word ->
            WordItem(word, navController)
            Spacer(modifier = Modifier.height(Dimens.medium))
        }
    }
}

@Composable
fun TextFieldWithSearchIcon(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = label,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "검색 아이콘"
            )
        }
    }
}




fun getBaseballTerms(): List<String> {
    return listOf(
        "홈런", "볼카운트", "스트라이크", "타석", "병살타", "좌익수", "우익수",
        "유격수", "적시타", "보크", "투구", "도루", "볼", "스퀴즈",
        "희생타", "페어볼", "희생플라이", "안타", "병살", "투수공"
    )
}

@Composable
fun WordItem(word: String, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navController.navigate("${WordScreen.Detail.name}/$word")
            }
    ) {
        Text(
            text = word,
            modifier = Modifier.padding(16.dp)
        )
    }
}