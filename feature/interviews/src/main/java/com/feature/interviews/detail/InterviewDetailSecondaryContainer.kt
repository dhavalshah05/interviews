package com.feature.interviews.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.service.presentation.R
import com.service.presentation.compose.AppTextStyle
import com.service.presentation.compose.extensions.clickableWithoutRipple

@Preview
@Composable
private fun PreviewInterviewDetailPrimaryContainer() {
    InterviewDetailSecondaryContainer(
        label = "Interview comments",
        value = "Here are some interview comments to look into.",
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
internal fun InterviewDetailSecondaryContainer(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
) {
    val isOpen = remember {
        mutableStateOf(false)
    }

    val rotate = animateFloatAsState(targetValue = if (isOpen.value) -180F else 0F)

    Column(
        modifier = modifier
            .heightIn(min = 50.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.cardBackground), RoundedCornerShape(4.dp))
            .clickableWithoutRipple { isOpen.value = !isOpen.value },
    ) {
        Row(
            modifier = Modifier
                .heightIn(min = 50.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = AppTextStyle.RegularPrimary,
                modifier = Modifier.weight(1F),
            )
            if (value.isNotBlank()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                    tint = colorResource(id = R.color.primary),
                    modifier = Modifier.rotate(rotate.value)
                )
            } else {
                Text(
                    text = stringResource(id = R.string.label_not_available),
                    style = AppTextStyle.RegularSecondary,
                )
            }
        }
        if (value.isNotBlank() && isOpen.value) {
            SelectionContainer(
                modifier = Modifier.padding(horizontal = 20.dp).padding(bottom = 15.dp)
            ) {
                Text(
                    text = value,
                    style = AppTextStyle.SemiBoldPrimary.copy(
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    ),
                    color = colorResource(id = R.color.textPrimary),
                )
            }
        }
    }
}
