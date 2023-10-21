package proyectofinal.com.example.abc.ui.academic_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import proyectofinal.com.example.abc.ui.utils.ComboOption

class AcademicDataViewModel : ViewModel() {

    private val _instituteEducation = MutableLiveData<String>()
    val instituteEducation: LiveData<String> = _instituteEducation
    private val _carrer = MutableLiveData<String>()
    val carrer: LiveData<String> = _carrer
    private val _startYear = MutableLiveData<String>()
    val startYear: LiveData<String> = _startYear
    private val _finalYear = MutableLiveData<String>()
    val finalYear: LiveData<String> = _finalYear


    fun onCarrerChanged(carrer: String) {
        _carrer.value = carrer
    }
    fun onStartYearChanged(startYear: String) {
        _startYear.value = startYear
    }
    fun onLoginClicked() {
        TODO("Not yet implemented")
    }
    fun onInstituteEducationChanged(instituteEducation: String) {
        _instituteEducation.value = instituteEducation
    }
    fun onFinalYearChanged(finalYear: String) {
        _finalYear.value = finalYear
    }

    fun onProgress(checked: Boolean) {
        TODO("Not yet implemented")
    }

}