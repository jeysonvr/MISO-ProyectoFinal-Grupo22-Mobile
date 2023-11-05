package proyectofinal.com.example.abc.model

import proyectofinal.com.example.abc.ui.utils.SelectableOption

data class Language(
    override val id: Int,
    val idioma: String
): SelectableOption {
    override val descripcion: String
        get() = idioma
}