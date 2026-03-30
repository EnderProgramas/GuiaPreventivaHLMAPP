package es.hospital_la_magdalena.guiapreventiva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AnexosFragment : Fragment() {
    private lateinit var rv: RecyclerView
    private lateinit var adp: AnexoAdapter
    private var listaCompleta: List<Anexo> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_anexos, container, false)
        rv = v.findViewById(R.id.lista_anexos)

        configurarDatos()

        adp = AnexoAdapter(listaCompleta)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adp

        return v
    }

    private fun configurarDatos() {
        listaCompleta = listOf(
            Anexo(
                titulo = "Anexo 1. Tabla para la definición del tipo de aislamiento por MMR y de especial vigilancia",
                imagen = R.drawable.anexo_1 // Renombra tu imagen 1 a anexo_1.jpg y ponla en drawable
            ),
            Anexo(
                titulo = "Anexo 2. Principales patologías susceptibles de aislamiento y definición de este",
                imagen = R.drawable.anexo_2 // Renombra tu imagen 2 a anexo_2.jpg y ponla en drawable
            ),
            Anexo(
                titulo = "Anexo 3. Tabla para la determinación de la metodología de higiene del paciente y del entorno por MRR",
                imagen = R.drawable.anexo_3 // Renombra tu imagen 3 a anexo_3.jpg y ponla en drawable
            ),
            Anexo(
                titulo = "Anexo 4. Tabla para la retirada del aislamiento y el estudio de portadores por MMR o de especial vigilancia",
                imagen = R.drawable.anexo_4 // Renombra tu imagen 4 a anexo_4.jpg y ponla en drawable
            )
        )
    }
}