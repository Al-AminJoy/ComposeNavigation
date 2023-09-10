package com.alamin.composenavigation

import android.os.Bundle
import android.telephony.ims.RegistrationManager.RegistrationCallback
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alamin.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    @Composable
    fun App() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "login") {
            composable(route = "registration") { Registration() }
            composable(route = "login") {
                Login { email,password ->
                    navController.navigate("main/${email}/${password}")
                }
            }


            composable(route = "main/{email}/{password}", arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                },navArgument("password") {
                    type = NavType.StringType
                }
            )) {
                val email = it.arguments!!.getString("email")
                val password = it.arguments!!.getString("password")
                Main(email!!,password!!)
            }
        }
    }

    @Composable
    fun Registration() {
        Text(text = "Registration Screen", style = MaterialTheme.typography.titleMedium)
    }

    @Composable
    fun Login(onClick: (String,String) -> Unit) {
        Text(
            text = "Login Screen",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.clickable {
                onClick("myemail@gmail.com","1234")
            })
    }

    @Composable
    fun Main(email:String,password:String) {
        Text(text = "Main Screen - $email - $password", style = MaterialTheme.typography.titleMedium)
    }
}

