package es.hospital_la_magdalena.guiapreventiva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnexoAdapter(private val listaAnexos: List<Anexo>) :
    RecyclerView.Adapter<AnexoAdapter.AnexoViewHolder>() {

    class AnexoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cabecera = v.findViewById<LinearLayout>(R.id.cabecera_anexo)
        val txtTitulo = v.findViewById<TextView>(R.id.texto_titulo_anexo)
        val icono = v.findViewById<TextView>(R.id.icono_anexo)
        val imgAnexo = v.findViewById<ImageView>(R.id.img_anexo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnexoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_anexo, parent, false)
        return AnexoViewHolder(v)
    }

    override fun onBindViewHolder(holder: AnexoViewHolder, position: Int) {
        val anexo = listaAnexos[position]
        holder.txtTitulo.text = anexo.titulo
        holder.imgAnexo.setImageResource(anexo.imagen)

        // Control de visibilidad
        holder.imgAnexo.visibility = if (anexo.expandido) View.VISIBLE else View.GONE
        holder.icono.text = if (anexo.expandido) "▲" else "▼"

        // Expansión/Contracción
        holder.cabecera.setOnClickListener {
            anexo.expandido = !anexo.expandido
            notifyItemChanged(position)
        }

        // Visor a pantalla completa
        holder.imgAnexo.setOnClickListener {
            val context = holder.itemView.context
            if (context is androidx.appcompat.app.AppCompatActivity) {
                val dialog = VisorImagenDialog(anexo.imagen)
                dialog.show(context.supportFragmentManager, "VisorImagenAnexo")
            }
        }
    }

    override fun getItemCount(): Int = listaAnexos.size
}