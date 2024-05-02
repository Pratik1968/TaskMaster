package `in`.pratikshekhar.taskmaster.firebase.firestore

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class UpdateTask(private val db:FirebaseFirestore = Firebase.firestore,val uid:String) {
      fun setStatus(id:String,status:Boolean,onFailureListener:(Exception)->Unit,onSuccessListener:()->Unit){

        db.collection(uid).document(id).update(mapOf("status" to status)).addOnFailureListener(onFailureListener).addOnSuccessListener{onSuccessListener()}
    }
}