package `in`.pratikshekhar.taskmaster.screens.splash


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.R
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.util.SetStatusBarColor

@Composable
fun SplashScreen() {
    SetStatusBarColor(color = Primary)
    MainContainer()

}
@Preview(showBackground = true)
@Composable
private  fun MainContainer(){
    val visible = remember { MutableTransitionState(false).apply { targetState=true } }
   val navController = LocalNavController.current
    Column(Modifier.background(Color.White).fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        AnimatedVisibility(
            visibleState = visible,

            enter = fadeIn(
                initialAlpha = 0.4f,
                animationSpec = tween(durationMillis = 1000)

            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 1000)
            )
        ) {
            Image(painter = painterResource(id = R.drawable.splashimage), contentDescription ="splash image" )
        when{
            visible.isIdle && visible.currentState -> navController.navigate("signup")
        }
        }
    }
}
