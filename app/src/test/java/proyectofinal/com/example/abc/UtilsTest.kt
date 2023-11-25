package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.ui.utils.ComboOption
import proyectofinal.com.example.abc.ui.utils.SelectableOption
import proyectofinal.com.example.abc.ui.utils.getActualYear
import proyectofinal.com.example.abc.ui.utils.toComboOptions
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class UtilsTest {

    @Test
    fun getActualYear_returnsCurrentYear() {
        val expectedYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        val actualYear = getActualYear()
        assertEquals(expectedYear, actualYear)
    }

    @Test
    fun listToComboOptions_mapsListToComboOptions() {
        val selectableOptions = listOf(ComboOption("Option 1", 1), ComboOption("Option 2", 2))
        val comboOptions = selectableOptions.toComboOptions()
        assertEquals(2, comboOptions.size)
        assertEquals("Option 1", comboOptions[0].descripcion)
        assertEquals(1, comboOptions[0].id)
        assertEquals("Option 2", comboOptions[1].descripcion)
        assertEquals(2, comboOptions[1].id)
    }
}