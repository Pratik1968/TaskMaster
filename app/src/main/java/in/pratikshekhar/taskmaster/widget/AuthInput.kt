package `in`.pratikshekhar.taskmaster.widget

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Gray1
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.PrimaryWithAlpha10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)

@Composable
fun AuthInput(value:String="Enter your name", onChange:(String)->Unit={}, icon:ImageVector= Icons.Filled.Person,type:KeyboardType= KeyboardType.Text){
    var innerValue by remember {
    mutableStateOf(value)
}
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
                        Log.d("test","clicked")
                    if(!clicked)  innerValue=""
                        clicked =true
                    }
                    is PressInteraction.Release -> {

                    }

                }

                interactions.emit(interaction)
            }


            override fun tryEmit(interaction: Interaction): Boolean {
                return interactions.tryEmit(interaction)
            }}}
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Primary,
backgroundColor = PrimaryWithAlpha10,

    )



    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors){ Row(modifier = Modifier
        .height(54.dp)
        .fillMaxWidth()
        .border(
            border = BorderStroke(1.dp, color = PrimaryWithAlpha10),
            shape = RoundedCornerShape(10.dp)
        ),verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.2f)
            .clip(shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
            .background(Primary), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {

            Icon(icon, contentDescription = "icon", tint = Color.White)
        }
        BasicTextField(value = innerValue,onValueChange ={
            innerValue = if(innerValue==value) "" else it
            onChange(it)
        }, modifier = Modifier

            .fillMaxHeight()
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 10.dp)
            ,
interactionSource= interactionSource,
            cursorBrush = SolidColor(Color.Black),
            keyboardOptions = KeyboardOptions(keyboardType = type ),
            visualTransformation = if(type== KeyboardType.Password && clicked) PasswordVisualTransformation() else VisualTransformation.None,
            textStyle = TextStyle(fontFamily = fontFamily,textAlign = TextAlign.Left, fontSize = 12.sp, color = if(innerValue==value) Gray1 else Color.Black )
            )
    }}
}
