package proyectofinal.com.example.abc

import proyectofinal.com.example.abc.di.AppModule
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class AppModuleTest {

    @Test
    fun testProvideRetrofitClient() {
        // Crea una instancia del módulo
        val appModule = AppModule

        // Llama a la función para obtener una instancia de RemoteUsuario
        val remoteUsuario = appModule.provideRetrofitClient()

        // Asegúrate de que la instancia no sea nula
        assertNotNull(remoteUsuario)

        // Agrega más aserciones según sea necesario para verificar su comportamiento.
    }
}