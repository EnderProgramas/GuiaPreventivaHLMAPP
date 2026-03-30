package es.hospital_la_magdalena.guiapreventiva

data class Infeccion(
    val microorganismo: String,
    val introduccion: String,
    val precaucionesEstandar: String,
    val precaucionesAmpliadas: String,
    val higienePaciente: String,
    val diagnosticoTratamiento: String,
    val criteriosRetirada: String,
    val imagenAlgoritmo: Int? = null,
    val imagenAlgoritmo2: Int? = null, // NUEVO CAMPO PARA LA SEGUNDA IMAGEN

    var expandidoPrincipal: Boolean = false,
    var expIntro: Boolean = false,
    var expEstandar: Boolean = false,
    var expAmpliadas: Boolean = false,
    var expHigiene: Boolean = false,
    var expDiag: Boolean = false,
    var expRetirada: Boolean = false
)