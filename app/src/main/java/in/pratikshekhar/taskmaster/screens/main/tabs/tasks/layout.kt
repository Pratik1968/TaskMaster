package `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks.TaskType.TaskTypeSection
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import kotlinx.datetime.LocalDate
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showBackground = true)
@Composable
internal fun Tasks(navController: NavHostController= NavHostController(LocalView.current.context)) {
    val month : DateFormat =  SimpleDateFormat("MM", Locale.getDefault())
    val year : DateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
    val day : DateFormat = SimpleDateFormat("dd", Locale.getDefault())

    val date: Date = Date()


    var currentDate by remember {
        mutableStateOf(
            LocalDate(
             year.format(date).toInt(),
             month.format(date).toInt(),
            day.format(date).toInt(),

        )
        )
    }
    val updateCurrentDate =fun (value:LocalDate) {
currentDate = value
    }
    Column(
        Modifier
            .fillMaxSize()

            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ){
TopBar(currentDate)
DateWithList(currentDate, updateCurrentDate)
        TaskTypeSection(currentDate)
    }
}
@Composable
private fun AddTask(modifier: Modifier=Modifier){
var status by remember {
mutableStateOf(false)
}
if(status){
    LocalNavController.current.navigate("addTask")

}

    Button(onClick = {
status =true
    },modifier= Modifier

        .height(45.dp),
shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(13.dp)
        , colors = ButtonDefaults.buttonColors(containerColor = Primary, contentColor = Color.White),

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
Icon(Icons.Filled.Add,contentDescription = "add",)
            Text(text = "Add Task", fontFamily = fontFamily, )

        }


    }
}
@Composable
private  fun TopBar(currentDate: LocalDate) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

        DateWithIcon(currentDate)
            AddTask()

    }
}