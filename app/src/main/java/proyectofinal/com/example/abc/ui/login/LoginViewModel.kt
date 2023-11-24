package proyectofinal.com.example.abc.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyectofinal.com.example.abc.model.LoginDTO
import proyectofinal.com.example.abc.model.LoginResponseDTO
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var remoteUsuario: RemoteUsuario = RemoteUsuario()

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun onLoginClicked(
        sharePreference: SharePreference,
        onLoginCandidateSucces: () -> Unit,
        onLoginAdminSucces: () -> Unit,
        onLoginCompanySucces: () -> Unit,
        onLoginError: () -> Unit
    ) {
        if (_email.value.isNullOrEmpty() || _password.value.isNullOrEmpty()) {
            onLoginError()
        } else {
            val loginDTO = LoginDTO(
                contrasena = _password.value!!,
                email = _email.value!!
            )
            uiScope.launch {
                try {
                    val response = remoteUsuario.login(loginDTO)
                    _email.value = ""
                    _password.value = ""
                    if (response.code() == (200)) {
                        savedPrefUser(response.body()!!, sharePreference)

                        when (response.body()!!.id_tipo_usuario) {
                            1 -> {
                                onLoginAdminSucces()
                            }

                            2 -> {
                                onLoginCandidateSucces()
                            }

                            3 -> {
                                onLoginCompanySucces()
                            }
                        }
                    } else {
                        onLoginError()
                    }

                } catch (e: Exception) {

                }
            }
        }
    }

    fun savedPrefUser(user: LoginResponseDTO, sharePreference: SharePreference) {
        sharePreference.setUserLogged(user)
    }

}