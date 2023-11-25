package proyectofinal.com.example.abc.ui.utils

import android.content.Context
import android.widget.Toast
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

fun mToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}