package com.khosousi.dictionary.feature.search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khosousi.dictionary.feature.search.domain.model.Meaning
import com.khosousi.dictionary.ui.theme.Purple40

/**
 * Created by Morteza Rastgoo on 2023-04-17.
 */
@ExperimentalTextApi
@Composable
fun MeaningItem(
    modifier: Modifier,
    meaning: Meaning
) {
    Column(
        modifier = Modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                meaning.partOfSpeech
                    ?: "",
                style = TextStyle(
                    fontSize = 20.sp,
                )
            )
            Spacer(modifier = Modifier.size(10.dp))
        }
        meaning.definitions?.mapIndexed { index, it ->
            Text(
                ("${index + 1}. " + it?.definition),
                style = TextStyle(
                    fontSize = 16.sp,
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Blue, Purple40, Color.Cyan)
                    )
                )
            )
            Text(
                it?.example
                    ?: "",
                style = TextStyle(
                    fontSize = 14.sp,
                )
            )
        }

    }


}