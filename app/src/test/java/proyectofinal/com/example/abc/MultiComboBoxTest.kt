package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.getSelectedOptions
import org.junit.Assert.assertEquals
import org.junit.Test

internal class MultiComboBoxTest {

    @Test
    fun `test getSelectedOptions`() {
        // Datos de ejemplo
        val selectedOptionsList = listOf(1, 3, 4)
        val options = listOf(
            ComboOption("Option 1",1 ),
            ComboOption("Option 2",2),
            ComboOption("Option 3",3),
            ComboOption("Option 4",4)
        )

        // Llama a la función que se va a probar
        val result = getSelectedOptions(selectedOptionsList, options)

        // Verifica que el resultado sea el esperado
        assertEquals("Option 1, Option 3, Option 4", result)
    }
}