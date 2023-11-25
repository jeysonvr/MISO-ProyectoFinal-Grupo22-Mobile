package proyectofinal.com.example.abc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.called
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import proyectofinal.com.example.abc.model.LoginResponseDTO
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.login.LoginViewModel
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val sharePreference = mockk<SharePreference>()
    private val remoteUsuario = mockk<RemoteUsuario>()

    private lateinit var viewModel: LoginViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = LoginViewModel()
        viewModel.remoteUsuario = remoteUsuario
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `onLoginClicked should call onLoginSuccess on successful login`() {
        val emailObserver = mockk<Observer<String>>(relaxed = true)
        val passwordObserver = mockk<Observer<String>>(relaxed = true)
        viewModel.email.observeForever(emailObserver)
        viewModel.password.observeForever(passwordObserver)

        coEvery { remoteUsuario.login(any()) } returns Response.success(
            200, LoginResponseDTO(
                id_tipo_usuario = 2,
                id = 3,
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InpyZXlAZW1haWwuY29tIiwiaWQiOjEsImlhdCI6MTY5OTE0MDY5NH0.iD31TM_8mYweu2nawV7oYG3mztdEYauiYVJ9VlPl5Dc",
                usuario = "zrey@email.com"
            )
        ) // Simulate a successful login

        val onLoginSuccessCallback = mockk<() -> Unit>()
        val onLoginAdminSucessCallback = mockk<() -> Unit>()
        val onLoginCompanySucessCallback = mockk<() -> Unit>()
        val onErrorAction = mockk<() -> Unit>()

        viewModel.onEmailChanged("test@example.com")
        viewModel.onPasswordChanged("password")

        viewModel.onLoginClicked(
            sharePreference,
            onLoginSuccessCallback,
            onLoginAdminSucessCallback,
            onLoginCompanySucessCallback,
            onErrorAction
        )

        // Verify that the email and password LiveData have been updated
        coEvery { onLoginSuccessCallback.invoke() }
    }


}