package proyectofinal.com.example.abc.model

data class InformacionAcademicaOut(
    val en_curso: Boolean,
    val fecha_fin: String,
    val fecha_inicio: String,
    val id: Int,
    val id_candidato: Int,
    val institucion: String,
    val titulo: String
)