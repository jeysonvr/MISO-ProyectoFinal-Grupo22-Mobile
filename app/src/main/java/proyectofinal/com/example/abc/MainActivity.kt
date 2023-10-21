package proyectofinal.com.example.abc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataAddScreen
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataScreen
import proyectofinal.com.example.abc.ui.experience.LaboralExperienceAddScreen
import proyectofinal.com.example.abc.ui.experience.LaboralExperienceScreen
import proyectofinal.com.example.abc.ui.login.LoginScreen
import proyectofinal.com.example.abc.ui.personal_data.PersonalDataScreen
import proyectofinal.com.example.abc.ui.skills.SkillsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkillsScreen()
            /*ABCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }*/
        }
    }
}
