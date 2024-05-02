package `in`.pratikshekhar.taskmaster.screens.viewTask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.ui.theme.Gray2
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.StripDownStringTo3LetterWithCapitalFirstLetter
import kotlinx.datetime.LocalDate

@Preview(showBackground = true)
@Composable
internal  fun DateContainer(startDate: LocalDate= LocalDate(2024,1,1), endDate: LocalDate= LocalDate(2024,1,10)){

Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
LabelAndDate(label = "start", date = startDate, horizontal = Alignment.Start)
    LabelAndDate(label = "end", date = endDate, horizontal = Alignment.End)
}
}

@Composable
private fun LabelAndDate(modifier:Modifier=Modifier,label:String,date:LocalDate,horizontal: Alignment.Horizontal){
    val day = date.dayOfMonth
    val month = StripDownStringTo3LetterWithCapitalFirstLetter(date.month.toString())
    val year = date.year.toString()
    Column(horizontalAlignment =horizontal ){
    Text(label,modifier = modifier, fontFamily = fontFamily, fontSize = 14.sp, fontWeight = FontWeight.Medium,color= Gray2)
Text("$day $month $year", fontFamily = fontFamily, fontSize = 12.sp, fontWeight = FontWeight.Medium,color= Gray2)
    }
}