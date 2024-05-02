package `in`.pratikshekhar.taskmaster.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.pratikshekhar.taskmaster.screens.signup.LocalUserInfo
import `in`.pratikshekhar.taskmaster.ui.theme.fontFamily
import `in`.pratikshekhar.taskmaster.widget.AuthInput

@Preview(showBackground = true)
@Composable
internal fun Form(){

      val user =   LocalUserInfo.current


Column(modifier = Modifier.fillMaxWidth(0.8f), verticalArrangement = Arrangement.spacedBy(20.dp)){
Title()
    AuthInput(value = "Enter your name", onChange ={
        user.name= it

    })
    AuthInput( value="Enter your email",icon=Icons.Filled.Email, type = KeyboardType.Email, onChange = {

        user.email = it

    })
    AuthInput(value="Enter your password", icon = Icons.Filled.Lock, type = KeyboardType.Password,onChange={
user.password = it
    })
    AuthInput(value="Enter your password again", icon = Icons.Filled.Lock, type = KeyboardType.Password,
        onChange = {
            user.passwordAgain = it
        }
        )


}
}
@Composable
private fun Title(){
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Text(
            "Create your account",
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black

        )
    }
}
