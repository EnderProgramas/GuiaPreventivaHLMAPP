package es.hospital_la_magdalena.guiapreventiva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DefinicionAdapter(private var listaDefiniciones: List<Definicion>) :
    RecyclerView.Adapter<DefinicionAdapter.DefinicionViewHolder>() {

    class DefinicionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textoTermino: TextView = itemView.findViewById(R.id.texto_termino)
        val textoDescripcion: TextView = itemView.findViewById(R.id.texto_descripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinicionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_definicion, parent, false)
        return DefinicionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DefinicionViewHolder, position: Int) {
        val definicion = listaDefiniciones[position]
        holder.textoTermino.text = definicion.termino
        holder.textoDescripcion.text = definicion.descripcion
    }

    override fun getItemCount(): Int {
        return listaDefiniciones.size
    }

    fun actualizarLista(nuevaLista: List<Definicion>) {
        listaDefiniciones = nuevaLista
        notifyDataSetChanged() // Fuerza la recarga visual de la lista
    }
}