package com.example.ticket_system_employee.presentation.commons.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.ticket_system_employee.R
import com.example.ticket_system_employee.presentation.commons.models.DialogModel
import com.example.ticket_system_employee.presentation.theme.Black
import com.example.ticket_system_employee.presentation.theme.TextStyles

object Dialogues {
    @Composable
    private fun TitleDialog(dialogModel: DialogModel, modifier: Modifier) {
        Text(
            text = dialogModel.title,
            style = TextStyles.titleStyle(dialogModel.color),
            modifier = modifier.fillMaxWidth().wrapContentHeight()
        )
    }

    @Composable
    private fun MessageContent(dialogModel: DialogModel) {
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
    fun DialogErrorLogin(dialogModel: DialogModel) {
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

}