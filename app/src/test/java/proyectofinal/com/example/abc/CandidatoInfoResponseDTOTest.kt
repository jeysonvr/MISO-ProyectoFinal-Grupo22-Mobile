package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.model.CandidatoInfoReponseDTO
import proyectofinal.com.example.abc.model.Country
import proyectofinal.com.example.abc.model.Usuario
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CandidatoInfoReponseDTOTest {
    @Test
    fun `equals returns true for objects with the same properties`() {
        // Arrange
        val candidato1 = getCandidatoInfoResponseDTO()
        val candidato2 = getCandidatoInfoResponseDTO()

        // Act
        val result = candidato1 == candidato2

        // Assert
        assertTrue(result)
    }

    @Test
    fun `equals returns false for objects with different properties`() {
        // Arrange
        val candidato1 = getCandidatoInfoResponseDTO()
        val candidato2 = getCandidatoInfoResponseDTO().copy(id = 2)

        // Act
        val result = candidato1 == candidato2

        // Assert
        assertFalse(result)
    }

    @Test
    fun `toString returns a non-empty string`() {
        // Arrange
        val candidato = getCandidatoInfoResponseDTO()

        // Act
        val result = candidato.toString()

        // Assert
        assertTrue(result.isNotEmpty())
    }

    private fun getCandidatoInfoResponseDTO(): CandidatoInfoReponseDTO {
        // Implementa lógica para crear una instancia de CandidatoInfoReponseDTO con valores de prueba.
        // Puedes usar mockk o simplemente crear una instancia manualmente con valores fijos.
        return CandidatoInfoReponseDTO(
            edad = 25,
            experiencia = emptyList(),
            habilidadesBlandas = emptyList(),
            habilidadesTecnicas = emptyList(),
            id = 1,
            id_pais = 1,
            idiomas = emptyList(),
            informacionAcademica = emptyList(),
            numero_telefono = "123456789",
            paisOrigen = Country(2, "Colombia"),
            usuario = Usuario(1,"12345678","email@test.com",2,"John Doe")
        )
    }
}