package `in`.pratikshekhar.taskmaster.firebase.realtime

import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import `in`.pratikshekhar.taskmaster.model.MicroTask


class MicroTaskDatabaseHandler(
    private val db: DatabaseReference = Firebase.database.reference,
    private val uuid: String,
    private val microTaskId: String
) {

    fun get(onSuccess: (List<MicroTask>) -> Unit) {
        val typeIndicator = object : GenericTypeIndicator<List<MicroTask>>() {}
        val Listener = object : ValueEventListener {
            var finalresult = listOf<MicroTask>()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI

                val result =   dataSnapshot.getValue(typeIndicator)
                println(result)


                if (result != null) {
                    onSuccess(result)
                }
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                error(databaseError.toException())
            }
        }
        val ref =  db.child(uuid).child(microTaskId)
       ref.keepSynced(true)
           ref.addValueEventListener(Listener)


    }
    fun setStatusChange(index:Int,status:Boolean,onSuccess: ()->Unit,onFailure:(Exception)->Unit){

        db.child(uuid).child(microTaskId).child(index.toString()).child("status").setValue(status).addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            onFailure(it)
        }

    }


}