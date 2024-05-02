package `in`.pratikshekhar.taskmaster.screens.addTask

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.util.StripDownStringTo3LetterWithCapitalFirstLetter
import `in`.pratikshekhar.taskmaster.widget.IconInput
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun StartAndEndDate() {
    val task = LocalTaskData.current
     val Box = hashMapOf<String,Int>(
        "StartDate" to 1,
        "EndDate" to 2,
    )
    var press by remember {
        mutableStateOf(false)
    }
    val updatePress = fun (value:Boolean){press=value}

    var selectedDate by remember {
        mutableStateOf(task.startDate)
    }
    var selectedBox by remember {
        mutableStateOf(Box["StartDate"])
    }
    val updateSelectedDate = fun(date : LocalDate) {selectedDate = date}
    LaunchedEffect(key1 = selectedDate) {
        if(selectedBox ==Box["StartDate"]){
            task.startDate = selectedDate
        }else{
            task.endDate = selectedDate
        }
     //   updateTaskData(taskData)

    }
    DateStuff(press =press,updatePress,updateSelectedDate, )

    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),){
        IconInput(
            Modifier
                .weight(1f)
                .height(80.dp)
                .clickable(
                    onClick = {
                        updatePress(true)
                        selectedBox = Box["StartDate"]

                    }
                ),
            placeholder = ""+task.startDate.dayOfMonth+"\t"+ StripDownStringTo3LetterWithCapitalFirstLetter(task.startDate.month.toString()), label = "Start",
        )
        Spacer(Modifier.width(10.dp))
        IconInput(
            Modifier
                .weight(1f)
                .clickable {
                    updatePress(true)
                    selectedBox = Box["EndDate"]
                },placeholder = ""+task.endDate.dayOfMonth+"\t"+ StripDownStringTo3LetterWithCapitalFirstLetter(task.endDate.month.toString()), label = "End")

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private  fun DateStuff(press: Boolean, updatePress: (Boolean) -> Unit, updateSelectedDate: (LocalDate) -> Unit){
    val datePickerState = rememberDatePickerState()

    if(press==true){
        DatePickerDialog(
            onDismissRequest = { updatePress(false) },
            confirmButton = {
                val selectedDate = datePickerState.selectedDateMillis?.let {
                    Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault()).date
                }
                if (selectedDate != null) {
                    updateSelectedDate(selectedDate)
                }

            }) {
            DatePicker(state = datePickerState,
                Modifier.background(Color.White)
                ,
                title = {

                }
                ,
                colors= DatePickerDefaults.colors(
                    containerColor = Color.White,
                    selectedDayContentColor = Color.White,
                    todayDateBorderColor = Primary,
titleContentColor = Color.Black,
                    headlineContentColor = Color.Black,
                    subheadContentColor = Color.Black,
                    yearContentColor = Color.Black,

currentYearContentColor = Color.Black,
                    weekdayContentColor = Color.Black,
                    todayContentColor = Color.Black,
disabledDayContentColor = Color.Black,
                    dayInSelectionRangeContentColor = Color.Black,
                    disabledSelectedDayContentColor = Color.Black,
selectedYearContentColor = Color.Black,
                    dayContentColor = Color.Black,
                    selectedDayContainerColor = Primary,

            selectedYearContainerColor = Color.Black ,
            dayInSelectionRangeContainerColor= Color.Black,
                    )
            )
        }
    }
}