package proyectofinal.com.example.abc.ui.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel(){
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun onFullNameChanged(fullName: String) {
        _fullName.value = fullName
    }
    fun onEmailChanged(email: String) {
        _email.value = email
    }
    fun onPasswordChanged(password: String) {
        _password.value = password
    }
    fun onSignUpClicked() {
        TODO("Not yet implemented")
    }
}