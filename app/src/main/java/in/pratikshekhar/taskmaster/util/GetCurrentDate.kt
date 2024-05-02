package `in`.pratikshekhar.taskmaster.util

import kotlinx.datetime.LocalDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun GetCurrentDate():LocalDate{
    val Date = Date()
    val month =  SimpleDateFormat("MM", Locale.getDefault()).format(Date).toString()
    val year  = SimpleDateFormat("yyyy", Locale.getDefault()).format(Date)
    val day  = SimpleDateFormat("dd", Locale.getDefault()).format(Date)
    val currentDate = LocalDate(year.toInt(),month.toInt(),day.toInt())
    return  currentDate
}
