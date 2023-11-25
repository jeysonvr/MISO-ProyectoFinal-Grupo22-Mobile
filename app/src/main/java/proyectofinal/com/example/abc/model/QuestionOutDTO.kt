package proyectofinal.com.example.abc.model

data class QuestionOutDTO(
    val id_pregunta: Int,
    val nivel: Int,
    val posibles_respuestas: List<PosiblesRespuesta>,
    val pregunta: String
)
