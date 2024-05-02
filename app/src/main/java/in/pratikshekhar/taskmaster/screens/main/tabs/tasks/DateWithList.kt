package `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.DateListCardColor
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.StripDownStringTo3LetterWithCapitalFirstLetter
import kotlinx.datetime.LocalDate


@Preview(showBackground=true)
@Composable
internal  fun DateWithList(currentDate:LocalDate= LocalDate(2024,12,1),updateCurrentDate:(LocalDate)->Unit={}){

    val monthTemp = listOf(31,28,31,30,31,30,31,31,30,31,30,31)
val totalDay = monthTemp[currentDate.monthNumber-1]
    Row(
        Modifier
            .fillMaxWidth()
            .height(64.dp)
            .horizontalScroll(rememberScrollState())
            ,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        for ( day in 1..totalDay){
            val temp =LocalDate(currentDate.year,currentDate.month,day)
            Card(temp, temp==currentDate, updateCurrentDate = updateCurrentDate)
        }

    }
}
@Composable
private  fun Card(date:LocalDate= LocalDate(2024,12,1),active:Boolean=false,updateCurrentDate:(LocalDate)->Unit){
val day = StripDownStringTo3LetterWithCapitalFirstLetter(date.dayOfWeek.toString())

    Column(
        Modifier
            .size(64.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(if(active) Primary else DateListCardColor)
            .clickable ( onClick={
                updateCurrentDate(date)
            } )
            .padding(10.dp), verticalArrangement = Arrangement.Center

        , horizontalAlignment = Alignment.CenterHorizontally){
Text(text = day, fontFamily= fontFamily, fontSize = 16.sp,color= if(active) Color.White else Primary)
        Text(date.dayOfMonth.toString(), fontFamily = fontFamily, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = if(active) Color.White else Primary)


    }
}

