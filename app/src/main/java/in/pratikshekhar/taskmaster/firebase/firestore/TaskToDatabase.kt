package `in`.pratikshekhar.taskmaster.firebase.firestore

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import `in`.pratikshekhar.taskmaster.firebase.realtime.AddMicroTaskToDatabase
import `in`.pratikshekhar.taskmaster.model.TaskAttr
import `in`.pratikshekhar.taskmaster.model.TaskAttrForDatabase
import `in`.pratikshekhar.taskmaster.screens.addTask.CategoryType

class TaskToDatabase(private val uid:String, val task:TaskAttr, private val db: FirebaseFirestore = Firebase.firestore, private val onSuccessListener: ()->Unit, private val onFailureListener: (Exception) ->Unit){
    init{
       if(task.category == CategoryType[0]){
           addPriorityAndMicroTask()
       }else {
          addOnlyTask("")
       }
        println("task here  $task")

    }


    private  fun addOnlyTask(microTaskId:String){

        val taskForDB = TaskAttrForDatabase(
            title = task.title, category = task.category,
            description = task.description, startDate = task.startDate.toEpochDays().toLong() , endDate = task.endDate.toEpochDays().toLong(), microTaskId =microTaskId )
        db.collection(uid).add(taskForDB).addOnSuccessListener{
            onSuccessListener()
        }.addOnFailureListener{
onFailureListener(it)
        }
    }


    private  fun addPriorityAndMicroTask(){
        AddMicroTaskToDatabase(microTaskList = task.listOfMicroTask, uuid = uid, onSuccessListener = {
            if (it != null) {
                addOnlyTask(it)
            }else{
                onFailureListener(Exception("MicroTask id not found"))
            }
        }, onFailureListener = {
            onFailureListener(it)
        })

    }


}