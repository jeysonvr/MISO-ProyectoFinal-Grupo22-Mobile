package proyectofinal.com.example.abc.ui.interview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import proyectofinal.com.example.abc.model.InterviewOutDTOItem
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterviewViewModel @Inject constructor(private val remoteUsuario: RemoteUsuario) :
    ViewModel() {

    private val _listInterviews = MutableLiveData<List<InterviewOutDTOItem>>()
    val listInterviews: LiveData<List<InterviewOutDTOItem>>? = _listInterviews
    private val _isDetail = MutableLiveData<Boolean>()
    val isDetail: LiveData<Boolean> = _isDetail
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog
    private val _comment = MutableLiveData<String>()
    val comment: LiveData<String> = _comment
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    fun getInitialData(sharePreference: SharePreference) {
        val userLoggued = sharePreference.getUserLogged()
        userLoggued?.let {
            _isDetail.value = (it.id_tipo_usuario == 1 || it.id_tipo_usuario == 3)
        }
        uiScope.launch {
            try {
                val response = remoteUsuario.getInterview(userLoggued!!.id)
                if (response.code() == (200)) {
                    _listInterviews.value = response.body()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun showDialog(detail: String?) {
        _showDialog.value = true
        _comment.value = detail
    }

    fun hideDialog() {
        _showDialog.value = false
    }
}