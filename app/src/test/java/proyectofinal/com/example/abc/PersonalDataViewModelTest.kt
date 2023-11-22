package proyectofinal.com.example.abc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.personal_data.PersonalDataViewModel
import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PersonalDataViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteUsuario: RemoteUsuario

    @Mock
    private lateinit var sharePreference: SharePreference

    @Mock
    private lateinit var fullNameObserver: Observer<String>

    @Mock
    private lateinit var emailObserver: Observer<String>

    @Mock
    private lateinit var edadObserver: Observer<String>

    @Mock
    private lateinit var phoneObserver: Observer<String>

    @Mock
    private lateinit var countriesObserver: Observer<List<ComboOption>>

    @Mock
    private lateinit var languagesObserver: Observer<List<ComboOption>>

    @Mock
    private lateinit var softSkillsObserver: Observer<List<ComboOption>>

    @Mock
    private lateinit var skillsObserver: Observer<List<ComboOption>>

    @Mock
    private lateinit var countrySelectedObserver: Observer<List<ComboOption>>

    @Mock
    private lateinit var languagesSelectedObserver: Observer<List<ComboOption>>

    @Mock
    private lateinit var softSkillsSelectedObserver: Observer<List<ComboOption>>

    @Mock
    private lateinit var skillsSelectedObserver: Observer<List<ComboOption>>

    private lateinit var viewModel: PersonalDataViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = PersonalDataViewModel()
        viewModel.fullName.observeForever(fullNameObserver)
        viewModel.email.observeForever(emailObserver)
        viewModel.edad.observeForever(edadObserver)
        viewModel.phone.observeForever(phoneObserver)
        viewModel.countries.observeForever(countriesObserver)
        viewModel.languages.observeForever(languagesObserver)
        viewModel.softSkills.observeForever(softSkillsObserver)
        viewModel.skills.observeForever(skillsObserver)
        viewModel.countrySelected.observeForever(countrySelectedObserver)
        viewModel.languagesSelected?.observeForever(languagesSelectedObserver)
        viewModel.softSkillsSelected?.observeForever(softSkillsSelectedObserver)
        viewModel.skillsSelected?.observeForever(skillsSelectedObserver)
        viewModel.remoteUsuario = remoteUsuario
    }

    @Test
    fun testFullNameChanged() {
        viewModel.onFullNameChanged("John Doe")
        verify(fullNameObserver).onChanged("John Doe")
    }

    @Test
    fun testPhoneChanged() {
        viewModel.onPhoneChanged("2555535532")
        verify(phoneObserver).onChanged("2555535532")
    }
    @Test
    fun testEdadChanged() {
        viewModel.onAgeChanged("25")
        verify(edadObserver).onChanged("25")
    }

    @Test
    fun onFullNameChanged_setsFullName() {
        // Arrange
        val fullName = "John Doe"

        // Act
        viewModel.onFullNameChanged(fullName)

        // Assert
        verify(fullNameObserver).onChanged("John Doe")
    }

    // Repite pruebas similares para otros campos y métodos
}