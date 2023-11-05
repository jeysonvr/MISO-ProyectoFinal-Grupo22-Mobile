package proyectofinal.com.example.abc.apis
import proyectofinal.com.example.abc.model.LoginDTO
import proyectofinal.com.example.abc.model.LoginResponseDTO
import proyectofinal.com.example.abc.model.RegistroDTO
import com.google.gson.JsonObject
import proyectofinal.com.example.abc.model.AcademicaDTO
import proyectofinal.com.example.abc.model.CandidatoInfoDTO
import proyectofinal.com.example.abc.model.CandidatoInfoReponseDTO
import proyectofinal.com.example.abc.model.CandidatoOutDTO
import proyectofinal.com.example.abc.model.ExperienciaLaboralDTO
import proyectofinal.com.example.abc.model.MetadataResponse


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    /**
     * Obtiene productos del servicio por medio de una query
     * @param query
     **/
    @POST("/registro/usuario")
    suspend fun registro(
        @Body registroDTO: RegistroDTO
    ): Response<String>


    @POST("/registro/login")
    suspend fun login(
        @Body loginDTO: LoginDTO
    ): Response<LoginResponseDTO>
    /**
     * Obtiene descripción del producto
     * @param item_id
     **/
    @GET("candidato/metadata")
    suspend fun getMetadata(

    ): Response<MetadataResponse>

    @POST("/candidato/")
    suspend fun saveCandidato(
        @Body candidatoInfoDTO: CandidatoInfoDTO
    ): Response<CandidatoOutDTO>

    @POST("candidato/infoAcademica")
    suspend fun saveCandidatoAcademica(
        @Body academicaDTO: AcademicaDTO
    ): Response<String>

    @POST("candidato/experiencia")
    suspend fun saveCandidatoExperiencia(
        @Body experienciaLaboralDTO: ExperienciaLaboralDTO
    ): Response<String>

    @GET("candidato/info/{email}")
    suspend fun getCandidato(
        @Path("email") email: String
    ): Response<CandidatoInfoReponseDTO>
}