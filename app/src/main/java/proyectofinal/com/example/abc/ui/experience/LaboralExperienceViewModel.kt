package proyectofinal.com.example.abc.ui.experience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import proyectofinal.com.example.abc.model.ExperienciaLabIn
import proyectofinal.com.example.abc.model.ExperienciaLaboralDTO
import proyectofinal.com.example.abc.model.ExperienciaOut
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import proyectofinal.com.example.abc.ui.utils.getActualYear
import proyectofinal.com.example.abc.ui.utils.toComboOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton
@HiltViewModel
open class LaboralExperienceViewModel @Inject constructor(private val remoteUsuario: RemoteUsuario) : ViewModel() {

    private val _listExperience = MutableLiveData<List<ExperienciaOut>>()
    val listExperience: LiveData<List<ExperienciaOut>>? = _listExperience

    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> = _companyName
    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate
    private val _finalDate = MutableLiveData<String>()
    val finalDate: LiveData<String> = _finalDate
    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description
    private val _workHere = MutableLiveData<Boolean>()
    val workHere: LiveData<Boolean> = _workHere
    private val _roles = MutableLiveData<List<ComboOption>>()
    val roles: LiveData<List<ComboOption>> = _roles
    private val _rolSelected = MutableLiveData<List<ComboOption>>()
    val rolSelected: LiveData<List<ComboOption>> = _rolSelected
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var idCandidato : Int? = null

    fun onRolChanged(rol: List<ComboOption>) {
        _rolSelected.value = rol
    }
    fun onStartDateChanged(startYear: String) {
        _startDate.value = startYear
    }
    fun onCompanyNameChanged(companyName: String) {
        _companyName.value = companyName
    }
    fun onFinalDateChanged(finalYear: String) {
        _finalDate.value = finalYear
    }
    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

    fun onWorkHere(checked: Boolean) {
        _finalDate.value = getActualYear().toString()
        _workHere.value = checked
    }

    fun onSaveClicked(onSaveSuccess: () -> Unit, onSaveFailed: () -> Unit) {
        if ( _workHere.value == null || _startDate.value.isNullOrEmpty() || _finalDate.value.isNullOrEmpty() || _companyName.value.isNullOrEmpty() || _description.value.isNullOrEmpty()) {
            onSaveFailed()
        } else {
            val experienciaLaboralDTO = ExperienciaLaboralDTO(
                experiencia = ExperienciaLabIn(
                    `actual` = if (_workHere.value?: false) 1 else 0,
                    fecha_fin = _finalDate.value!!,
                    fecha_inicio = _startDate.value!!,
                    id_rol = 1,
                    descripcion_actividades = _description.value!!,
                    nombre_empresa = _companyName.value!!
                ),
                id_candidato = idCandidato!!
            )
            save(onSaveSuccess, experienciaLaboralDTO, onSaveFailed)
        }
    }

    fun save(onSaveSuccess: () -> Unit,experienciaLaboralDTO: ExperienciaLaboralDTO,onSaveFailed: () -> Unit) {
        uiScope.launch {
            try {
                val response = remoteUsuario.saveExperienciaLaboral(experienciaLaboralDTO)
                if (response.code() == (200)) {
                    onSaveSuccess()
                } else {
                    onSaveFailed()
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
                    idCandidato = response.body()!!.id
                    _listExperience.value = response.body()!!.experiencia
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getMetaData(sharePreference: SharePreference){
        uiScope.launch {
            try {
                val response = remoteUsuario.getMetadata()
                if(response.code().equals(200)) {
                    _roles.value = response.body()!!.roles.toComboOptions()
                    getInfoUser(sharePreference)
                }

            } catch (e: Exception) {

            }
        }
    }
}