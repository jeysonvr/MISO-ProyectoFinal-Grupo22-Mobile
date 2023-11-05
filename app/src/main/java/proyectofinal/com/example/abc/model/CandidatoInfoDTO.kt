package proyectofinal.com.example.abc.model

data class CandidatoInfoDTO(
    val edad: Int,
    val email: String,
    val experiencia: List<Experiencia>,
    val habilidadesBlandas: List<Int>,
    val habilidadesTecnicas: List<Int>,
    val id_pais: Int,
    val idiomas: List<Int>,
    val informacionAcademica: List<InformacionAcademica>,
    val numero_telefono: String
)