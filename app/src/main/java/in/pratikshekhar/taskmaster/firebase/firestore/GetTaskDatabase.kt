package `in`.pratikshekhar.taskmaster.firebase.firestore

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import `in`.pratikshekhar.taskmaster.model.MicroTask
import `in`.pratikshekhar.taskmaster.screens.addTask.CategoryType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.atStartOfDayIn
import java.sql.Date
import java.time.ZoneOffset


class GetTaskDatabase(val db:FirebaseFirestore = Firebase.firestore, val uid:String,) {

     fun getTaskOnlyTitle(currentDate:LocalDate, onFailureListener:(Exception)->Unit, onSuccessListener:(List<Map<String,Any>>)->Unit){
val currentLong:Long = currentDate.toEpochDays().toLong()
val finalResult = mutableListOf<Map<String,Any>>()
        db.collection(uid).whereEqualTo("category", CategoryType[0]).get(Source.CACHE)
            .addOnSuccessListener{
                    result ->
                for (document in result) {
                   val data =  document.data+Pair("id",document.id)
                    if(currentLong in data["startDate"].toString().toLong()..data["endDate"].toString().toLong()){
finalResult+=data
                    }
                }
                onSuccessListener(finalResult)

            }.addOnFailureListener(onFailureListener)
    }
    fun getTaskById(id:String,onFailureListener:(Exception)->Unit, onSuccessListener:(DocumentSnapshot)->Unit){
        db.collection(uid).document(id).get().addOnSuccessListener {documents-> onSuccessListener(documents) }.addOnFailureListener{onFailureListener(it)}
    }
    fun getDailyTaskOnlyTitle(currentDate:LocalDate, onFailureListener:(Exception)->Unit, onSuccessListener:(List<Map<String,Any>>)->Unit){
        val currentLong:Long = currentDate.toEpochDays().toLong()
        val finalResult = mutableListOf<Map<String,Any>>()
        db.collection(uid).whereEqualTo("category", CategoryType[1]).get(Source.CACHE)
            .addOnSuccessListener{
                    result ->
                for (document in result) {
                    val data =  document.data+Pair("id",document.id)
                    if(currentLong in data["startDate"].toString().toLong()..data["endDate"].toString().toLong()){
                        finalResult+=data
                    }
                }
                onSuccessListener(finalResult)

            }.addOnFailureListener(onFailureListener)
    }
}