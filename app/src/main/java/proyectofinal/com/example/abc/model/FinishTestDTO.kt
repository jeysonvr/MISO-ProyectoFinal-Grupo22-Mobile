package proyectofinal.com.example.abc.model

data class FinishTestDTO(
    val calificacion: String,
    val descripcion: String,
    val fecha_inicio: String,
    val id: Int,
    val id_candidato: Int,
    val id_estado_evaluacion: Int,
    val tiempo_limite: Int,
    val ultimo_nivel: Int
)