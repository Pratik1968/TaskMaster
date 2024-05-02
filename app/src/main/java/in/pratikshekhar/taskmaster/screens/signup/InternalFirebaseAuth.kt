package `in`.pratikshekhar.taskmaster.screens.signup

import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest

public class InternalFirebaseAuth(private val email:String, private val password:String,private val name:String, private  val auth: FirebaseAuth,private val context:android.content.Context,private val updateStatus:(Boolean)->Unit){

    init {
        println("Test here")
               createAccount()
    }
   private  fun createAccount(){

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                   updateName()


                } else {
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()

                }


            }
    }
    private  fun updateName(){
        val userAuth = Firebase.auth.currentUser
        userAuth?.updateProfile(userProfileChangeRequest { displayName = name })
            ?.addOnCompleteListener { task2 ->
                if (task2.isSuccessful) updateStatus(true)
                else task2.exception?.message?.let { Log.e("error", it) }
            }
    }

}