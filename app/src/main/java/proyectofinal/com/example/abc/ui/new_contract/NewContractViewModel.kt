package proyectofinal.com.example.abc.ui.new_contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import proyectofinal.com.example.abc.model.ContratoInDTO
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import proyectofinal.com.example.abc.ui.utils.toComboOptions
import proyectofinal.com.example.abc.model.EmpresasOutDTO
import proyectofinal.com.example.abc.model.ProjectOutDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewContractViewModel @Inject constructor(private val remoteUsuario: RemoteUsuario) :
    ViewModel() {
    private val _applicant = MutableLiveData<List<ComboOption>>()
    val applicant: LiveData<List<ComboOption>> = _applicant
    private val _company = MutableLiveData<List<ComboOption>>()
    val company: LiveData<List<ComboOption>> = _company
    private val _project = MutableLiveData<List<ComboOption>>()
    val project: LiveData<List<ComboOption>> = _project
    private val _rol = MutableLiveData<List<ComboOption>>()
    val rol: LiveData<List<ComboOption>> = _rol
    private val _applicantSelected = MutableLiveData<List<ComboOption>>()
    val applicantSelected: LiveData<List<ComboOption>> = _applicantSelected
    private val _companySelected = MutableLiveData<List<ComboOption>>()
    val companySelected: LiveData<List<ComboOption>>? = _companySelected
    private val _projectSelected = MutableLiveData<List<ComboOption>>()
    val projectSelected: LiveData<List<ComboOption>>? = _projectSelected
    private val _rolSelected = MutableLiveData<List<ComboOption>>()
    val rolSelected: LiveData<List<ComboOption>>? = _rolSelected
    private val listCompanies : MutableLiveData<EmpresasOutDTO?> = MutableLiveData<EmpresasOutDTO?>()
    private val listProjects : MutableLiveData<ProjectOutDTO?> = MutableLiveData<ProjectOutDTO?>()

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun onApplicantChanged(applicant: List<ComboOption>) {
        _applicantSelected.value = applicant
    }

    fun onCompanyChanged(company: List<ComboOption>) {
        if (company.isNotEmpty()) {
            _companySelected.value = company
            listCompanies.value?.filter { it.usuario.id == company[0].id }.let {
                getProjects(it?.get(0)!!.id)
            }
        }
    }

    fun onProjectChanged(project: List<ComboOption>) {
        _projectSelected.value = project
        getRoles(_projectSelected.value!![0].id)
    }

    fun onRolChanged(rol: List<ComboOption>) {
        _rolSelected.value = rol
    }

    fun getInfoInicial(sharePreference: SharePreference) {
        uiScope.launch {
            try {
                val response = remoteUsuario.getCandidatos()
                if (response.code() == 200) {
                    _applicant.value = response.body()?.map {
                        ComboOption(id = it.usuario.id, descripcion = it.usuario.nombre_completo)
                    } ?: emptyList()
                    getCompanies()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getCompanies() {
        uiScope.launch {
            try {
                val response = remoteUsuario.getCompanies()
                if (response.code() == 200) {
                    listCompanies.value = response.body()
                    _company.value = response.body()?.map {
                        ComboOption(id = it.usuario.id, descripcion = it.usuario.nombre_completo)
                    } ?: emptyList()
                }
            } catch (e: Exception) {
            }
        }
    }

    fun getRoles(idProject : Int) {
        var listRolesProyecto = emptyList<ComboOption>()
        listProjects.value?.filter { it.id == idProject }?.map {
            it.rolesProyecto.forEach {
                listRolesProyecto = listRolesProyecto.plus(ComboOption(id = it.id, descripcion = it.rol))
            }
        }
        _rol.value = listRolesProyecto
    }

    fun getProjects(idCompanie: Int) {
        _projectSelected.value = emptyList()
        _project.value = emptyList()
        uiScope.launch {
            try {
                val response = remoteUsuario.getProjects(idCompanie)
                if (response.code() == 200) {
                    listProjects.value = response.body()
                    _project.value = response.body()?.map {
                        ComboOption(id = it.id, descripcion = it.nombre)
                    }
                }
            } catch (e: Exception) {

            }
        }
    }


    fun onSaveInfoClicked(onSaveSuccess: () -> Unit, onSaveFailed: () -> Unit) {
        val contrato = ContratoInDTO(
            idUsuarioEmpleado = _applicantSelected.value!![0].id,
            idUsuarioEmpresa = _companySelected.value!![0].id,
            idRol = _rolSelected.value!![0].id,
            idProyecto = _projectSelected.value!![0].id
        )
        uiScope.launch() {
            try {
                val response = remoteUsuario.saveContrato(contrato = contrato)
                if (response.code() == 200) {
                    onSaveSuccess()
                } else {
                    if (response.code() == 419) {
                        onSaveFailed()
                    }
                }
            } catch (e: Exception) {

            }

        }
    }
}