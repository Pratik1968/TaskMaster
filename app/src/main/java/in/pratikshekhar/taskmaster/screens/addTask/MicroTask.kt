package `in`.pratikshekhar.taskmaster.screens.addTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.pratikshekhar.taskmaster.model.MicroTask
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.widget.CustomInput
import `in`.pratikshekhar.taskmaster.widget.Label

@Preview(showBackground = true)
@Composable
fun MicroTask(){
    MainContainer()
}
@Composable
private  fun MainContainer(){
Column (
    Modifier
        .fillMaxWidth()
        .wrapContentHeight()){
Title()
    Spacer(modifier = Modifier.height(10.dp))
List()

}
}
@Composable
private fun Title(){
Label(text = "Micro Task")
}
@Composable
private fun List(){
var count by remember {
    mutableIntStateOf(1)
}
    val taskData = LocalTaskData.current
    var innerList by remember {
        mutableStateOf(listOf<MicroTask>())
    }
val updateMicroTaskList = fun (task:MicroTask){innerList+=task}
LaunchedEffect(key1 = innerList) {
    taskData.listOfMicroTask  = innerList

}
    for(index in 1..count){
        TaskName(index,onValueChange = {},updateMicroTaskList)
        Spacer(modifier = Modifier.height(10.dp))
    }
AddButton(onClick ={
    count++
})
}
@Composable
private fun TaskName(id:Int,onValueChange: (String) -> Unit = {}, updateMicroTaskList: (MicroTask) -> Unit, ){
    var mircoTask by remember {
        mutableStateOf(MicroTask(
            id=id,
            title = "",
            status = false
        ))
    }

LaunchedEffect(key1 = mircoTask) {
    updateMicroTaskList(mircoTask)
}

Box(Modifier.height(50.dp)){

   CustomInput (placeholder= mircoTask.title, onValueChange = {

        mircoTask.title = it
    })

}
}
@Composable
private fun AddButton(onClick:()->Unit={}){
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
        Button(modifier = Modifier
            .height(50.dp)
            .width(50.dp),onClick = onClick,colors= ButtonDefaults.buttonColors(Primary), shape = RoundedCornerShape(10.dp), contentPadding = PaddingValues(0.dp)) {
          Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)  { Icon(Icons.Filled.Add, contentDescription = "add1", tint = Color.White) }
        }
    }
}