package `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.R
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.StripDownStringTo3LetterWithCapitalFirstLetter
import kotlinx.datetime.LocalDate

@Preview(showBackground = true)
@Composable
internal fun DateWithIcon(currentDate: LocalDate =LocalDate(2024,1,1)) {




MainContainer(currentDate)

}

@Composable
private  fun MainContainer(currentDate: LocalDate) {

    Row (verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.calendar), contentDescription ="calendar", colorFilter = ColorFilter.tint(
            Primary) )
        Spacer(Modifier.width(10.dp))
DateContainer(currentDate)

    }
}
@Composable
private fun DateContainer(currentDate: LocalDate) {
   val month = StripDownStringTo3LetterWithCapitalFirstLetter(currentDate.month.toString())

    Row (horizontalArrangement = Arrangement.spacedBy(2.dp)){
        Text(month,Modifier.wrapContentHeight(), fontFamily = fontFamily, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
Text(",", fontFamily= fontFamily ,fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(currentDate.year.toString(), fontFamily= fontFamily, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.End)
    }
}