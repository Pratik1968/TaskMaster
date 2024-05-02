package `in`.pratikshekhar.taskmaster.screens.main.tabs.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Gray2
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily

@Preview(showBackground = true)
@Composable
fun NameAndGettingContainer(name:String="Pratik"){

    Column {
        NameText(text = name)
Greeting(text = "Have a nice day !")
    }
}
@Composable
private fun NameText(text:String){
    Text("Welcome $text", fontFamily= fontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)

}
@Composable
private  fun Greeting(text:String="Have a nice day !"){
    Text("$text", fontFamily= fontFamily, fontSize = 16.sp,color= Gray2)
}