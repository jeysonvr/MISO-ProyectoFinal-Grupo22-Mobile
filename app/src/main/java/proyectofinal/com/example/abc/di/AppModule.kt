package proyectofinal.com.example.abc.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import proyectofinal.com.example.abc.repository.RemoteUsuario
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): RemoteUsuario {
        return RemoteUsuario()
    }
}