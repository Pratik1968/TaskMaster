package `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks.TaskType

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.LocalSharedTask
import `in`.pratikshekhar.taskmaster.firebase.firestore.GetTaskDatabase
import `in`.pratikshekhar.taskmaster.ui.theme.PrimaryWithAlpha10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.GetCurrentDate
import `in`.pratikshekhar.taskmaster.widget.DailyTaskCard
import kotlinx.datetime.LocalDate

@Composable
internal  fun DailyTask(currentDate: LocalDate) {
   val uuid = Firebase.auth.currentUser?.uid
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
GetRequiredCard(uuid = uuid!!, currentDate = currentDate)
    }
}

@Composable
private fun Card(index:Int,text:String="Work Out",uuid:String,item:Map<String,Any>){
  val shareData = LocalSharedTask.current
    val nav = LocalNavController.current
    Box(
        Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, PrimaryWithAlpha10), RoundedCornerShape(20.dp))
            .padding(20.dp)
            .clickable(onClick = {
                shareData.title = item["title"].toString()
                shareData.description = item["description"].toString()
                shareData.startDate = item["startDate"]
                    .toString()
                    .toLong()
                shareData.endDate = item["endDate"]
                    .toString()
                    .toLong()
                shareData.microTaskId = ""
                shareData.status = item["status"].toString().toBoolean()
                shareData.id = item["id"].toString()
                nav.navigate("viewTask")
            })

    ) {
        Text(text, fontFamily= fontFamily,)
    }
}

@Composable
private fun GetRequiredCard(uuid: String, currentDate: LocalDate) {
    var listItemInfo by remember {
        mutableStateOf(listOf<Map<String,Any>>())

    }
    val query =   GetTaskDatabase(uid = uuid)
    query.getDailyTaskOnlyTitle(currentDate = currentDate, onFailureListener = {
        error(it)
    }, onSuccessListener = {
            data->listItemInfo = data

    } )

    if(listItemInfo.isNotEmpty()){
        val  count =0
        for(item in listItemInfo){
            Card(index = count , text = item["title"].toString(),uuid =uuid,item=item )
        }
    }else{
    }
}