package es.hospital_la_magdalena.guiapreventiva

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var contenidoHome: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val logoPreventiva = findViewById<ImageView>(R.id.logo_preventiva)
        val iconoConsulta = findViewById<ImageView>(R.id.icono_consulta)
        val iconoActualizaciones = findViewById<ImageView>(R.id.icono_actualizaciones)
        contenidoHome = findViewById(R.id.contenido_home)

        // Configuración del listener para los clics en el menú inferior
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_definiciones -> {
                    cargarFragmento(DefinicionesFragment())
                    true
                }
                R.id.nav_infecciones -> {
                    cargarFragmento(InfeccionesFragment())
                    true
                }
                R.id.nav_anexos -> {
                    cargarFragmento(AnexosFragment())
                    true
                }
                else -> false
            }
        }

        // Evento de pulsación para el retorno al estado inicial
        logoPreventiva.setOnClickListener {
            resetearAInicio(bottomNavigation)
        }

        // Evento de pulsación para enviar correo de consulta
        iconoConsulta.setOnClickListener {
            val intent = android.content.Intent(android.content.Intent.ACTION_SENDTO).apply {
                data = android.net.Uri.parse("mailto:")
                putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf("guillamon_caresc@gva.es"))
                putExtra(android.content.Intent.EXTRA_SUBJECT, "CONSULTA GUÍA MMRR")
            }
            try {
                startActivity(intent)
            } catch (e: Exception) {
                android.widget.Toast.makeText(this, "No hay aplicación de correo instalada.", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        // Evento de pulsación para el registro de actualizaciones
        iconoActualizaciones.setOnClickListener {
            android.app.AlertDialog.Builder(this)
                .setTitle("Registro de Actualizaciones")
                .setMessage("Versión 1.0\n- Implementación inicial de la Guía de Precauciones y Aislamientos.\n- Integración de 11 microorganismos multirresistentes.\n- Incorporación de algoritmos de retirada y anexos técnicos.")
                .setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // Gestión técnica del botón físico/gestual "Atrás" de Android
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragmentoActivo = supportFragmentManager.findFragmentById(R.id.contenedor_fragmentos)
                if (contenidoHome.visibility == View.GONE) {
                    // Si el inicio no está visible (estamos en un fragmento), volvemos al inicio
                    resetearAInicio(bottomNavigation)
                } else {
                    // Si ya estamos en el inicio, cerramos la aplicación
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                    isEnabled = true
                }
            }
        })
    }

    // Función para limpiar el contenedor de fragmentos y resetear el menú inferior
    private fun resetearAInicio(bottomNavigation: BottomNavigationView) {
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        
        // Mostrar contenido principal
        contenidoHome.visibility = View.VISIBLE
        
        bottomNavigation.menu.setGroupCheckable(0, true, false)
        for (i in 0 until bottomNavigation.menu.size()) {
            bottomNavigation.menu.getItem(i).isChecked = false
        }
        bottomNavigation.menu.setGroupCheckable(0, true, true)
    }

    // Función técnica para ejecutar la transición y reemplazo de la pantalla
    private fun cargarFragmento(fragment: Fragment) {
        // Ocultar contenido principal al cargar cualquier fragmento
        contenidoHome.visibility = View.GONE
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_fragmentos, fragment)
            .commit()
    }
}
