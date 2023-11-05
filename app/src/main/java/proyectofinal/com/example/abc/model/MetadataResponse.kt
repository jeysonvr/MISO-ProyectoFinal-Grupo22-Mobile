package proyectofinal.com.example.abc.model


data class MetadataResponse(
    val habilidadesBlandas: List<SoftSkills>,
    val habilidadesTecnicas: List<Skills>,
    val idiomas: List<Language>,
    val paises: List<Country>,
    val roles: List<Role>
)