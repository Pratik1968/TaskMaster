package `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks.TaskType

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import kotlinx.datetime.LocalDate

@Composable
internal fun TaskTypeSection(currentDate: LocalDate) {
var tabIndex by remember{
    mutableIntStateOf(1)
}
    val updateTabIndex = fun (_tabIndex:Int){
        tabIndex = _tabIndex
    }
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()){

    TabContainer(updateTabIndex,tabIndex)
        Spacer(modifier = Modifier.height(10.dp))
        when(tabIndex){
 0-> PriorityTask(currentDate)
            1-> DailyTask(currentDate)
        }
    }

}
@Composable
private  fun TabContainer(updateTabIndex: (Int) -> Unit, tabIndex: Int =0) {

    Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
TabItem(Modifier.weight(1f),"Priority Task",0,updateTabIndex,tabIndex)
TabItem(Modifier.weight(1f),"Daily Task",1,updateTabIndex,tabIndex)
    }
}
@Composable
fun TabItem(modifier: Modifier=Modifier,text:String,index:Int,updateTabIndex: (Int) -> Unit, tabIndex: Int){
    Column(modifier.clickable(onClick = {
        updateTabIndex(index)
    })
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text, fontFamily = fontFamily, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = if(tabIndex==index) Primary else Color.Black)

        Spacer(Modifier.height(10.dp))
        Box(
            Modifier
                .fillMaxWidth(0.1f)
                .height(2.dp)
                .background(if (tabIndex == index) Primary else Color.Transparent))

    }
}
