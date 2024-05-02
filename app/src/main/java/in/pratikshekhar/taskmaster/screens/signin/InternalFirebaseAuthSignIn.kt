package `in`.pratikshekhar.taskmaster.screens.signin

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class InternalFirebaseAuthSignIn(val auth:FirebaseAuth, val email:String, val password:String,val updateStatus:(Boolean)->Unit,val context:android.content.Context) {
    init{
        signIn()
    }
    private  fun signIn(){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(){task->
                if(task.isSuccessful){

                    updateStatus(true)
                }else{
                    Toast.makeText(context,task.exception?.message, Toast.LENGTH_SHORT).show()

                }

            }
    }
}
