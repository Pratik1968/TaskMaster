package `in`.pratikshekhar.taskmaster.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import `in`.pratikshekhar.taskmaster.R
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.Purple10

@Preview(showBackground = true)
@Composable
internal fun Tab(modifier: Modifier=Modifier,activeIndex:Int=0,setActiveIndex:(Int)->Unit={}){
    Row(
        modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
TabItem(modifier=Modifier.clickable ( onClick={
setActiveIndex(0)

} ),icon = R.drawable.home, color = if(activeIndex==0) Primary else Purple10 )
        TabItem(modifier=Modifier.clickable ( onClick={
            setActiveIndex(1)
        } ),icon=R.drawable.calendar,color = if(activeIndex==1) Primary else Purple10)
        TabItem(modifier=Modifier.clickable ( onClick={
           setActiveIndex(2)
        } ),icon = R.drawable.profile,color = if(activeIndex==2) Primary else Purple10)

    }
}
@Composable
private fun TabItem(modifier: Modifier=Modifier,icon: Int = R.drawable.home, onClick:()->Unit={},color:Color=Purple10){
    Box(){
Image(modifier = modifier,painter = painterResource(id =icon ), contentDescription = "icon", colorFilter =ColorFilter.tint(color))
    }
}