package `in`.pratikshekhar.taskmaster.screens.main.tabs.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.LocalNavController

import `in`.pratikshekhar.taskmaster.R
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.GetName

@Preview(showBackground = true)
@Composable
fun Profile() {
   Column(Modifier.fillMaxSize()){
TopBar()
ProfileList()
    }
}
@Composable
fun TopBar(){
    Box(
        Modifier
            .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
            .background(Primary)
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.4f),


    ){
PageTitleText(modifier = Modifier.align(Alignment.TopStart))
        GetName()?.let { ProfileAndNameContainer(Modifier.align(Alignment.Center), it) }


    }

}
@Composable
private  fun PageTitleText(modifier: Modifier){
    Text(text = "Profile", modifier = Modifier, fontFamily= fontFamily, fontWeight = FontWeight.Bold,color=Color.White, fontSize = 18.sp)

}
@Composable
private fun ProfileAndNameContainer(modifier: Modifier=Modifier,name:String){
    Column(
        modifier
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileContainer()
        Spacer(modifier = Modifier.height(10.dp))
        NameText(text =name )
    }
}
@Composable
private fun ProfileContainer(){
    Box(
        Modifier

            .clip(CircleShape)
            .background(Color.White)
            .fillMaxWidth(0.3f)
            .aspectRatio(1f)
            .background(
                Color.White
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Filled.Person,
            modifier =Modifier.fillMaxSize(0.8f),
            contentDescription = "Profile", tint = Color.LightGray,)
    }
}
@Composable
private fun NameText(text: String){
    Text(text, fontFamily = fontFamily, fontSize = 14.sp,color=Color.White, fontWeight = FontWeight.SemiBold)

}
@Composable
fun ProfileList(){
 var status by remember {
     mutableStateOf(false)
 }
    if (status){
        LocalNavController.current.navigate("signin")
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)) {
ProfileListItem( icon = R.drawable.logout,text = "Logout", onClick = {
Firebase.auth.signOut()
status = true
})
    }
}
@Composable
fun ProfileListItem(modifier:Modifier = Modifier,icon:Int,text:String,onClick:()->Unit={}){
    Row(
        Modifier.clickable(onClick = onClick)
    ) {
        Image(modifier = modifier,painter = painterResource(id =icon ), contentDescription = "icon", colorFilter =ColorFilter.tint(
            Primary))
        Spacer(modifier = Modifier.width(10.dp))
Text(text = text, fontFamily = fontFamily, fontSize = 16.sp)
    }
    }

