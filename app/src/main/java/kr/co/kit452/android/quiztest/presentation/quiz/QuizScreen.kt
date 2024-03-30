package kr.co.kit452.android.quiztest.presentation.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.co.kit452.android.quiztest.ui.theme.Dimens

@Composable
fun QuizScreen (modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(Dimens.medium))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Blue)
                    .padding(16.dp)
                ,contentAlignment = Alignment.Center
            ) {
                Text(text = "2/10",
                    color = Color.White,)
            }
        }
        Spacer(modifier = Modifier.height(Dimens.medium))
        QuestionCard()

        ControlButtons()

        AnswersBlock()

        AnswerResultUi()
    }
}

@Composable
fun QuestionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = Dimens.cardMinHeight, max = Dimens.cardMaxHeight)
            .padding(bottom = Dimens.medium),
        shape = RoundedCornerShape(Dimens.medium),
        elevation = CardDefaults.cardElevation(Dimens.thin),
        border = BorderStroke(Dimens.hairline, Color.Black.copy(alpha = 0.2f)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = Dimens.cardMinHeight, max = Dimens.cardMaxHeight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "문제 문구",
                modifier = Modifier.padding(horizontal = Dimens.medium),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun ControlButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.extraLarge),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedButton(
            shape = RoundedCornerShape(Dimens.medium),
            border = BorderStroke(Dimens.hairline, Color.Black.copy(alpha = 0.2f)),
            contentPadding = PaddingValues(start = Dimens.large, end = Dimens.medium),
            onClick = {}
        ) {
            Text(
                text = "도움말",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Spacer(Modifier.width(Dimens.medium))
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "도움말",
                tint = Color.Black,
                modifier = Modifier
                    .size(Dimens.iconSmallSize)
                    .padding(top = Dimens.thin)
            )
        }

        OutlinedButton(
            shape = RoundedCornerShape(Dimens.medium),
            border = BorderStroke(Dimens.hairline, Color.Black.copy(alpha = 0.2f)),
            contentPadding = PaddingValues(start = Dimens.large, end = Dimens.medium),
            onClick = {}
        ) {
            Text(
                text = "스킵",
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Spacer(Modifier.width(Dimens.medium))
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = "스킵",
                tint = Color.Black,
                modifier = Modifier
                    .size(Dimens.iconSmallSize)
                    .padding(top = Dimens.thin)
            )
        }
    }
}

@Composable
fun AnswersBlock() {
    Row (
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column(modifier = Modifier.weight(1f)) {
            AnswerChoice()
            Spacer(modifier = Modifier.height(Dimens.medium))
            AnswerChoice()
        }
        Spacer(modifier = Modifier.width(Dimens.small))
        Column (modifier = Modifier.weight(1f)) {
            AnswerChoice()
            Spacer(modifier = Modifier.height(Dimens.medium))
            AnswerChoice()
        }
    }
}

@Composable
fun AnswerChoice() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {},
        shape = RoundedCornerShape(Dimens.medium),
        border = BorderStroke(Dimens.hairline, Color.Black.copy(alpha = 0.2f))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = false, onClick = {})
            Text(
                text = "보기",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun AnswerResultUi() {
    Icon(imageVector = Icons.Default.Check, contentDescription = null)
}
