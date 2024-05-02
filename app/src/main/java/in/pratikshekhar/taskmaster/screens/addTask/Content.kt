package `in`.pratikshekhar.taskmaster.screens.addTask

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.firebase.firestore.TaskToDatabase
import `in`.pratikshekhar.taskmaster.model.TaskAttr
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.widget.NormalInput
import kotlinx.datetime.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
internal  val LocalTaskData= compositionLocalOf<TaskAttr>  { error("Task data not found") }
internal val LocalUpdateTaskData = compositionLocalOf<(task:TaskAttr)->Unit>{ error("Task update function not found") }
internal  var LocalPressStatus = compositionLocalOf <Boolean>{error("Press status not found ")  }
internal  var LocalSetPressStatus = compositionLocalOf <(Boolean)->Unit>{error("Press status not found ")  }

@Preview(showBackground = true)
@Composable
fun Content() {
var  taskData by remember {
    mutableStateOf(TaskAttr(    LocalDate(2024,1,1),
        LocalDate(2024,1,10),
        title="",
        category="PRIORITY",
        description = "",
listOf()
        ))
}
    var pressStatus by remember {
        mutableStateOf(false)
    }
    val setPressStatus = fun(value:Boolean){ pressStatus = value}
    val updateTaskData =fun ( data:TaskAttr) {
taskData = data
    }
    var showMicroTask by remember {
        mutableStateOf(true)
    }
    val updateShowMicroTask = fun(value:Boolean){showMicroTask=value}
    LaunchedEffect(key1 = taskData) {
        println(taskData)
    }
    CompositionLocalProvider(LocalTaskData provides taskData, LocalUpdateTaskData provides updateTaskData,
        LocalPressStatus provides  pressStatus, LocalSetPressStatus provides setPressStatus ){Column(
        Modifier
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
            .background(Color.White)
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 0.dp)
            .verticalScroll(rememberScrollState())
    ,


    verticalArrangement = Arrangement.spacedBy(10.dp)

) {
StartAndEndDate()
    TitleContainer()
    Category(updateShowMicroTask)
    Description()
  if(showMicroTask) {

      MicroTask()
  }

    CreateTask()
    Spacer(modifier =Modifier.height( 10.dp))

}}


}

@Composable
private  fun TitleContainer() {
   val task = LocalTaskData.current
    NormalInput(
        Modifier
            .fillMaxWidth()
            .height(80.dp), placeholder =task.title, label = "Title",
        onValueChange = {
            task.title = it
        })
}
@Composable
private fun CreateTask(){
    val task = LocalTaskData.current
    var pressStatus = LocalPressStatus.current
val setPressStatus = LocalSetPressStatus.current
    val nav = LocalNavController.current
    Button(onClick = {
setPressStatus(true)
        val uuid = Firebase.auth.currentUser?.uid
println("pressed")
        if (uuid != null ) {
            TaskToDatabase(uuid,task, onSuccessListener =  {
                 Log.d("data","success")
                nav.navigate("main")
            }, onFailureListener = {
error(it)
            })
        }

    }, modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
colors = ButtonDefaults.buttonColors(
    containerColor = Primary,
    )
        ){
        Text("Create Task", fontFamily= fontFamily, color = Color.White)
    }
}
