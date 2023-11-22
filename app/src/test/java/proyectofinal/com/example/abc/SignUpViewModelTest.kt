package proyectofinal.com.example.abc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import proyectofinal.com.example.abc.ui.sign_up.SignUpViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SignUpViewModelTest {



    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setup() {
        viewModel = SignUpViewModel()
    }

    @Test
    fun onFullNameChanged_updatesFullNameLiveData() {
        val fullName = "John Doe"
        viewModel.onFullNameChanged(fullName)

        val fullNameLiveData = viewModel.fullName.value

        assertEquals(fullName, fullNameLiveData)
    }

    @Test
    fun onEmailChanged_updatesEmailLiveData() {
        val email = "johndoe@example.com"
        viewModel.onEmailChanged(email)

        val emailLiveData = viewModel.email.value

        assertEquals(email, emailLiveData)
    }

    @Test
    fun onPasswordChanged_updatesPasswordLiveData() {
        val password = "secure_password"
        viewModel.onPasswordChanged(password)

        val passwordLiveData = viewModel.password.value

        assertEquals(password, passwordLiveData)
    }
}