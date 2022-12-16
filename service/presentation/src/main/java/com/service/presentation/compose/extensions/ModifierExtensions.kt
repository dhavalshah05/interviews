package com.service.presentation.compose.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

inline fun Modifier.clickableWithRipple(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(
        indication = rememberRipple(bounded = true),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

inline fun Modifier.clickableWithoutRipple(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}