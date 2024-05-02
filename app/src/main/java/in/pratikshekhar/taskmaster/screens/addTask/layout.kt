package `in`.pratikshekhar.taskmaster.screens.addTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.SetStatusBarColor

@Composable
fun AddTask(){
SetStatusBarColor(color = Primary)
    MainContainer()
}
@Preview(showBackground = true)
@Composable
private fun MainContainer(){
    Column(
        Modifier
            .fillMaxSize()
            .background(Primary)

        ,

        ){
        Box(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center) {
            Title()

        }
        Spacer(modifier = Modifier.fillMaxHeight(0.01f))
        Content()

    }
}
@Composable
   private fun Title(){

    Text("Add Task", fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White )
}
