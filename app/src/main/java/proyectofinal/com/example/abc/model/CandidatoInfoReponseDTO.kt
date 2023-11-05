package proyectofinal.com.example.abc.model

data class CandidatoInfoReponseDTO(
    val edad: Int,
    val experiencia: List<ExperienciaOut>,
    val habilidadesBlandas: List<SoftSkills>,
    val habilidadesTecnicas: List<Skills>,
    val id: Int,
    val id_pais: Int,
    val idiomas: List<Language>,
    val informacionAcademica: List<InformacionAcademicaOut>,
    val numero_telefono: String,
    val paisOrigen: Country,
    val usuario: Usuario
)