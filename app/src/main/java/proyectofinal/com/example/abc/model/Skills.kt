package proyectofinal.com.example.abc.model

import proyectofinal.com.example.abc.ui.utils.SelectableOption

data class Skills(
    override val descripcion: String,
    override val id: Int
) : SelectableOption