package proyectofinal.com.example.abc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataAddScreen
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataScreen
import proyectofinal.com.example.abc.ui.experience.LaboralExperienceAddScreen
import proyectofinal.com.example.abc.ui.experience.LaboralExperienceScreen
import proyectofinal.com.example.abc.ui.login.LoginScreen
import proyectofinal.com.example.abc.ui.login.LoginViewModel
import proyectofinal.com.example.abc.ui.main_menu.MainScreen
import proyectofinal.com.example.abc.ui.personal_data.PersonalDataScreen
import proyectofinal.com.example.abc.ui.profile.ProfileScreen
import proyectofinal.com.example.abc.ui.sign_up.SignUpScreen
import proyectofinal.com.example.abc.ui.sign_up.SignUpViewModel
import proyectofinal.com.example.abc.ui.skills.SkillsScreen
import proyectofinal.com.example.abc.ui.theme.ABCTheme
import proyectofinal.com.example.abc.ui.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ABCTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "welcome") {
                    composable("about") {

                    }
                    navigation(
                        startDestination = "WelcomeScreen",
                        route = "welcome"
                    ) {
                        composable("WelcomeScreen") {
                            WelcomeScreen(navController = navController)
                        }
                        composable(route = "loginScreen"){
                            LoginScreen(navController = navController)
                        }
                        composable("SignUpScreen") {
                            SignUpScreen(navController = navController)

                        }
                    }
                    navigation(
                        startDestination = "MainScreen",
                        route = "mainScreenNavigation"
                    ) {
                        composable("MainScreen") {
                            MainScreen(navController = navController)
                        }
                        composable("ProfileScreen") {
                            ProfileScreen(navController = navController)
                        }
                        composable("PersonalDataScreen") {
                            PersonalDataScreen(navController = navController)
                        }
                        composable("AcademicDataScreen") {
                            AcademicDataScreen(navController = navController)
                        }
                        composable("AcademicDataAddScreen") {
                            AcademicDataAddScreen(navController = navController)
                        }
                        composable("LaboralExperienceScreen") {
                            LaboralExperienceScreen(navController = navController)
                        }
                        composable("AddLaboralExperienceScreen") {
                            LaboralExperienceAddScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
