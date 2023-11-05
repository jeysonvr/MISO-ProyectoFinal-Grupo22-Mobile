package proyectofinal.com.example.abc.model

import proyectofinal.com.example.abc.ui.utils.SelectableOption

data class Role(
    override val id: Int,
    val rol: String
) : SelectableOption {
    override val descripcion: String
        get() = rol
}