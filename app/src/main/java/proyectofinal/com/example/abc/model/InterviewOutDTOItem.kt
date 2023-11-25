package proyectofinal.com.example.abc.model

data class InterviewOutDTOItem(
    val activa: Boolean,
    val asunto: String,
    val fecha: String,
    val hora_fin: String,
    val hora_inicio: String,
    val id: Int,
    val resultados: String,
    val usuarios: List<Usuario>
)