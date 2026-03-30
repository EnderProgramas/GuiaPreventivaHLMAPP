package es.hospital_la_magdalena.guiapreventiva

data class Anexo(
    val titulo: String,
    val imagen: Int,
    var expandido: Boolean = false
)