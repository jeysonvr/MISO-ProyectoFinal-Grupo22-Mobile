package proyectofinal.com.example.abc.apis

import com.google.gson.GsonBuilder
import proyectofinal.com.example.abc.ui.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitHelper {

    var gson = GsonBuilder()
        .setLenient()
        .create()

    val logLevel = HttpLoggingInterceptor.Level.BODY
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(logLevel)
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createInsecureOkHttpClient(loggingInterceptor))
            .build()
    }

    fun createInsecureOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val trustAllCertificates = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCertificates, java.security.SecureRandom())

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .sslSocketFactory(sslContext.socketFactory, trustAllCertificates[0] as X509TrustManager)
            .hostnameVerifier { hostname, session -> true }
            .build()
    }



    val service: APIService = getRetrofit().create(APIService::class.java)
}