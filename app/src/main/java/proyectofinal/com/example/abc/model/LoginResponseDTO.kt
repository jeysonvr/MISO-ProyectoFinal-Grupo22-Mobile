package proyectofinal.com.example.abc.model

data class LoginResponseDTO(
    val id_tipo_usuario: Int,
    val id: Int,
    val token: String,
    val usuario: String
)
