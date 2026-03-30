package es.hospital_la_magdalena.guiapreventiva

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.github.chrisbanes.photoview.PhotoView

class VisorImagenDialog(private val imagenResId: Int) : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Fuerza el estilo a pantalla completa sin barra de título
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.visor_imagen, container, false)

        val photoView = view.findViewById<PhotoView>(R.id.imagen_ampliada)
        val btnCerrar = view.findViewById<ImageButton>(R.id.boton_cerrar)

        // Carga la imagen recibida en el componente de zoom
        photoView.setImageResource(imagenResId)

        // Cierra el diálogo al pulsar la X
        btnCerrar.setOnClickListener { dismiss() }

        return view
    }
}