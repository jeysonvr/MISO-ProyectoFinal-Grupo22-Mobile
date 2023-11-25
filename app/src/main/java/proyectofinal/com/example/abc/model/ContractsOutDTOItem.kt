package proyectofinal.com.example.abc.model

data class ContractsOutDTOItem(
    val activo: Boolean,
    val empleado: UsuarioCompany?,
    val empresa: UsuarioCompany?,
    val evaluacion_desempeño: EvaluacionDesempeño?,
    val fecha_fin: String?,
    val fecha_inicio: String?,
    val id: Int
)