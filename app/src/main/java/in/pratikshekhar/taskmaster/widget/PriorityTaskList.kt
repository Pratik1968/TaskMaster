package `in`.pratikshekhar.taskmaster.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.LocalSharedTask
import `in`.pratikshekhar.taskmaster.firebase.firestore.GetTaskDatabase
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.Purple10
import `in`.pratikshekhar.taskmaster.ui.theme.Red10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.GetCurrentDate

@Preview(showBackground = true)
@Composable
fun PriorityTaskList(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)){
        Title()
    CardList()
    }
}
@Composable
private  fun CardList(){
    val colorList = listOf(Primary, Purple10, Red10)
    val uuid = Firebase.auth.currentUser?.uid
var emptyList by remember {
    mutableStateOf(false)
}
    val updateEmptyListState = fun (value:Boolean){emptyList = value}
if(!emptyList){
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (uuid != null) {
            GetRequiredCard(uuid,updateEmptyListState)
        }
    }
}else{
    Box(
        Modifier
            .height(50.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center){
        Text("Nothing to show", fontFamily = fontFamily, fontSize = 14.sp, color = Color.LightGray)
    }
}
}
@Composable
private fun Title(){
    Text("My Priority Task", fontFamily = fontFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
}
@Composable
private fun Card(title: String, item: Map<String, Any>){
    var shareData =     LocalSharedTask.current
    val nav = LocalNavController.current
    Box(
        Modifier
            .width(129.dp)
            .height(188.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Primary)
            .padding(10.dp)
    ){
        Column(Modifier.fillMaxSize()) {
            Box(Modifier
                .fillMaxSize()
                .clickable(
                    onClick = {
                        println(item)
                        shareData.title = item["title"].toString()
                        shareData.description = item["description"].toString()
                        shareData.startDate = item["startDate"]
                            .toString()
                            .toLong()
                        shareData.endDate = item["endDate"]
                            .toString()
                            .toLong()
                        shareData.microTaskId = item["microTaskId"].toString()
                        shareData.id = item["id"].toString()
                        shareData.status = item["status"]
                            .toString()
                            .toBoolean()
                        nav.navigate("viewTask")

                    }
                )
                , contentAlignment = Alignment.Center){
                Text(title, color = Color.White, fontFamily = fontFamily, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            }


        }
    }

}
@Composable
private fun GetRequiredCard(uuid: String, updateEmptyListState: (Boolean) -> Unit) {
    var listItemInfo by remember {
        mutableStateOf(listOf<Map<String,Any>>())

    } 
    val query =   GetTaskDatabase(uid = uuid)
    query.getTaskOnlyTitle(currentDate = GetCurrentDate(), onFailureListener = {
        error(it)
    }, onSuccessListener = {
            data->listItemInfo = data

    } )
    
    if(!listItemInfo.isEmpty()){
        for(item in listItemInfo){
            Card(title = item["title"].toString(), item)
        }
        }else{
            println("empty")
updateEmptyListState(true)
    }
}
