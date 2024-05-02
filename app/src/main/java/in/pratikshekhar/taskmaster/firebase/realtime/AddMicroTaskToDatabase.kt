package `in`.pratikshekhar.taskmaster.firebase.realtime

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import `in`.pratikshekhar.taskmaster.model.MicroTask

class AddMicroTaskToDatabase(private val database:DatabaseReference=Firebase.database.reference, private val microTaskList:List<MicroTask>, private  val uuid:String, private  val onSuccessListener:(String?)->Unit, private  val onFailureListener:(Exception)->Unit) {
    init {
add()
    }
    private fun add(){
val ref =  database.child(uuid).push()
     val autoGen = ref.key
    ref.setValue(microTaskList).addOnFailureListener {
    onFailureListener(it)
}.addOnSuccessListener {

    onSuccessListener(autoGen)
    }
    }
}
