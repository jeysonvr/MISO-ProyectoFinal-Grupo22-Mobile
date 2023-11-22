package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.model.FinishTestDTO
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FinishTestDTOTest {
    @Test
    fun `equals returns true for objects with the same properties`() {
        // Arrange
        val finishTest1 = getFinishTestDTO()
        val finishTest2 = getFinishTestDTO()

        // Act
        val result = finishTest1 == finishTest2

        // Assert
        assertTrue(result)
    }

    @Test
    fun `equals returns false for objects with different properties`() {
        // Arrange
        val finishTest1 = getFinishTestDTO()
        val finishTest2 = getFinishTestDTO().copy(id = 2)

        // Act
        val result = finishTest1 == finishTest2

        // Assert
        assertFalse(result)
    }



    @Test
    fun `toString returns a non-empty string`() {
        // Arrange
        val finishTest = getFinishTestDTO()

        // Act
        val result = finishTest.toString()

        // Assert
        assertTrue(result.isNotEmpty())
    }

    private fun getFinishTestDTO(): FinishTestDTO {
        // Implementa lógica para crear una instancia de FinishTestDTO con valores de prueba.
        // Puedes usar mockk o simplemente crear una instancia manualmente con valores fijos.
        return FinishTestDTO(
            calificacion = "Aprobado",
            descripcion = "Descripción de prueba",
            fecha_inicio = "2023-01-01",
            id = 1,
            id_candidato = 1,
            id_estado_evaluacion = 2,
            tiempo_limite = 60,
            ultimo_nivel = 3
        )
    }
}