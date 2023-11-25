package proyectofinal.com.example.abc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.mockk
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.new_contract.NewContractViewModel
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class NewContractViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var remoteUsuario: RemoteUsuario

    @Mock
    lateinit var sharePreference: SharePreference

    @Mock
    lateinit var observerApplicant: Observer<List<ComboOption>>

    @Mock
    lateinit var observerCompany: Observer<List<ComboOption>>

    @Mock
    lateinit var observerProject: Observer<List<ComboOption>>

    @Mock
    lateinit var observerRol: Observer<List<ComboOption>>

    @Mock
    lateinit var observerApplicantSelected: Observer<List<ComboOption>>

    @Mock
    lateinit var observerCompanySelected: Observer<List<ComboOption>>

    @Mock
    lateinit var observerProjectSelected: Observer<List<ComboOption>>

    @Mock
    lateinit var observerRolSelected: Observer<List<ComboOption>>

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: NewContractViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = NewContractViewModel(remoteUsuario)
        viewModel.applicant.observeForever(observerApplicant)
        viewModel.company.observeForever(observerCompany)
        viewModel.project.observeForever(observerProject)
        viewModel.rol.observeForever(observerRol)
        viewModel.applicantSelected.observeForever(observerApplicantSelected)
        viewModel.companySelected?.observeForever(observerCompanySelected)
        viewModel.projectSelected?.observeForever(observerProjectSelected)
        viewModel.rolSelected?.observeForever(observerRolSelected)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `onApplicantChanged sets the value in _applicantSelected`() {
        // Arrange
        val applicant = listOf(mockk<ComboOption>())

        // Act
        viewModel.onApplicantChanged(applicant)

        // Assert
        assert(viewModel.applicantSelected.value == applicant)
        verify(observerApplicantSelected).onChanged(applicant)
    }

    /*@Test
    fun `onCompanyChanged sets the value in _companySelected and calls getProjects`() = runBlockingTest {
        // Arrange
        val company = listOf(mockk<ComboOption>())
        // Act
        viewModel.onCompanyChanged(company)
        // Assert
        assert(viewModel.companySelected?.value == company)
        verify(observerCompanySelected).onChanged(company)
        verify(remoteUsuario).getProjects(company[0].id)
    }*/

    @Test
    fun `onRolChanged sets the value in _rolSelected`() {
        // Arrange
        val rol = listOf(mockk<ComboOption>())

        // Act
        viewModel.onRolChanged(rol)

        // Assert
        assert(viewModel.rolSelected?.value == rol)
        verify(observerRolSelected).onChanged(rol)
    }


}