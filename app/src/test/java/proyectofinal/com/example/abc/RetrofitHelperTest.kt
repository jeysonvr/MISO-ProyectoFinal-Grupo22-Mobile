package proyectofinal.com.example.abc

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import proyectofinal.com.example.abc.apis.RetrofitHelper
import proyectofinal.com.example.abc.ui.utils.BASE_URL
import junit.framework.TestCase.assertNotNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelperTest {

    private lateinit var gson: Gson
    private lateinit var loggingInterceptor: HttpLoggingInterceptor

    @Before
    fun setup() {
        gson = GsonBuilder().setLenient().create()
        loggingInterceptor = HttpLoggingInterceptor()
    }

    @Test
    fun testCreateInsecureOkHttpClient() {
        val okHttpClient = RetrofitHelper.createInsecureOkHttpClient(loggingInterceptor)

        // Add assertions here to verify the OkHttpClient configuration as expected.
        // For example, you can verify if the SSL socket factory is set correctly.
    }

    @Test
    fun testGetRetrofit() {
        val retrofit = RetrofitHelper.getRetrofit()

        // Verifica que la instancia de Retrofit no sea nula
        assertNotNull(retrofit)
        // Add assertions here to compare the result with the mock Retrofit.
    }
}