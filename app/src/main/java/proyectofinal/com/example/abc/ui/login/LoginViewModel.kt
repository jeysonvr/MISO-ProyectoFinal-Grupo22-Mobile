package proyectofinal.com.example.abc.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun onEmailChanged(email: String) {
        _email.value = email
    }
    fun onPasswordChanged(password: String) {
        _password.value = password
    }
    fun onLoginClicked() {
        TODO("Not yet implemented")
    }

}