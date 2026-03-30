package es.hospital_la_magdalena.guiapreventiva

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DefinicionesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var buscador: EditText
    private lateinit var adaptador: DefinicionAdapter
    private var listaCompleta: List<Definicion> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_definiciones, container, false)

        recyclerView = view.findViewById(R.id.lista_definiciones)
        buscador = view.findViewById(R.id.buscador_definiciones)

        configurarDatos()
        configurarRecyclerView()
        configurarBuscador()

        return view
    }

    private fun configurarDatos() {
        // Datos extraídos textualmente del Punto 6 del documento PDF
        listaCompleta = listOf(
            Definicion("Aislamiento", "Conjunto de medidas técnicas dirigidas a interrumpir la cadena de transmisión desde una fuente, ya sea un paciente o su entorno, hacia un huésped susceptible, mediante la implementación de barreras estándar y adicionales."),
            Definicion("Precauciones estándar", "Primera línea de defensa de aplicación universal a todos los pacientes, con independencia de su diagnóstico, incluyendo la higiene de manos, el uso de EPI, la higiene respiratoria y el manejo seguro de materiales y residuos."),
            Definicion("Equipo de Protección Individual (EPI)", "Barreras personales como guantes, batas, mascarillas quirúrgicas o FFP2/FFP3, cuya eficacia depende de una secuencia segura de colocación y retirada."),
            Definicion("Precauciones adicionales", "Medidas basadas en la transmisión que se añaden a las estándar cuando el mecanismo predominante de diseminación del patógeno exige requisitos específicos de ubicación y control ambiental."),
            Definicion("Colonización", "Presencia y multiplicación de un microorganismo en un huésped sin que existan signos o síntomas clínicos de enfermedad."),
            Definicion("Infección", "Respuesta patológica del organismo ante la invasión microbiana."),
            Definicion("Cocos", "Bacterias con morfología esférica."),
            Definicion("Bacilos", "Bacterias con morfología cilíndrica o de bastón."),
            Definicion("Microorganismos Grampositivos", "Bacterias con pared celular gruesa compuesta predominantemente por peptidoglicano, la cual retiene el colorante cristal violeta."),
            Definicion("Microorganismos Gramnegativos", "Bacterias con capa de peptidoglicano fina y membrana externa rica en lipopolisacáridos, que requiere el uso de safranina para su visualización y confiere resistencia intrínseca."),
            Definicion("SARM / MRSA", "Staphylococcus aureus resistente a la meticilina. Coco Grampositivo patógeno de alta prevalencia."),
            Definicion("ERV / VRE", "Enterococos resistentes a la vancomicina (E. faecalis y E. faecium). Cocos Grampositivos de la microbiota intestinal con capacidad para sobrevivir en superficies ambientales."),
            Definicion("Enterobacterales", "Bacilos Gramnegativos (ej. E. coli, Klebsiella spp.) productores frecuentes de BLEE y carbapenemasas, cuyo hábitat principal es el tracto digestivo."),
            Definicion("Bacilos Gramnegativos No Fermentadores", "Microorganismos (ej. Pseudomonas, Acinetobacter) con resistencia intrínseca elevada y capacidad extraordinaria para persistir en entornos húmedos y superficies inanimadas."),
            Definicion("BAAR", "Bacilo ácido-alcohol resistente (ej. Mycobacterium tuberculosis). Requiere tinción de Ziehl-Neelsen debido a su pared rica en ácidos micólicos y aislamiento aéreo estricto."),
            Definicion("Clostridioides difficile", "Bacilo Gram-positivo anaerobio estricto y formador de esporas, con resistencia ambiental extrema a desinfectantes habituales."),
            Definicion("Candidozyma auris", "Hongo multirresistente (levadura) con capacidad de colonización persistente y transmisión horizontal similar a las bacterias multirresistentes."),
            Definicion("IRP", "Partículas respiratorias infecciosas. Emitidas por virus respiratorios como SARS-CoV-2 o Influenza, exigen control riguroso."),
            Definicion("Microorganismos Multirresistentes (MMR)", "Patógenos que presentan resistencia a múltiples clases de antimicrobianos, limitando las opciones terapéuticas."),
            Definicion("MR (Microorganismo Resistente)", "Presenta falta de sensibilidad adquirida a, al menos, un agente en tres o más familias de antimicrobianos habitualmente eficaces."),
            Definicion("XDR (Patógenos extensamente resistentes)", "Microorganismos que mantienen sensibilidad únicamente a dos o menos categorías de antimicrobianos."),
            Definicion("PDR (Pandrogresistentes)", "Microorganismos resistentes a todos los agentes antimicrobianos disponibles."),
            Definicion("Clasificación de Ambler", "Clasificación molecular que agrupa las betalactamasas según su estructura y mecanismo catalítico, determinando el espectro de hidrólisis y opciones terapéuticas."),
            Definicion("Enzimas de Clase A", "Serina-betalactamasas que incluyen las betalactamasas de espectro extendido (BLEE) y algunas carbapenemasas como KPC."),
            Definicion("Enzimas de Clase B (Metalo-betalactamasas)", "Enzimas dependientes de zinc (ej. VIM, IMP, NDM) capaces de hidrolizar todos los betalactámicos, incluidos carbapenémicos, excepto monobactámicos."),
            Definicion("Enzimas de Clase C (AmpC)", "Serina-betalactamasas, a menudo cromosómicas, que confieren resistencia a penicilinas y cefalosporinas de 1ª a 3ª generación; no son inhibidas por ácido clavulánico."),
            Definicion("Enzimas de Clase D", "Serina-betalactamasas (ej. OXA-48) con actividad hidrolítica selectiva frente a penicilinas y carbapenémicos."),
            Definicion("Transmisión por contacto", "Ruta de transmisión directa (piel a piel) o indirecta (a través de fómites o manos del personal sanitario que actúan como vector)."),
            Definicion("Transmisión por gotas", "Diseminación mediante partículas respiratorias de corto alcance emitidas al hablar o toser que impactan en mucosas cercanas."),
            Definicion("Transmisión aérea (aerosoles)", "Diseminación mediante partículas respiratorias infecciosas (IRP) que permanecen suspendidas en el aire y pueden ser inhaladas a mayores distancias.")
        )
    }

    private fun configurarRecyclerView() {
        adaptador = DefinicionAdapter(listaCompleta)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adaptador
    }

    private fun configurarBuscador() {
        buscador.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtrarResultados(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filtrarResultados(textoBusqueda: String) {
        val listaFiltrada = listaCompleta.filter {
            it.termino.contains(textoBusqueda, ignoreCase = true) ||
                    it.descripcion.contains(textoBusqueda, ignoreCase = true)
        }
        adaptador.actualizarLista(listaFiltrada)
    }
}