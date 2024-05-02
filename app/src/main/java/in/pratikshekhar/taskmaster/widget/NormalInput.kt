package `in`.pratikshekhar.taskmaster.widget

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.PrimaryWithAlpha10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

@Preview(showBackground = true)
@Composable
fun NormalInput(modifier: Modifier = Modifier,label:String="label",placeholder: String="",onValueChange: (String) -> Unit={}) {
    var innerValue by remember {
        mutableStateOf(placeholder)
    }
    val updateInnerValue = fun(value:String){innerValue =value}
    var clicked by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        object : MutableInteractionSource {
            override val interactions = MutableSharedFlow<Interaction>(
                extraBufferCapacity = 16,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

            override suspend fun emit(interaction: Interaction) {
                when (interaction) {
                    is PressInteraction.Press -> {
                        Log.d("test", "clicked")
                        if (!clicked) innerValue = ""
                        clicked = true
                    }

                    is PressInteraction.Release -> {

                    }

                }

                interactions.emit(interaction)
            }


            override fun tryEmit(interaction: Interaction): Boolean {
                return interactions.tryEmit(interaction)
            }
        }
    }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Primary,
        backgroundColor = PrimaryWithAlpha10,

        )
CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors){
    Column(modifier = modifier.background(Color.White)) {
        Label(text = label)
        Spacer(Modifier.height(10.dp))
        Input(placeholder = innerValue, onValueChange = onValueChange, interactionSource = interactionSource, updateInnerValue = updateInnerValue)


    }
}
}
@Composable
fun Input(modifier: Modifier = Modifier, placeholder: String, maxlines: Int = 1,onValueChange: (String) -> Unit,interactionSource: MutableInteractionSource,updateInnerValue:(String)->Unit){
    BasicTextField(modifier = modifier.fillMaxWidth().fillMaxHeight().border(BorderStroke(1.dp, PrimaryWithAlpha10),
        RoundedCornerShape(10.dp)

    ).padding(10.dp),
        interactionSource= interactionSource,
        cursorBrush = SolidColor(Color.Black),
        value = placeholder, maxLines = maxlines, onValueChange ={
updateInnerValue(it)
            onValueChange(it)})
}
@Composable
fun Label(text:String){
    Text(text, fontFamily = fontFamily, color = Primary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
}