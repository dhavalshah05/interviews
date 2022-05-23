package com.template.app.compose.views.interviewitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.template.app.compose.views.texts.AppTextStyle
import com.template.app.domain.interviews.models.InterviewResult
import com.template.app.ui.interviews.models.UiInterviewResult

@Composable
fun InterviewResultStatus(
    modifier: Modifier = Modifier,
    interviewResult: InterviewResult,
    fontSize: Float = 10F,
    width: Dp? = null,
) {
    val result = remember(interviewResult.name) {
        UiInterviewResult.from(interviewResult)
    }

    val paddingH = (fontSize * 4) / 10
    val paddingV = (fontSize * 2) / 10
    val radius = (fontSize * 2) / 10

    Text(
        text = stringResource(id = result.textResId).uppercase(),
        style = AppTextStyle.RegularPrimary.copy(
            color = colorResource(id = result.textColorResId),
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center,
        ),
        modifier = modifier
            .then(if (width != null) Modifier.width(width) else Modifier)
            .background(colorResource(id = result.bgColorResId), RoundedCornerShape(radius.dp))
            .padding(horizontal = paddingH.dp, vertical = paddingV.dp)
    )
}