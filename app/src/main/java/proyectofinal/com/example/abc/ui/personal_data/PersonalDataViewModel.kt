package proyectofinal.com.example.abc.ui.personal_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyectofinal.com.example.abc.ui.utils.ComboOption

class PersonalDataViewModel: ViewModel() {
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _edad = MutableLiveData<String>()
    val edad: LiveData<String> = _edad
    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone
    private val _paises = MutableLiveData<List<ComboOption>>()
    val paises: LiveData<List<ComboOption>> = _paises
    private val _languages = MutableLiveData<List<ComboOption>>()
    val languages: LiveData<List<ComboOption>> = _languages
    private val _softSkills = MutableLiveData<List<ComboOption>>()
    val softSkills: LiveData<List<ComboOption>> = _softSkills


    fun onEmailChanged(email: String) {
        _email.value = email
    }
    fun onEdadChanged(password: String) {
        _edad.value = password
    }
    fun onLoginClicked() {
        TODO("Not yet implemented")
    }
    fun onFullNameChanged(fullName: String) {
        _fullName.value = fullName
    }
    fun onPhoneChanged(phone: String) {
        _phone.value = phone
    }
    fun onPaisesChanged(paises: List<ComboOption>) {
        _paises.value = paises
    }
    fun onLanguagesChanged(languages: List<ComboOption>) {
        _languages.value = languages
    }
    fun onSoftSkillsChanged(softSkills: List<ComboOption>) {
        _softSkills.value = softSkills
    }

}