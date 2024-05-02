package    `in`.pratikshekhar.taskmaster.screens.addTask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.pratikshekhar.taskmaster.model.MicroTask
import `in`.pratikshekhar.taskmaster.model.TaskAttr
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.PrimaryWithAlpha10
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.widget.Label
import kotlinx.datetime.LocalDate
val CategoryType = listOf<String>("PRIORITY","DAILY")

@Composable
fun Category(updateShowMicroTask: (Boolean) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Label(text = "Catergory")
Spacer(modifier = Modifier.height(10.dp))
        PriorityAndDailyTaskContainer(updateShowMicroTask = updateShowMicroTask)
    }
}

@Preview(showBackground = true)
@Composable
private fun PriorityAndDailyTaskContainer(
    updateShowMicroTask: (Boolean) -> Unit={}
) {
    val task = LocalTaskData.current

    var getActiveIndex by remember {
      mutableStateOf(CategoryType.indexOf(task.category))
    }
    LaunchedEffect(key1 = getActiveIndex) {
        task.category=CategoryType[getActiveIndex]
        if(CategoryType[getActiveIndex]== CategoryType[0]){
            updateShowMicroTask(true)
        }else{
            updateShowMicroTask(false)
        }
    }
    Row(
        Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CustomButtonCategory(Modifier.weight(1f),text = "Priority Task",0,getActiveIndex,fun(){task.category ="PRIORITY"; getActiveIndex =CategoryType.indexOf(task.category)})
        CustomButtonCategory(Modifier.weight(1f),text = "Daily Task",1,getActiveIndex,fun(){task.category ="DAILY";getActiveIndex =CategoryType.indexOf(task.category)})
    }
}

@Composable
private fun CustomButtonCategory(modifier: Modifier, text: String, index:Int, activeIndex :Int,onClick:()->Unit) {
    Box(
        modifier
            .then(
                if (index == activeIndex) {
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Primary)
                } else {
                    Modifier.border(
                        BorderStroke(1.dp, PrimaryWithAlpha10),
                        RoundedCornerShape(10.dp)
                    )

                }
                    .fillMaxHeight()
            )
            .clickable(onClick = onClick)

        ,
        contentAlignment = Alignment.Center

    ) {
        Text(text, fontFamily = fontFamily, color = if(index==activeIndex) Color.White else Primary)
    }
}