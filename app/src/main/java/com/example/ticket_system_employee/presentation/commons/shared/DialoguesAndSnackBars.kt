package com.example.ticket_system_employee.presentation.commons.shared

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.presentation.commons.models.DialogModel
import com.example.ticket_system_employee.presentation.commons.models.SnackBarModel
import com.example.ticket_system_employee.presentation.theme.Black
import com.example.ticket_system_employee.presentation.theme.TextStyles
import com.example.ticket_system_employee.presentation.theme.White

object DialoguesAndSnackBars {
    @Composable
    private fun TitleDialog(dialogModel: DialogModel, modifier: Modifier) {
        Text(
            text = dialogModel.title,
            style = TextStyles.titleStyle(dialogModel.color),
            modifier = modifier.fillMaxWidth().wrapContentHeight()
        )
    }

    @Composable
    fun MessageContent(dialogModel: DialogModel) {
        Text(
            text = dialogModel.message,
            style = TextStyles.contentStyle(Black),
            modifier = Modifier.fillMaxWidth().wrapContentWidth()
        )
    }

    @Composable
    private fun DialogAcceptButton(dialogModel: DialogModel) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            val buttonText = stringResource(R.string.txt_accept)
            SharedComponents.OutlinedDialogButton(
                modifier = Modifier.wrapContentWidth(), onClick = { dialogModel.confirmAction.invoke() },
                text = buttonText, color = dialogModel.color
            )
        }
    }

    @Composable
    fun GenericDialog(dialogModel: DialogModel) {
        val shape = GenericProperties.roundenShapeDefault
        val borderStroke = GenericProperties.borderStrokeDynamic(dialogModel.color)
        val cardColors = GenericProperties.whiteCardColor
        Dialog(onDismissRequest = dialogModel.dismissRequest, properties = GenericProperties.noCancelableProperty) {
            Card(shape = shape, border = borderStroke, colors = cardColors,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    TitleDialog(dialogModel, Modifier.fillMaxWidth())
                    Spacer(Modifier.height(14.dp))
                    MessageContent(dialogModel)
                    Spacer(Modifier.height(14.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End
                    ) { DialogAcceptButton(dialogModel) }
                }
            }
        }

    }

    @Composable
    fun SnackBarWithoutAction(snackBarModel: SnackBarModel, snackBarVisible: Boolean) {
        val animationTime = (snackBarModel.duration / 2).toInt()
        AnimatedVisibility(
            visible = snackBarVisible,
            enter = scaleIn(animationSpec = tween(durationMillis = animationTime)),
            exit = scaleOut(animationSpec = tween(durationMillis = animationTime))
        ) {
            Card(
                modifier = snackBarModel.modifier.padding(horizontal = 16.dp, vertical = 20.dp),
                colors = GenericProperties.cardColorsSnackBars(snackBarModel.color)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = snackBarModel.text, style = TextStyles.snackBarTextStyle(White),
                        modifier = Modifier.padding(12.dp).fillMaxWidth(), maxLines = 2
                    )
                }
            }
        }
        DisposableEffect(Unit) {
            if (snackBarVisible) {
                val handler = Handler(Looper.getMainLooper())
                val runnable = Runnable { snackBarModel.dismissAction.invoke() }
                handler.postDelayed(runnable, snackBarModel.duration)
                onDispose { handler.removeCallbacks(runnable) }
            } else { onDispose { } }
        }

    }

}