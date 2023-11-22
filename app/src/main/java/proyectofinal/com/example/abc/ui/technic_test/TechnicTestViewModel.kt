package proyectofinal.com.example.abc.ui.technic_test

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import proyectofinal.com.example.abc.R
import proyectofinal.com.example.abc.model.AnswerDTO
import proyectofinal.com.example.abc.model.PosiblesRespuesta
import proyectofinal.com.example.abc.model.QuestionOutDTO
import proyectofinal.com.example.abc.model.TestResponseDTOItem
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TechnicTestViewModel @Inject constructor(
    private val remoteUsuario: RemoteUsuario,
    @ApplicationContext private val applicationContext: Context
) : ViewModel() {
    private val _listTests = MutableLiveData<List<TestResponseDTOItem>>()
    val listTests: LiveData<List<TestResponseDTOItem>>? = _listTests

    private val _question = MutableLiveData<QuestionOutDTO>()
    val question: LiveData<QuestionOutDTO>? = _question
    private val _selectedOption = MutableLiveData<PosiblesRespuesta>()
    val selectedOption: LiveData<PosiblesRespuesta> = _selectedOption
    private val _buttonLabel = MutableLiveData<String>()
    val buttonLabel: LiveData<String> = _buttonLabel


    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var idCandidato: Int? = null
    private var numQuestion = 0
    private var idTest: Int? = null
    fun setSelectedOption(selectedOption: PosiblesRespuesta) {
        _selectedOption.value = selectedOption
    }

    fun getTests() {
        uiScope.launch {
            try {
                val response = remoteUsuario.getTests(idCandidato!!)
                if (response.code() == (200)) {
                    _listTests.value = response.body()!!
                }
            } catch (e: Exception) {

            }
        }
    }

    fun getInfoUser(sharePreference: SharePreference) {
        uiScope.launch {
            try {
                val response = remoteUsuario.getCandidato(sharePreference.getUserLogged()!!.usuario)
                if (response.code() == (200)) {
                    idCandidato = response.body()!!.id
                    getTests()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun startTest(idTestStart: Int, onSaveSuccess: () -> Unit, onSaveFailed: () -> Unit) {
        idTest = idTestStart
        _buttonLabel.value = applicationContext.getString(R.string.next)
        uiScope.launch {
            try {
                val response = remoteUsuario.startTest(idTest!!)
                if (response.code() == (201)) {
                    getQuestion(onSaveSuccess = onSaveSuccess, onSaveFailed = onSaveFailed)
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun getQuestion(onSaveSuccess: () -> Unit, onSaveFailed: () -> Unit) {
        uiScope.launch {
            try {
                val response = remoteUsuario.getQuestion(idTest!!)
                if (response.code() == (200)) {
                    _question.value = response.body()!!
                }else {
                    if (response.code() == (409)) {
                        finishTest(onSaveSuccess = onSaveSuccess, onSaveFailed = onSaveFailed)
                    } else {
                        onSaveFailed()
                    }
                }

            } catch (e: Exception) {

            }
        }
    }

    fun onSaveAnswerClicked(onSaveSuccess: () -> Unit, onSaveFailed: () -> Unit) {
        val answer = AnswerDTO(
            idEvaluacion = idTest!!,
            idPregunta = _question.value!!.id_pregunta,
            idRespuesta = _selectedOption.value!!.id_respuesta
        )
        uiScope.launch {
            try {
                val response = remoteUsuario.answerQuestion(answer)
                if (response.code() == (200)) {
                    if (numQuestion == 5) {
                        numQuestion = 0
                        finishTest(onSaveSuccess = onSaveSuccess, onSaveFailed = onSaveFailed)
                    } else {
                        if (numQuestion == 4) {
                            _buttonLabel.value = applicationContext.getString(R.string.send)
                        }
                        numQuestion++
                        getQuestion(onSaveSuccess = onSaveSuccess, onSaveFailed = onSaveFailed)
                    }
                } else {
                    onSaveFailed()
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun finishTest(onSaveSuccess: () -> Unit, onSaveFailed: () -> Unit) {
        uiScope.launch {
            try {
                val response = remoteUsuario.finishTest(idTest!!)
                if (response.code() == (200)) {
                    onSaveSuccess()
                } else {
                    onSaveFailed()
                }
            } catch (e: Exception) {
            }
        }
    }
}