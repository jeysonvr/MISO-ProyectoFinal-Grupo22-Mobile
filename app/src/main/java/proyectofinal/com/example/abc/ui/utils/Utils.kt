package proyectofinal.com.example.abc.ui.utils

import java.util.Calendar

const val BASE_URL= "https://34.117.49.114";

fun getActualYear(): Int{
    return Calendar.getInstance().get(Calendar.YEAR)
}

inline fun <reified T> List<T>.toComboOptions(): List<ComboOption> where T : SelectableOption {
    return this.map { item ->
        ComboOption(
            descripcion = item.descripcion,
            id = item.id
        )
    }
}