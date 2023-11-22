package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.model.AcademicaDTO
import proyectofinal.com.example.abc.model.InformacionAcademicaIn
import org.junit.Assert.assertEquals
import org.junit.Test

class AcademicDTOTest {

    @Test
    fun `create AcademicaDTO and verify properties`() {
        // Crea una instancia de AcademicaDTO
        val informacionAcademicaIn = InformacionAcademicaIn(
            titulo = "Licenciatura",
            institucion = "Universidad de Ejemplo",
            fecha_fin = "2023-05-15",
            fecha_inicio = "2022-05-15",
            en_curso = false
        )

        val academicaDTO = AcademicaDTO(
            id_candidato = 1,
            informacionAcademica = informacionAcademicaIn
        )

        // Verifica las propiedades
        assertEquals(1, academicaDTO.id_candidato)
        assertEquals(informacionAcademicaIn, academicaDTO.informacionAcademica)
    }

    @Test
    fun `create AcademicaDTO with default values`() {
        // Crea una instancia de AcademicaDTO con valores predeterminados
        val academicaDTO = AcademicaDTO(0, InformacionAcademicaIn("", "", "","", false))

        // Verifica que las propiedades tengan los valores predeterminados
        assertEquals(0, academicaDTO.id_candidato)
        assertEquals(InformacionAcademicaIn("", "", "", "", false), academicaDTO.informacionAcademica)
    }
}