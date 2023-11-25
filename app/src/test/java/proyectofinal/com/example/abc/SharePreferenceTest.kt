package proyectofinal.com.example.abc

import android.content.Context
import android.content.SharedPreferences
import proyectofinal.com.example.abc.ui.utils.SharePreference
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SharePreferenceTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var sharedPreferences: SharedPreferences

    lateinit var sharePreference: SharePreference

    val editor = Mockito.mock(SharedPreferences.Editor::class.java)
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(context.getSharedPreferences("", Context.MODE_PRIVATE)).thenReturn(sharedPreferences)

        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)
        sharePreference = SharePreference(context)
    }

    @Test
    fun getString_shouldReturnDefaultValue() {
        val key = "test_key"

        val result = sharePreference.getString(key)

        assertEquals(null, result)
    }

    @Test
    fun setString_shouldStoreValue() {
        val key = "test_key"
        val value = "test_value"

        val editorCaptor: ArgumentCaptor<SharedPreferences.Editor> = ArgumentCaptor.forClass(SharedPreferences.Editor::class.java)
        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)

        sharePreference.setString(key, value)

        // Verificar que se llama a putString() y apply() en el editor capturado
        Mockito.verify(editor).putString(key, value)
        Mockito.verify(editor).apply()
    }

    @Test
    fun getUserLogged_shouldReturnNullWhenEmpty() {
        Mockito.`when`(sharedPreferences.getString("user", null)).thenReturn("")

        val result = sharePreference.getUserLogged()

        assertNull(result)
    }

    @Test
    fun getUserLogged_shouldReturnLoginResponseDTO() {
        val loginResponseJson = "{\"usuario\":\"testUser\",\"token\":\"testToken\",\"id_tipo_usuario\":1}"
        Mockito.`when`(sharedPreferences.getString("user", null)).thenReturn(loginResponseJson)

        val result = sharePreference.getUserLogged()

        assertEquals("testUser", result?.usuario)
        assertEquals("testToken", result?.token)
    }
}