package `in`.pratikshekhar.taskmaster.screens.main

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.screens.main.tabs.home.Home
import `in`.pratikshekhar.taskmaster.screens.main.tabs.profile.Profile
import `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks.DateWithIcon
import `in`.pratikshekhar.taskmaster.screens.main.tabs.tasks.Tasks
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.util.SetStatusBarColor

@Composable
fun Main() {
SetStatusBarColor(color = Primary)
    val userAuth = Firebase.auth.currentUser
    val name = userAuth?.displayName
    MainContainer()

}
@Preview(showBackground = true)

@Composable
private fun MainContainer() {
    var activeTab:Int by remember {
        mutableIntStateOf(0)
    }

    Box(Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.White)){
        when(activeTab){
            0-> Home()
            1-> Tasks()
            2-> Profile()
            else -> Home()
        }
        TabContainer(Modifier.align(Alignment.BottomCenter), activeIndex = activeTab, setActiveIndex = {activeTab =it})
    }
}
@Composable
private fun TabContainer(modifier: Modifier,setActiveIndex:(Int)->Unit={},activeIndex:Int) {


    Box(
        modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f))  { Tab(activeIndex =activeIndex, setActiveIndex = setActiveIndex)}
}