package proyectofinal.com.example.abc

import android.content.Context
import proyectofinal.com.example.abc.model.PosiblesRespuesta
import proyectofinal.com.example.abc.model.QuestionOutDTO
import proyectofinal.com.example.abc.model.TestResponseDTOItem
import proyectofinal.com.example.abc.repository.RemoteUsuario
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import proyectofinal.com.example.abc.model.AnswerDTO
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TechnicTestViewModel @Inject constructor(
    private val remoteUsuario: RemoteUsuario,
    @ApplicationContext private val applicationContext: Context) : ViewModel() {
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
                if (response.code().equals(200)) {
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
                if (response.code().equals(200)) {
                    idCandidato = response.body()!!.id
                    getTests()
                }
            } catch (e: Exception) {

            }
        }
    }

    fun startTest(idTestStart: Int, onSaveSuccess: () -> Unit) {
        idTest = idTestStart
        _buttonLabel.value = applicationContext.getString(R.string.next)
        uiScope.launch {
            try {
                val response = remoteUsuario.startTest(idTest!!)
                if (response.code().equals(201)) {
                    getQuestion(onSaveSuccess = onSaveSuccess)
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun getQuestion( onSaveSuccess: () -> Unit) {
        uiScope.launch {
            try {
                val response = remoteUsuario.getQuestion(idTest!!)
                if (response.code().equals(200)) {
                    _question.value = response.body()!!
                }
                if (response.code().equals(409)) {
                    finishTest(onSaveSuccess = onSaveSuccess)
                }
            } catch (e: Exception) {

            }
        }
    }

    fun onSaveAnswerClicked(onSaveSuccess: () -> Unit) {
        val answer = AnswerDTO(
            idEvaluacion = idTest!!,
            idPregunta = _question.value!!.id_pregunta,
            idRespuesta = _selectedOption.value!!.id_respuesta
        )
        uiScope.launch {
            try {
                val response = remoteUsuario.answerQuestion(answer)
                if (response.code().equals(200)) {
                    if (numQuestion == 5) {
                        numQuestion = 0
                        finishTest(onSaveSuccess = onSaveSuccess)
                    } else {
                        if (numQuestion == 4){
                            _buttonLabel.value = applicationContext.getString(R.string.send)
                        }
                        numQuestion++
                        getQuestion(onSaveSuccess = onSaveSuccess)
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun finishTest(onSaveSuccess: () -> Unit) {
        uiScope.launch {
            try {
                val response = remoteUsuario.finishTest(idTest!!)
                if (response.code().equals(200)) {
                    onSaveSuccess()
                }
            } catch (e: Exception) {
            }
        }
    }
}