package `in`.pratikshekhar.taskmaster.screens.main.tabs.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Gray2
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.GetName
import `in`.pratikshekhar.taskmaster.util.StripDownStringTo3LetterWithCapitalFirstLetter
import `in`.pratikshekhar.taskmaster.widget.PriorityTaskList
import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun Home() {
    val name = GetName()
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
        , verticalArrangement = Arrangement.spacedBy(30.dp)) {
      DateText()
        if (name != null) {
            NameAndGettingContainer(name)
        }else {
            NameAndGettingContainer()

        }
        PriorityTaskList()
        DailyTaskList()
    }
}
@Composable
private fun DateText(){
    val Date = Date()
    val month =  SimpleDateFormat("MM", Locale.getDefault()).format(Date).toString()
    val year  = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date)
    val day  = SimpleDateFormat("dd", Locale.getDefault()).format(Date)
    val currentDate = LocalDate(year.toInt(),month.toInt(),day.toInt())
    val dayWeek = currentDate.dayOfWeek.toString().lowercase().replaceFirstChar ( transform ={char->char.uppercase()} )
val monthText = StripDownStringTo3LetterWithCapitalFirstLetter(currentDate.month.toString())
    Text("$dayWeek,  $monthText $day $year", fontFamily= fontFamily, fontSize = 12.sp, color = Gray2)


}
