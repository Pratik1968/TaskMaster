package `in`.pratikshekhar.taskmaster.util

import androidx.compose.runtime.Composable
import com.google.firebase.auth.FirebaseAuth

@Composable
fun GetName(): String? {
    return FirebaseAuth.getInstance().currentUser?.displayName
}