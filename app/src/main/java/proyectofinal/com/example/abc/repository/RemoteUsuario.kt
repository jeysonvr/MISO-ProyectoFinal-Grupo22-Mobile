package proyectofinal.com.example.abc.repository
import com.google.gson.JsonObject
import proyectofinal.com.example.abc.apis.RetrofitHelper
import proyectofinal.com.example.abc.model.LoginDTO
import proyectofinal.com.example.abc.model.LoginResponseDTO
import proyectofinal.com.example.abc.model.RegistroDTO
import proyectofinal.com.example.abc.model.AcademicaDTO
import proyectofinal.com.example.abc.model.CandidatoInfoDTO
import proyectofinal.com.example.abc.model.CandidatoInfoReponseDTO
import proyectofinal.com.example.abc.model.CandidatoOutDTO
import proyectofinal.com.example.abc.model.ExperienciaLaboralDTO
import proyectofinal.com.example.abc.model.InformacionAcademica
import proyectofinal.com.example.abc.model.MetadataResponse
import retrofit2.Response

class RemoteUsuario {
    suspend fun registro(registroDTO: RegistroDTO): Response<String> {
        return RetrofitHelper.service.registro(registroDTO)
    }
    suspend fun login(loginDTO: LoginDTO): Response<LoginResponseDTO> {
        return RetrofitHelper.service.login(loginDTO)
    }

    suspend fun getMetadata(): Response<MetadataResponse> {
        return RetrofitHelper.service.getMetadata()
    }

    suspend fun saveCandidato(candidatoInfoDTO: CandidatoInfoDTO): Response<CandidatoOutDTO> {
        return RetrofitHelper.service.saveCandidato(candidatoInfoDTO)
    }

    suspend fun saveExperienciaLaboral(experienciaLaboralDTO: ExperienciaLaboralDTO): Response<String> {
        return RetrofitHelper.service.saveCandidatoExperiencia(experienciaLaboralDTO)
    }

    suspend fun saveInformacionAcademica(informacionAcademicaDTO: AcademicaDTO): Response<String> {
        return RetrofitHelper.service.saveCandidatoAcademica(informacionAcademicaDTO)
    }

    suspend fun getCandidato(email: String): Response<CandidatoInfoReponseDTO> {
        return RetrofitHelper.service.getCandidato(email = email)
    }
}