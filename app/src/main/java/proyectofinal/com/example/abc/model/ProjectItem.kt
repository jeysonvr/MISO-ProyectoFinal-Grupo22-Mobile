package proyectofinal.com.example.abc.model

data class ProjectItem(
    val descripcion: String,
    val id: Int,
    val id_empresa: Int,
    val id_estado: Int,
    val nombre: String,
    val rolesProyecto: List<RolesProyecto>
)