package `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks.TaskType

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.LocalSharedTask
import `in`.pratikshekhar.taskmaster.firebase.firestore.GetTaskDatabase
import `in`.pratikshekhar.taskmaster.model.TaskAttrForDatabase
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.PrimaryWithAlpha10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.GetCurrentDate
import `in`.pratikshekhar.taskmaster.util.StripDownStringTo3LetterWithCapitalFirstLetter
import kotlinx.datetime.LocalDate
@Composable
internal  fun PriorityTask(currentDate: LocalDate) {
   val uuid = Firebase.auth.currentUser?.uid
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            ,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {

       GetRequiredCard(uuid = uuid!!,currentDate) {

       }
    }
}


@Composable
private  fun Card(task:TaskAttrForDatabase ){
    var shareData =     LocalSharedTask.current
    val nav = LocalNavController.current
//
    //    Surface(
//     shadowElevation = 0.1.dp,
//        shape = RoundedCornerShape(10.dp),
//    ) {
        Row(
            Modifier
                .border(BorderStroke(1.dp, PrimaryWithAlpha10), RoundedCornerShape(20.dp))
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(20.dp)
                .clickable(
                    onClick = {
                        shareData.title = task.title
                        shareData.description = task.description
                        shareData.startDate = task.startDate

                        shareData.endDate = task.endDate

                        shareData.microTaskId = task.microTaskId
                        shareData.id = task.id
                        shareData.status = task.status
                        nav.navigate("viewTask")
                    }
                )
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .fillMaxHeight(0.5f)
                    .width(1.dp)
                    .background(Primary)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                CardTopContainer(text = task.title)
                CardDescription(text = task.description)
                CardDate(startDate =LocalDate.fromEpochDays(task.startDate.toInt()), endDate =LocalDate.fromEpochDays(task.endDate.toInt()))


            }
        }



}
@Composable
private  fun CardDate(startDate: LocalDate,endDate:LocalDate){

    val startDateMonthText = StripDownStringTo3LetterWithCapitalFirstLetter(startDate.month.toString())
    val startDateDayText = startDate.dayOfMonth
val endDateMonthText= StripDownStringTo3LetterWithCapitalFirstLetter(endDate.month.toString())
    val endDateDayText = endDate.dayOfMonth
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
Text("$startDateMonthText,$startDateDayText - $endDateMonthText,$endDateDayText", fontFamily = fontFamily, color = Primary)

            }
}
@Composable
private  fun CardDescription(text:String){
    Text(text, fontFamily= fontFamily,)
}

@Composable
private fun CardTopContainer(text:String){
    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
        Text(text, fontFamily= fontFamily, color = Primary, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)

        Icon(Icons.Filled.MoreVert, contentDescription = "menu", modifier = Modifier.rotate(-90f), tint = PrimaryWithAlpha10 )

    }
}

@Composable
private fun GetRequiredCard(uuid: String, currentDate: LocalDate, function: () -> Unit, ) {
    var listItemInfo by remember {
        mutableStateOf(listOf<Map<String,Any>>())

    }
    val query =   GetTaskDatabase(uid = uuid)
    query.getTaskOnlyTitle(currentDate = currentDate, onFailureListener = {
        error(it)
    }, onSuccessListener = {
            data->listItemInfo = data

    } )

    if(!listItemInfo.isEmpty()){
        for(item in listItemInfo){
          val Data  = TaskAttrForDatabase( startDate = 0, endDate =  0, microTaskId = "")
            Data.title = item["title"].toString()
            Data.description = item["description"].toString()
            Data.startDate = item["startDate"]
                .toString()
                .toLong()
            Data.endDate = item["endDate"]
                .toString()
                .toLong()
            Data.microTaskId = item["microTaskId"].toString()
            Data.id = item["id"].toString()
            Data.status = item["status"]
                .toString()
                .toBoolean()
            Card(Data)
        }

    }else{
        println("empty")

    }
}
