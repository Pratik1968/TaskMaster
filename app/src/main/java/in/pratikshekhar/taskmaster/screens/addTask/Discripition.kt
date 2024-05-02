package `in`.pratikshekhar.taskmaster.screens.addTask

import android.util.Log
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.pratikshekhar.taskmaster.widget.CustomInput
import `in`.pratikshekhar.taskmaster.widget.Input
import `in`.pratikshekhar.taskmaster.widget.Label
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
@Preview(showBackground = true)
@Composable
fun Description(onValueChange:(String)->Unit={}){
val task = LocalTaskData.current

    Column{
    Label(text = "Description")
    Spacer(modifier = Modifier.height(10.dp))

   CustomInput(Modifier.fillMaxHeight(0.2f), placeholder = task.description,onValueChange = {
        task.description= it
    })
}
}
