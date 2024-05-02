package `in`.pratikshekhar.taskmaster.screens.viewTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.LocalSharedTask
import `in`.pratikshekhar.taskmaster.firebase.firestore.UpdateTask
import `in`.pratikshekhar.taskmaster.model.TaskAttr
import `in`.pratikshekhar.taskmaster.model.TaskAttrForDatabase
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import kotlinx.datetime.LocalDate

@Composable
fun ViewTaskScreen(navController: NavController=NavHostController(LocalView.current.context),showDailyTask:Boolean = true){
val dataGot = LocalSharedTask.current
    MainContainer(dataGot , if(dataGot.microTaskId=="") false else true)
}

@Composable
private fun MainContainer(
    taskGot: TaskAttrForDatabase,
    showDailyTask: Boolean
) {
    val   task: TaskAttr = TaskAttr(
      startDate =   LocalDate.fromEpochDays(taskGot.startDate.toInt()),
        endDate = LocalDate.fromEpochDays(taskGot.endDate.toInt()),
        title = taskGot.title,
        description = taskGot.description,
        listOfMicroTask = listOf()
    )

    println(taskGot.microTaskId)
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())

        , verticalArrangement = Arrangement.spacedBy(22.dp)

    ) {
Title(text = task.title)
        DateContainer(startDate = task.startDate, endDate = task.endDate)

        Description(task.description)
        if(showDailyTask){ TodoList(taskGot.microTaskId) }
   println("Task got "+ taskGot.status)
        if(!taskGot.status){
       FinishButton(taskGot.id)
   }
    }
}

@Composable
private  fun Title(text:String){
    Text(text, fontFamily= fontFamily,color= Primary, fontSize = 32.sp, fontWeight = FontWeight.Bold)
}
@Composable
private fun FinishButton(id: String) {
    val uuid = Firebase.auth.currentUser?.uid
    val nav= LocalNavController.current
val updateDb = UpdateTask(uid = uuid!!)
    Button(
        onClick = {
updateDb.setStatus(id,true , onSuccessListener = {
    nav.navigate("main")
}, onFailureListener = {
    error(it)
})
        },
       modifier= Modifier
           .fillMaxWidth()
           .height(60.dp),
        colors = ButtonDefaults.buttonColors(Primary),
        shape = RoundedCornerShape(10.dp)
    ){
        Text("Finish")
    }
}