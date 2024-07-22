package com.ibm.instashop.screens.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibm.instashop.ui.theme.PrimaryColor

@Composable
fun PrimaryButton(
    width: Int = 500,
    shapeSize: Float,
    btnText: String,
    color: Color = MaterialTheme.colors.PrimaryColor,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val modifier = Modifier
        .padding(top = 10.dp, bottom = 10.dp)
        .height(55.dp)
        .clip(RoundedCornerShape(shapeSize.dp))
        .run {
            if (width == 500) {
                fillMaxWidth()
            } else {
                width(width.dp)
            }
        }

    Button(
        modifier = modifier,
        onClick = {
            onClick()
                  },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = Color.White
        ),
        enabled = enabled
    ) {
        Text(text = btnText, fontSize = 16.sp)
    }
}
