package `in`.pratikshekhar.taskmaster.model

import kotlinx.datetime.LocalDate

data class TaskAttr(var startDate:LocalDate, var endDate:LocalDate, var title:String="", var category:String="PRIORITY", var description:String = "", var listOfMicroTask:List<MicroTask>,)
data class TaskAttrForDatabase(var startDate: Long, var endDate: Long, var title:String="", var category:String="PRIORITY", var description:String = "", var microTaskId:String,var status: Boolean = false,var id:String ="")

data class MicroTask(var id:Int,var title:String,var status:Boolean){
    constructor() : this(0, "", false)
}