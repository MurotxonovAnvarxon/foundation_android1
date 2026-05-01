package com.devuz.foundation_android.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devuz.foundation_android.ui.theme.RickAction
import com.devuz.network.models.response.home.RootResultCharacter

@Suppress("ParamsComparedByRef")
@Composable
fun CharacterGridItem(
    modifier: Modifier,
    character: RootResultCharacter,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(listOf(Color.Transparent, RickAction)),
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Box {
            CharacterImage(imageUrl = character.image ?: "")

            var color = Color.Yellow

            when (character.status) {
                "Alive" -> color = Color.Green
                "Dead" -> color = Color.Red
                "Unknown" -> color = Color.Yellow
            }

            CharacterStatusCircle(
                color = color,
                modifier = Modifier.padding(start = 6.dp, top = 6.dp)
            )
        }
        Text(
            text = character.name ?:"",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 26.sp,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}
