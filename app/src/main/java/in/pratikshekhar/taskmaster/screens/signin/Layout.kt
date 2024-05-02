package `in`.pratikshekhar.taskmaster.screens.signin

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import `in`.pratikshekhar.taskmaster.LocalNavController
import `in`.pratikshekhar.taskmaster.R
import `in`.pratikshekhar.taskmaster.model.UserAuth
import `in`.pratikshekhar.taskmaster.ui.theme.Gray1
import `in`.pratikshekhar.taskmaster.ui.theme.Primary
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.util.SetStatusBarColor

val  LocalUserInfo = compositionLocalOf<UserAuth> { error("User info not found") }
@Composable
fun SignInScreen() {
    val user by remember {
        mutableStateOf(UserAuth("","","",""))
    }

        SetStatusBarColor(color = Primary)

        Column(
            Modifier
                .background(androidx.compose.ui.graphics.Color.White)

        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            CompositionLocalProvider(LocalUserInfo provides user) {

            MainContainer()
            }
        }


}

@Preview(showBackground = true)
@Composable
private fun MainContainer(){
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        , horizontalAlignment = Alignment.CenterHorizontally, ) {

        LogoAndTaglineContainer()
        Form()
        Spacer(modifier = Modifier.height(20.dp))
        SignInButton()
        Spacer(modifier = Modifier.weight(1f))
        NotHaveAccount()

    }
}
@Composable
private fun LogoAndTaglineContainer(){

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        Spacer(modifier = Modifier.height(10.dp))
        TagLine()
        Spacer(modifier = Modifier.height(30.dp))
    }

}

@Composable
private fun Logo(){
    Image(painter = painterResource(id = R.drawable.task_master), contentDescription ="logo" )
}
@Composable
private  fun TagLine(){
    Text("Management App", color = Gray1, fontFamily = fontFamily)
}
@Composable
private fun  SignInButton(){
    var status by remember {
        mutableStateOf(false)

    }
    val context = LocalContext.current
    val user = LocalUserInfo.current
    val nav =  LocalNavController.current
val updateStatus = fun(value:Boolean){status = value}
if(status){
    nav.navigate("main")
}

        Button(onClick = {
signIn(context,user,updateStatus)
        }, modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(50.dp), colors = ButtonDefaults.buttonColors(
            Primary
        ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                "Sign In",
                fontFamily = fontFamily,
                color = androidx.compose.ui.graphics.Color.White
            )
        }

}
@Composable
private fun NotHaveAccount(){
    var status by remember {
        mutableStateOf(false)

    }
    if(status){

        LocalNavController.current.navigate("signup")
    }
    Row(Modifier.height(20.dp), verticalAlignment = Alignment.CenterVertically){

        Text("Don’t have an account? ", fontFamily= fontFamily, color = androidx.compose.ui.graphics.Color.Black, fontSize = 14.sp)

      Text("Sign Up",Modifier.clickable (onClick = {
status=true
      } ), fontFamily= fontFamily, color = Primary, fontSize = 14.sp)
    }
}


private fun signIn(context: Context, user: UserAuth, updateStatus: (Boolean) -> Unit) {

    if(user.email.isEmpty() || user.password.isEmpty() ) {
        Toast.makeText(context,"Please fill the information",Toast.LENGTH_SHORT).show()

    }else{

        val auth = Firebase.auth
        InternalFirebaseAuthSignIn(auth, email = user.email, password = user.password, updateStatus = updateStatus,context)
    }

}

