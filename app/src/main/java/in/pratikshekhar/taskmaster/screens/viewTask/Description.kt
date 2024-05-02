package `in`.pratikshekhar.taskmaster.screens.viewTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Gray2
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily

@Preview(showBackground = true)
@Composable
fun Description(text: String = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam . Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam"){
    Column (Modifier.fillMaxWidth()){
Label()
        DescriptionText(text = text)
    }
}
@Composable
fun Label(text:String ="Description"){
    Text(text, fontFamily= fontFamily, fontSize = 14.sp, color = Gray2, fontWeight = FontWeight.Medium)

}
@Composable
private  fun DescriptionText(text:String){
Text(text, fontFamily = fontFamily, fontSize = 12.sp, color = Gray2)
}