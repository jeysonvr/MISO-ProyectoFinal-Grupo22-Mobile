package proyectofinal.com.example.abc.ui.performance_evaluation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import proyectofinal.com.example.abc.model.ContractsOutDTOItem
import proyectofinal.com.example.abc.model.PerformanceEvaluationInDTO
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerformanEvaluationViewModel @Inject constructor(private val remoteUsuario: RemoteUsuario) :
    ViewModel() {
    private val _listPerformanceEvaluations = MutableLiveData<List<ContractsOutDTOItem>>()
    val listPerformanceEvaluations: LiveData<List<ContractsOutDTOItem>>? =
        _listPerformanceEvaluations

    private val _applicant = MutableLiveData<List<ComboOption>>()
    val applicant: LiveData<List<ComboOption>> = _applicant
    private val _applicantSelected = MutableLiveData<List<ComboOption>>()
    val applicantSelected: LiveData<List<ComboOption>> = _applicantSelected
    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    fun onApplicantChanged(applicant: List<ComboOption>) {
        _applicantSelected.value = applicant
    }

    fun onDescriptionChanged(description: String) {
        _description.value = description
    }

    fun getInfoInicial(sharePreference: SharePreference) {
        val user = sharePreference.getUserLogged()!!
        uiScope.launch {
            try {
                val response = remoteUsuario.getContracts(id = 6, idTipoUser = user.id_tipo_usuario)
                if (response.code() == 200) {
                    _applicant.value =
                        response.body()?.filter { it.evaluacion_desempeño == null }?.map {
                            ComboOption(id = it.id, descripcion = it.empleado!!.nombre_completo)
                        } ?: emptyList()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getInfoCandidate(sharePreference: SharePreference) {
        val user = sharePreference.getUserLogged()!!
        uiScope.launch {
            try {
                val response =
                    remoteUsuario.getContracts(id = user.id, idTipoUser = user.id_tipo_usuario)
                if (response.code() == (200)) {
                    _listPerformanceEvaluations.value =
                        response.body()!!.filter { it.evaluacion_desempeño != null }
                }
            } catch (e: Exception) {
                println("error")
            }
        }
    }

    fun onSaveInfoClicked(onSaveSuccess: () -> Unit) {
        val performanceEvaluationInDTO = PerformanceEvaluationInDTO(
            id_contrato = _applicantSelected.value!![0].id,
            descripcion = _description.value!!
        )
        uiScope.launch {
            try {
                val response = remoteUsuario.savePerformanceEvaluation(performanceEvaluationInDTO)
                if (response.code() == 201) {
                    onSaveSuccess()
                }
            } catch (e: Exception) {

            }
        }
    }
}