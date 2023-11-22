package proyectofinal.com.example.abc.ui.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import proyectofinal.com.example.abc.model.RegistroDTO
import proyectofinal.com.example.abc.repository.RemoteUsuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var remoteUsuario: RemoteUsuario = RemoteUsuario()

    fun onFullNameChanged(fullName: String) {
        _fullName.value = fullName
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onPasswordChanged(password: String) {
        _password.value = password
    }

    fun onSignUpClicked(onSignUpSuccess: () -> Unit, onSignUpFailed : () -> Unit) {
        val registroDTO = RegistroDTO(
            contrasena = _password.value!!,
            email = _email.value!!,
            id_tipo_usuario = 2,
            nombre_completo = _fullName.value!!
        )
        uiScope.launch {
            try {
                //remoteUsuario.login(loginDTO)
                val response = remoteUsuario.registro(registroDTO)
                _fullName.value = ""
                _email.value = ""
                _password.value = ""
                if (response.body().equals("successful!")) {
                    onSignUpSuccess()
                } else {
                    onSignUpFailed()
                }

            } catch (e: Exception) {

            }
        }

    }
}