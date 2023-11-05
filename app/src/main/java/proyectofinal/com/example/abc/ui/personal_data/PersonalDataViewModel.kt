package proyectofinal.com.example.abc.ui.personal_data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import proyectofinal.com.example.abc.model.CandidatoInfoDTO
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SelectableOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import proyectofinal.com.example.abc.ui.utils.toComboOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PersonalDataViewModel: ViewModel() {
    private val _fullName = MutableLiveData<String>()
    val fullName: LiveData<String> = _fullName
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _edad = MutableLiveData<String>()
    val edad: LiveData<String> = _edad
    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone
    private val _countries = MutableLiveData<List<ComboOption>>()
    val countries: LiveData<List<ComboOption>> = _countries
    private val _languages = MutableLiveData<List<ComboOption>>()
    val languages: LiveData<List<ComboOption>> = _languages
    private val _softSkills = MutableLiveData<List<ComboOption>>()
    val softSkills: LiveData<List<ComboOption>> = _softSkills
    private val _skills = MutableLiveData<List<ComboOption>>()
    val skills: LiveData<List<ComboOption>> = _skills
    private val _countrySelected = MutableLiveData<List<ComboOption>>()
    val countrySelected: LiveData<List<ComboOption>> = _countrySelected
    private val _languagesSelected = MutableLiveData<List<ComboOption>>()
    val languagesSelected: LiveData<List<ComboOption>> = _languagesSelected
    private val _softSkillsSelected = MutableLiveData<List<ComboOption>>()
    val softSkillsSelected: LiveData<List<ComboOption>> = _softSkillsSelected
    private val _skillsSelected = MutableLiveData<List<ComboOption>>()
    val skillsSelected: LiveData<List<ComboOption>> = _skillsSelected
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var remoteUsuario: RemoteUsuario = RemoteUsuario()

    fun onSaveInfoClicked(onSaveSuccess: () -> Unit) {
        val candidatoInfoDTO = CandidatoInfoDTO(
            edad = _edad.value!!.toInt(),
            email = _email.value!!,
            experiencia = listOf(),
            habilidadesBlandas =  softSkillsSelected.value!!.map {softSkills -> softSkills.id },
            habilidadesTecnicas = skillsSelected.value!!.map { skills -> skills.id },
            id_pais = _countrySelected.value!![0].id,
            idiomas = languagesSelected.value!!.map { languages -> languages.id },
            informacionAcademica = listOf(),
            numero_telefono = _phone.value!!
        )
        uiScope.launch {
            try {

                val response = remoteUsuario.saveCandidato(candidatoInfoDTO)
                if(response.code().equals(200)) {
                    onSaveSuccess()
                }
            } catch (e: Exception) {

            }
        }
    }
    fun onFullNameChanged(fullName: String) {
        _fullName.value = fullName
    }
    fun onPhoneChanged(phone: String) {
        _phone.value = phone
    }
    fun onAgeChanged(age: String) {
        _edad.value = age
    }
    fun onPaisesChanged(countries: List<ComboOption>) {
        _countrySelected.value = countries
    }
    fun onLanguagesChanged(languages: List<ComboOption>) {
        _languagesSelected.value = languages
    }
    fun onSoftSkillsChanged(softSkills: List<ComboOption>) {
        _softSkillsSelected.value = softSkills
    }

    fun onSkillsChanged(skills: List<ComboOption>) {
        _skillsSelected.value = skills
    }

    fun getInfoInicial(sharePreference: SharePreference) {
        getMetaData(sharePreference)
        //getInfoUser(sharePreference)
    }

    fun getMetaData(sharePreference: SharePreference){
        uiScope.launch {
            try {
                val response = remoteUsuario.getMetadata()
                if(response.code().equals(200)) {
                    _countries.value = response.body()!!.paises.toComboOptions()
                    _softSkills.value = response.body()!!.habilidadesBlandas.toComboOptions()
                    _languages.value = response.body()!!.idiomas.toComboOptions()
                    _skills.value = response.body()!!.habilidadesTecnicas.toComboOptions()
                    getInfoUser(sharePreference = sharePreference)
                }

            } catch (e: Exception) {

            }
        }
    }

    fun getInfoUser(sharePreference: SharePreference){
        uiScope.launch {
            try {
                val response = remoteUsuario.getCandidato(sharePreference.getUserLogged()!!.usuario)
                if(response.code().equals(200)) {
                    _fullName.value = response.body()!!.usuario.nombre_completo
                    _email.value = response.body()!!.usuario.email
                    _phone.value = response.body()!!.numero_telefono
                    _edad.value = response.body()!!.edad.toString()
                    _softSkillsSelected.value = response.body()!!.habilidadesBlandas.toComboOptions()
                    _skillsSelected.value = response.body()!!.habilidadesTecnicas.toComboOptions()
                    _countrySelected.value = _countries.value?.filter { it.id == response.body()!!.id_pais } ?: emptyList()
                    _languagesSelected.value = response.body()!!.idiomas.toComboOptions()
                }
            } catch (e: Exception) {

            }
        }
    }

}