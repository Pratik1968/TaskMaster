package `in`.pratikshekhar.taskmaster.util

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.content.ContextCompat

@Composable
fun SetStatusBarColor( color: Int){
    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor = color
}
@Composable
fun SetStatusBarColor( color: Color){
    val view = LocalView.current
    val window = (view.context as Activity).window
    window.statusBarColor =color.toArgb()
}