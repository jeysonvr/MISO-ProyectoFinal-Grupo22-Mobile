package proyectofinal.com.example.abc.ui.experience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class LaboralExperienceViewModel : ViewModel() {
    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> = _companyName
    private val _rol = MutableLiveData<String>()
    val rol: LiveData<String> = _rol
    private val _startYear = MutableLiveData<String>()
    val startYear: LiveData<String> = _startYear
    private val _finalYear = MutableLiveData<String>()
    val finalYear: LiveData<String> = _finalYear
    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    fun onRolChanged(rol: String) {
        _rol.value = rol
    }
    fun onStartYearChanged(startYear: String) {
        _startYear.value = startYear
    }
    fun onLoginClicked() {
        TODO("Not yet implemented")
    }
    fun onCompanyNameChanged(companyName: String) {
        _companyName.value = companyName
    }
    fun onFinalYearChanged(finalYear: String) {
        _finalYear.value = finalYear
    }
    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

    fun onWorkHere(checked: Boolean) {
        TODO("Not yet implemented")
    }

}