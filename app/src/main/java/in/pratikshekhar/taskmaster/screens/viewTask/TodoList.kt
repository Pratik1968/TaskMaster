package `in`.pratikshekhar.taskmaster.screens.viewTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.firebase.realtime.MicroTaskDatabaseHandler
import `in`.pratikshekhar.taskmaster.model.MicroTask
import `in`.pratikshekhar.taskmaster.widget.DailyTaskCard

@Preview(showBackground = true)
@Composable
fun TodoList(microTaskId: String="") {
    val uuid = Firebase.auth.currentUser?.uid
Column(
    Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(Color.White)) {
Label("Todo List")
    Spacer(Modifier.height(10.dp))
    if (uuid != null) {
        TaskList(microTaskId = microTaskId, uuid = uuid)
    }
}
}
@Composable
private fun TaskList(microTaskId: String, uuid: String) {

  val handler =  MicroTaskDatabaseHandler(uuid=uuid, microTaskId = microTaskId)
   var taskListInfo by remember {
mutableStateOf(listOf<MicroTask>())
   }
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            ,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        handler.get {
            taskListInfo = it
        }
      var   count = 0
      for (item in taskListInfo){
        DailyTaskCard(index = count ,text = item.title,uuid,microTaskId,item.status)
count++
      }




    }
}