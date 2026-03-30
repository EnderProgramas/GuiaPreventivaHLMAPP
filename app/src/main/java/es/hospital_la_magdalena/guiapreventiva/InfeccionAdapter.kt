package es.hospital_la_magdalena.guiapreventiva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InfeccionAdapter(private var listaInfecciones: List<Infeccion>) :
    RecyclerView.Adapter<InfeccionAdapter.InfeccionViewHolder>() {

    class InfeccionViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val cabPrincipal = v.findViewById<LinearLayout>(R.id.cabecera_principal)
        val contSub = v.findViewById<LinearLayout>(R.id.contenedor_subsecciones)
        val txtMicro = v.findViewById<TextView>(R.id.texto_microorganismo)
        val icoPri = v.findViewById<TextView>(R.id.icono_principal)

        val cabIntro = v.findViewById<LinearLayout>(R.id.cabecera_intro)
        val icoIntro = v.findViewById<TextView>(R.id.icono_intro)
        val cueIntro = v.findViewById<TextView>(R.id.cuerpo_intro)

        val cabEst = v.findViewById<LinearLayout>(R.id.cabecera_estandar)
        val icoEst = v.findViewById<TextView>(R.id.icono_estandar)
        val cueEst = v.findViewById<TextView>(R.id.cuerpo_estandar)

        val cabAmp = v.findViewById<LinearLayout>(R.id.cabecera_ampliadas)
        val icoAmp = v.findViewById<TextView>(R.id.icono_ampliadas)
        val cueAmp = v.findViewById<TextView>(R.id.cuerpo_ampliadas)

        val cabHig = v.findViewById<LinearLayout>(R.id.cabecera_higiene)
        val icoHig = v.findViewById<TextView>(R.id.icono_higiene)
        val cueHig = v.findViewById<TextView>(R.id.cuerpo_higiene)

        val cabDiag = v.findViewById<LinearLayout>(R.id.cabecera_diag)
        val icoDiag = v.findViewById<TextView>(R.id.icono_diag)
        val cueDiag = v.findViewById<TextView>(R.id.cuerpo_diag)

        val cabRet = v.findViewById<LinearLayout>(R.id.cabecera_retirada)
        val icoRet = v.findViewById<TextView>(R.id.icono_retirada)
        val contRet = v.findViewById<LinearLayout>(R.id.contenedor_retirada_cuerpo)
        val txtRet = v.findViewById<TextView>(R.id.texto_retirada_contenido)
        val imgAlgo = v.findViewById<ImageView>(R.id.img_algoritmo_final)
        // NUEVA REFERENCIA PARA LA SEGUNDA IMAGEN
        val imgAlgo2 = v.findViewById<ImageView>(R.id.img_algoritmo_final_2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfeccionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_infeccion, parent, false)
        return InfeccionViewHolder(v)
    }

    override fun onBindViewHolder(holder: InfeccionViewHolder, position: Int) {
        val i = listaInfecciones[position]
        holder.txtMicro.text = i.microorganismo
        holder.cueIntro.text = i.introduccion
        holder.cueEst.text = i.precaucionesEstandar
        holder.cueAmp.text = i.precaucionesAmpliadas
        holder.cueHig.text = i.higienePaciente
        holder.cueDiag.text = i.diagnosticoTratamiento
        holder.txtRet.text = i.criteriosRetirada

        // Configuración de la Primera Imagen
        if (i.imagenAlgoritmo != null) {
            holder.imgAlgo.setImageResource(i.imagenAlgoritmo)
            holder.imgAlgo.visibility = View.VISIBLE

            // Habilita el clic para abrir el visor a pantalla completa
            holder.imgAlgo.setOnClickListener {
                val context = holder.itemView.context
                if (context is androidx.appcompat.app.AppCompatActivity) {
                    val dialog = VisorImagenDialog(i.imagenAlgoritmo)
                    dialog.show(context.supportFragmentManager, "VisorImagen")
                }
            }
        } else {
            holder.imgAlgo.visibility = View.GONE
            holder.imgAlgo.setOnClickListener(null)
        }

        // Configuración de la Segunda Imagen
        if (i.imagenAlgoritmo2 != null) {
            holder.imgAlgo2.setImageResource(i.imagenAlgoritmo2)
            holder.imgAlgo2.visibility = View.VISIBLE

            // Habilita el clic para abrir el visor a pantalla completa
            holder.imgAlgo2.setOnClickListener {
                val context = holder.itemView.context
                if (context is androidx.appcompat.app.AppCompatActivity) {
                    val dialog = VisorImagenDialog(i.imagenAlgoritmo2)
                    dialog.show(context.supportFragmentManager, "VisorImagen2")
                }
            }
        } else {
            holder.imgAlgo2.visibility = View.GONE
            holder.imgAlgo2.setOnClickListener(null)
        }

        holder.contSub.visibility = if (i.expandidoPrincipal) View.VISIBLE else View.GONE
        holder.icoPri.text = if (i.expandidoPrincipal) "▲" else "▼"
        holder.cueIntro.visibility = if (i.expIntro) View.VISIBLE else View.GONE
        holder.icoIntro.text = if (i.expIntro) "-" else "+"
        holder.cueEst.visibility = if (i.expEstandar) View.VISIBLE else View.GONE
        holder.icoEst.text = if (i.expEstandar) "-" else "+"
        holder.cueAmp.visibility = if (i.expAmpliadas) View.VISIBLE else View.GONE
        holder.icoAmp.text = if (i.expAmpliadas) "-" else "+"
        holder.cueHig.visibility = if (i.expHigiene) View.VISIBLE else View.GONE
        holder.icoHig.text = if (i.expHigiene) "-" else "+"
        holder.cueDiag.visibility = if (i.expDiag) View.VISIBLE else View.GONE
        holder.icoDiag.text = if (i.expDiag) "-" else "+"
        holder.contRet.visibility = if (i.expRetirada) View.VISIBLE else View.GONE
        holder.icoRet.text = if (i.expRetirada) "-" else "+"

        holder.cabPrincipal.setOnClickListener {
            i.expandidoPrincipal = !i.expandidoPrincipal
            notifyItemChanged(position)
        }
        holder.cabIntro.setOnClickListener {
            i.expIntro = !i.expIntro
            notifyItemChanged(position)
        }
        holder.cabEst.setOnClickListener {
            i.expEstandar = !i.expEstandar
            notifyItemChanged(position)
        }
        holder.cabAmp.setOnClickListener {
            i.expAmpliadas = !i.expAmpliadas
            notifyItemChanged(position)
        }
        holder.cabHig.setOnClickListener {
            i.expHigiene = !i.expHigiene
            notifyItemChanged(position)
        }
        holder.cabDiag.setOnClickListener {
            i.expDiag = !i.expDiag
            notifyItemChanged(position)
        }
        holder.cabRet.setOnClickListener {
            i.expRetirada = !i.expRetirada
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = listaInfecciones.size

    fun actualizarLista(l: List<Infeccion>) {
        listaInfecciones = l
        notifyDataSetChanged()
    }
}