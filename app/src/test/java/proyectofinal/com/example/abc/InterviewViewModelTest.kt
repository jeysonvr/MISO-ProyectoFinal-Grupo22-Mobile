package proyectofinal.com.example.abc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import proyectofinal.com.example.abc.model.InterviewOutDTOItem
import proyectofinal.com.example.abc.repository.RemoteUsuario
import proyectofinal.com.example.abc.ui.interview.InterviewViewModel
import proyectofinal.com.example.abc.ui.utils.SharePreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class InterviewViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var remoteUsuario: RemoteUsuario

    @Mock
    lateinit var sharePreference: SharePreference

    @Mock
    lateinit var observerListInterviews: Observer<List<InterviewOutDTOItem>>

    @Mock
    lateinit var observerIsDetail: Observer<Boolean>

    @Mock
    lateinit var observerShowDialog: Observer<Boolean>

    @Mock
    lateinit var observerComment: Observer<String>

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: InterviewViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = InterviewViewModel(remoteUsuario)
        viewModel.listInterviews?.observeForever(observerListInterviews)
        viewModel.isDetail.observeForever(observerIsDetail)
        viewModel.showDialog.observeForever(observerShowDialog)
        viewModel.comment.observeForever(observerComment)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    /*@Test
    fun `getInitialData sets _isDetail based on user type`() = runBlockingTest {
        // Arrange
        val userLoggued = mockk<SharePreference.UserLogged>()
        every { sharePreference.getUserLogged() } returns userLoggued
        every { userLoggued.id_tipo_usuario } returns 1
        // Act
        viewModel.getInitialData(sharePreference)
        // Assert
        verify(observerIsDetail).onChanged(true)
    }
    @Test
    fun `getInitialData calls getInterview on successful response`() = runBlockingTest {
        // Arrange
        val userLoggued = mockk<SharePreference.UserLogged>()
        every { sharePreference.getUserLogged() } returns userLoggued
        every { userLoggued.id } returns 123
        coEvery { remoteUsuario.getInterview(123) } returns Response.success(listOf(mockk()))
        // Act
        viewModel.getInitialData(sharePreference)
        // Assert
        verify(remoteUsuario).getInterview(123)
        verify(observerListInterviews).onChanged(any())
    }
    @Test
    fun `getInitialData does not call getInterview on null user`() = runBlockingTest {
        // Arrange
        every { sharePreference.getUserLogged() } returns null
        // Act
        viewModel.getInitialData(sharePreference)
        // Assert
        verify(remoteUsuario, never()).getInterview(any())
        verify(observerListInterviews, never()).onChanged(any())
    }*/

    @Test
    fun `showDialog sets _showDialog and _comment`() {
        // Arrange
        val detail = "Some detail"

        // Act
        viewModel.showDialog(detail)

        // Assert
        assert(viewModel.showDialog.value == true)
        assert(viewModel.comment.value == detail)
        verify(observerShowDialog).onChanged(true)
        verify(observerComment).onChanged(detail)
    }

    @Test
    fun `hideDialog sets _showDialog to false`() {
        // Act
        viewModel.hideDialog()

        // Assert
        assert(viewModel.showDialog.value == false)
        verify(observerShowDialog).onChanged(false)
    }

}