package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.model.CandidatoInfoDTO
import proyectofinal.com.example.abc.model.Experiencia
import proyectofinal.com.example.abc.model.InformacionAcademica
import org.junit.Assert.assertEquals
import org.junit.Test

class CandidatoInfoDTOTest {

    @Test
    fun testCandidatoInfoDTO() {
        // Crea una instancia de CandidatoInfoDTO
        val candidatoInfoDTO = CandidatoInfoDTO(
            edad = 30,
            email = "test@example.com",
            experiencia = listOf(
                Experiencia(0,"prueba","2020","2015",1,"Company 1",),
                Experiencia(0,"prueba","2022","2020",1,"Company 2",),
            ),
            habilidadesBlandas = listOf(1, 2, 3),
            habilidadesTecnicas = listOf(4, 5),
            id_pais = 1,
            idiomas = listOf(6, 7),
            informacionAcademica = listOf(
                InformacionAcademica(titulo ="Degree 1", institucion ="University 1"),
                InformacionAcademica(titulo ="Degree 2", institucion ="University 2"),
            ),
            numero_telefono = "123456789"
        )

        // Aserciones para verificar los valores
        assertEquals(30, candidatoInfoDTO.edad)
        assertEquals("test@example.com", candidatoInfoDTO.email)
        assertEquals(2, candidatoInfoDTO.experiencia.size)
        assertEquals(3, candidatoInfoDTO.habilidadesBlandas.size)
        assertEquals(2, candidatoInfoDTO.habilidadesTecnicas.size)
        assertEquals(1, candidatoInfoDTO.id_pais)
        assertEquals(2, candidatoInfoDTO.informacionAcademica.size)
        assertEquals("123456789", candidatoInfoDTO.numero_telefono)
    }
}