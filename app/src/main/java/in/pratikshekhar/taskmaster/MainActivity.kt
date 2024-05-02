package `in`.pratikshekhar.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.model.TaskAttrForDatabase
import `in`.pratikshekhar.taskmaster.screens.addTask.AddTask
import `in`.pratikshekhar.taskmaster.screens.main.Main
import `in`.pratikshekhar.taskmaster.screens.signin.SignInScreen
import `in`.pratikshekhar.taskmaster.screens.splash.SplashScreen
import `in`.pratikshekhar.taskmaster.screens.signup.SignUpScreen
import `in`.pratikshekhar.taskmaster.screens.viewTask.ViewTaskScreen
import `in`.pratikshekhar.taskmaster.ui.theme.TaskMasterTheme
val LocalNavController  = compositionLocalOf<NavHostController> { error("Nav controller not found") }
val LocalSharedTask = compositionLocalOf<TaskAttrForDatabase> { error("SharedData not found") }
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TaskMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    MainView()
                }
            }
        }
    }
    @Composable
    fun MainView(){

var shareDataBetweenScreen by remember {

    mutableStateOf(TaskAttrForDatabase(1,1,"","","",""))
}

        val navController = rememberNavController()

        CompositionLocalProvider(LocalNavController provides navController, LocalSharedTask provides shareDataBetweenScreen) {
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") { SplashScreen() }
                composable("signup") { SignUpScreen() }
                composable("signin") { SignInScreen() }
                composable("main") { Main() }
                composable("addTask"){ AddTask()}
                composable("viewTask"){ ViewTaskScreen()}

            }
        }
    }

}

