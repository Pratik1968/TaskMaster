package `in`.pratikshekhar.taskmaster.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Black10
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.PrimaryWithAlpha10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily


@Preview(showBackground = true)
@Composable
fun IconInput(modifier: Modifier = Modifier, label:String="label", placeholder: String="placeholder",icon:ImageVector = Icons.Filled.DateRange,onValueChange:(String)->Unit={}){
    Column(modifier = modifier

        .background(Color.White)

    ) {
        Label(text = label )
        Spacer(modifier = Modifier.height(10.dp))
        InputContainer(icon,placeholder,onValueChange)
    }
}
@Composable
private  fun InputContainer(icon:ImageVector = Icons.Filled.DateRange,placeholder: String,onValueChange: (String) -> Unit){
    Row(
        Modifier
            .border(
                BorderStroke(1.dp, PrimaryWithAlpha10),
                RoundedCornerShape(10.dp)
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(icon, contentDescription = "input icon", tint = Primary)
        Spacer(Modifier.width(5.dp))
        BasicTextField(enabled = false,value =placeholder , onValueChange =onValueChange, textStyle = TextStyle(color= Black10) )
    }
}
