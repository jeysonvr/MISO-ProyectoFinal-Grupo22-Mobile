package proyectofinal.com.example.abc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.experience.LaboralExperienceViewModel
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.getActualYear
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LaboralExperienceViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteUsuario: RemoteUsuario

    private lateinit var viewModel: LaboralExperienceViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LaboralExperienceViewModel(remoteUsuario)
    }

    @Test
    fun onRolChanged_updatesRolSelectedLiveData() {
        val mockRoles = listOf(ComboOption("Role 1", 1), ComboOption("Role 2", 2))
        viewModel.onRolChanged(mockRoles)

        val rolSelected = viewModel.rolSelected.value

        assertEquals(mockRoles, rolSelected)
    }

    @Test
    fun onStartDateChanged_updatesStartDateLiveData() {
        val startDate = "2023-01-01"
        viewModel.onStartDateChanged(startDate)

        val startDateLiveData = viewModel.startDate.value

        assertEquals(startDate, startDateLiveData)
    }

    @Test
    fun onCompanyNameChanged_updatesCompanyNameLiveData() {
        val companyName = "Test Company"
        viewModel.onCompanyNameChanged(companyName)

        val companyNameLiveData = viewModel.companyName.value

        assertEquals(companyName, companyNameLiveData)
    }

    @Test
    fun onFinalDateChanged_updatesFinalDateLiveData() {
        val finalDate = "2023-12-31"
        viewModel.onFinalDateChanged(finalDate)

        val finalDateLiveData = viewModel.finalDate.value

        assertEquals(finalDate, finalDateLiveData)
    }

    @Test
    fun onDescriptionChanged_updatesDescriptionLiveData() {
        val description = "Test description"
        viewModel.onDescriptionChanged(description)

        val descriptionLiveData = viewModel.description.value

        assertEquals(description, descriptionLiveData)
    }

    @Test
    fun onWorkHere_updatesWorkHereAndFinalDateLiveData() {
        val checked = true
        viewModel.onWorkHere(checked)

        val workHereLiveData = viewModel.workHere.value
        val finalDateLiveData = viewModel.finalDate.value

        assertTrue(checked)
        assertTrue(finalDateLiveData == getActualYear().toString())
    }
}