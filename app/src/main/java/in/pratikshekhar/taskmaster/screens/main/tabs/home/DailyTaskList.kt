package `in`.pratikshekhar.taskmaster.screens.main.tabs.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.firebase.firestore.GetTaskDatabase
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.GetCurrentDate
import `in`.pratikshekhar.taskmaster.widget.DailyTaskCard

@Preview(showBackground = true)
@Composable
fun DailyTaskList(){
    Column(Modifier.fillMaxWidth()) {
        Title()
Spacer(modifier = Modifier.height(10.dp))
        CardList()
    }

}
@Composable
private fun CardList(){
    val uuid = Firebase.auth.currentUser?.uid
   var emptyListStatus by remember {
       mutableStateOf(false)
   }
    val updateEmptyListStatus = fun (value:Boolean){
        emptyListStatus = value
    }
    if(!emptyListStatus){Column(
   verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
    if (uuid != null) {
        GetRequiredCard(uuid=uuid,updateEmptyListStatus)
    }}

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
    Text("Daily Task", fontFamily = fontFamily, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun GetRequiredCard(uuid: String, updateEmptyListStatus: (Boolean) -> Unit,) {
    var listItemInfo by remember {
        mutableStateOf(listOf<Map<String,Any>>())

    }
    val query =   GetTaskDatabase(uid = uuid)
    query.getDailyTaskOnlyTitle(currentDate = GetCurrentDate(), onFailureListener = {
        error(it)
    }, onSuccessListener = {
            data->listItemInfo = data

    } )

    if(listItemInfo.isNotEmpty()){
       updateEmptyListStatus(false)
        val  count =0
        for(item in listItemInfo){
            DailyTaskCard(index = count , text = item["title"].toString(),uuid, show = false,item=item )
        }
    }else{
        updateEmptyListStatus(true)

        Box(
            Modifier
                .height(50.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center){
            Text("Nothing to show", fontFamily = fontFamily, fontSize = 14.sp, color = Color.LightGray)
        }

    }
}