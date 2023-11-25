package proyectofinal.com.example.abc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.academic_data.AcademicDataViewModel
import proyectofinal.com.example.abc.ui.utils.getActualYear
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class AcademicDataViewModelTest {



    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteUsuario: RemoteUsuario

    private lateinit var viewModel: AcademicDataViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = AcademicDataViewModel()
    }

    @Test
    fun onCarrerChanged_updatesCarrerLiveData() {
        val carrer = "Computer Science"
        viewModel.onCarrerChanged(carrer)

        val carrerLiveData = viewModel.carrer.value

        assertEquals(carrer, carrerLiveData)
    }

    @Test
    fun onStartYearChanged_updatesStartYearLiveData() {
        val startYear = "2023"
        viewModel.onStartYearChanged(startYear)

        val startYearLiveData = viewModel.startYear.value

        assertEquals(startYear, startYearLiveData)
    }

    @Test
    fun inProgressChanged_updatesFinalYearAndInProgressLiveData() {
        val checked = true
        viewModel.inProgressChanged(checked)

        val finalYearLiveData = viewModel.finalYear.value
        val inProgressLiveData = viewModel.inProgress.value

        assertTrue(checked)
        assertTrue(finalYearLiveData == getActualYear().toString())
    }

    @Test
    fun onInstituteEducationChanged_updatesInstituteEducationLiveData() {
        val instituteEducation = "ABC University"
        viewModel.onInstituteEducationChanged(instituteEducation)

        val instituteEducationLiveData = viewModel.instituteEducation.value

        assertEquals(instituteEducation, instituteEducationLiveData)
    }

    @Test
    fun onFinalYearChanged_updatesFinalYearLiveData() {
        val finalYear = "2025"
        viewModel.onFinalYearChanged(finalYear)

        val finalYearLiveData = viewModel.finalYear.value

        assertEquals(finalYear, finalYearLiveData)
    }
}