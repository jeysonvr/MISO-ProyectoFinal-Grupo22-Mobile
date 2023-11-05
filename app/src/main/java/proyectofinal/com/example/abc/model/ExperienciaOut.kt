package proyectofinal.com.example.abc.model

data class ExperienciaOut(
    val Rol: Rol,
    val `actual`: Boolean,
    val descripcion_actividades: String,
    val fecha_fin: String,
    val fecha_inicio: String,
    val id: Int,
    val id_candidato: Int,
    val nombre_empresa: String
)