package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.model.ExperienciaOut
import proyectofinal.com.example.abc.model.Rol
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExperienciaOutTest {
    @Test
    fun `equals returns true for objects with the same properties`() {
        // Arrange
        val experiencia1 = getExperienciaOut()
        val experiencia2 = getExperienciaOut()

        // Act
        val result = experiencia1 == experiencia2

        // Assert
        assertTrue(result)
    }

    @Test
    fun `equals returns false for objects with different properties`() {
        // Arrange
        val experiencia1 = getExperienciaOut()
        val experiencia2 = getExperienciaOut().copy(id = 2)

        // Act
        val result = experiencia1 == experiencia2

        // Assert
        assertFalse(result)
    }


    @Test
    fun `toString returns a non-empty string`() {
        // Arrange
        val experiencia = getExperienciaOut()

        // Act
        val result = experiencia.toString()

        // Assert
        assertTrue(result.isNotEmpty())
    }

    private fun getExperienciaOut(): ExperienciaOut {
        // Implementa lógica para crear una instancia de ExperienciaOut con valores de prueba.
        // Puedes usar mockk o simplemente crear una instancia manualmente con valores fijos.
        return ExperienciaOut(
            Rol(1, "Desarrollador"),
            actual = true,
            descripcion_actividades = "Desarrollo de software",
            fecha_fin = "2023-01-01",
            fecha_inicio = "2022-01-01",
            id = 1,
            id_candidato = 1,
            nombre_empresa = "ABC Company"
        )
    }
}