package `in`.pratikshekhar.taskmaster.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.LocalSharedTask
import `in`.pratikshekhar.taskmaster.firebase.realtime.MicroTaskDatabaseHandler
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.PrimaryWithAlpha10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily

@Preview(showBackground = true)
@Composable
  fun DailyTaskCard(
    index: Int = 0,
    text: String = "text",
    uuid: String = "",
    microTaskId: String = "",
    status: Boolean= false,
    show:Boolean=true,
    item:Map<String,Any> = emptyMap()
){
   val shareData = LocalSharedTask.current
    val nav = LocalNavController.current
    var checked by remember {
        mutableStateOf(status)
    }
    val updateStatus = MicroTaskDatabaseHandler(uuid = uuid, microTaskId = microTaskId)
    LaunchedEffect(key1 = checked) {
updateStatus.setStatusChange(index,checked,{},{ error(it) })
    }
    Row(
        Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, PrimaryWithAlpha10), RoundedCornerShape(20.dp))
            .padding(10.dp)
            .clickable(
                onClick = {
                    if(!show){
                        shareData.title = item["title"].toString()
                        shareData. description = item["description"].toString()
                        shareData. startDate = item["startDate"].toString().toLong()
                        shareData. endDate = item["endDate"].toString().toLong()
                        // TODO: change to micro
                        shareData. microTaskId = ""
                        nav.navigate("viewTask")
                    }
                }
            )
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, fontFamily= fontFamily,)
        Checkbox(modifier = Modifier.alpha(if(show) 1f else 0f),checked = checked, onCheckedChange = {checked=!checked}, colors = CheckboxDefaults.colors(
          checkedColor =   Primary,
            uncheckedColor = Color.LightGray
        ))
    }
}
