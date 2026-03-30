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

class InfeccionesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var buscador: EditText
    private lateinit var adaptador: InfeccionAdapter
    private var listaCompleta: List<Infeccion> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_infecciones, container, false)
        recyclerView = view.findViewById(R.id.lista_infecciones)
        buscador = view.findViewById(R.id.buscador_infecciones)

        configurarDatos()
        configurarRecyclerView()
        configurarBuscador()

        return view
    }

    private fun configurarDatos() {
        listaCompleta = listOf(
            Infeccion(
                microorganismo = "Clostridioides Difficile",

                introduccion = """
                    
El Clostridioides difficile se define como una bacteria bacilo anaerobia con capacidad patogénica para producir infecciones graves en el tracto gastrointestinal. Los principales reservorios son los pacientes infectados o colonizados y el entorno inanimado. Inicia un proceso de endoesporulación liberando esporas al ambiente a través de deposiciones, las cuales sobreviven a métodos de limpieza habituales y muestran resistencia a geles con base alcohólica. Es la primera causa de diarrea nosocomial en países desarrollados.

SIGNOS Y SÍNTOMAS

Diarrea (≥ 3 deposiciones líquidas/24h, tipos 5 y 7 de Bristol) sin administración de laxantes. Consistencia acuosa, verdosa y olor fétido. En cuadros graves: fiebre, dolor abdominal, escalofríos, taquicardia o megacolon tóxico.

CLASIFICACIÓN

- Caso Nuevo: Primera muestra (+) sin antecedentes, o síntomas iniciados >8 semanas tras resolución de episodio previo.

- Caso Prevalente/Recurrente: Criterios clínicos/microbiológicos entre 2 y 8 semanas post-episodio.

- Caso No Clasificable: Antecedente en otro centro bajo circunstancias temporales específicas.

Brote: Agregación de ≥3 casos en un mes tras 48h de ingreso, con vínculo epidemiológico.

CADENA EPIDEMIOLÓGICA

Aprox. 50% de pacientes clínicamente superados excretan esporas asintomáticamente 1-4 semanas post-tratamiento. Transmisión predominante por contacto indirecto (manos, equipo, superficies). Riesgo incrementado con antimicrobianos, >65 años, estancias prolongadas o IBP.
                """.trimIndent(),

                precaucionesEstandar = """
                    
Aplicación universal. En C. difficile, la higiene previa al contacto puede realizarse con solución hidroalcohólica. Sin embargo, la higiene de manos TRAS el contacto con el paciente, fluidos o antes de abandonar la habitación debe ejecutarse estrictamente mediante lavado mecánico con AGUA Y JABÓN para asegurar la eliminación física de las esporas.
                """.trimIndent(),

                precaucionesAmpliadas = """

PROCEDIMIENTOS / UBICACIÓN

Registro obligatorio de aislamiento estricto en Orion Clinic. Habitación individual con presión neutra y puerta cerrada (excepcionalmente, separación física de 1,5m o cohorte).

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: ESTRICTO)

EPI: Guantes, bata manga larga desechable y mascarilla quirúrgica antes de entrar. Desecho en interior como residuo Grupo III.

CONSIDERACIONES ESPECIALES

- Muestras: Transporte en envase rígido desechable preparado previamente. Relevo en puerta (limpio-sucio) con bolsa zip y doble profesional.

- Exitus: Doble celador and doble barrera. Desinfección de camilla de acero inoxidable con toallitas cloradas (sin aclarar).

- Comida: Uso exclusivo de material desechable, eliminado en Grupo III en interior de habitación.

- Traslados/Grúa: Restringido a lo imprescindible (preaviso a Rx). Modelo "doble celador". Doble desinfección de grúa (si hay suciedad: detergente+biocida; si no: toallitas cloradas). Textiles a lavandería ≥65°C o inmersión en lejía 1/10 (10 min).

ENTORNO Y LIMPIEZA

Material de uso exclusivo. Superficies no porosas limpias: toallitas cloradas. Superficies porosas: inmersión lejía 1/10. Limpieza diaria y terminal con Taski Sprint H-100 al 5% con fricción agresiva. NO UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS.
                """.trimIndent(),

                higienePaciente = """
                    
La atención diaria requiere un protocolo de higiene corporal sistemático dirigido a reducir la carga de endoesporas en la superficie cutánea. 

- Método: Higiene completa cada 24 horas empleando lavado con AGUA Y JABÓN para arrastrar las endoesporas. 
- Educación: Labor educativa continua al paciente y cuidadores sobre medidas de barrera.

                """.trimIndent(),

                diagnosticoTratamiento = """
                    
DIAGNÓSTICO:

1. Test de detección antígeno GDH (cribado).

2. Si GDH positivo: Inmunoensayo de toxinas A y B.

3. Si toxinas negativo a pesar de GDH positivo: Obligatorio prueba PCR para confirmar cepas toxigénicas.

TRATAMIENTO:

Suspensión inmediata de antibióticos causantes (clindamicina, fluoroquinolonas, cefalosporinas 2ª+, carbapenémicos) e IBP innecesarios.

- Primer episodio (leve/moderado): Vancomicina oral (125mg/6h) x 10 días. (Metronidazol oral solo alternativa en casos leves inmunocompetentes).

- Recurrencias: Primera (Fidaxomicina). Segundas (Vancomicina descendente, Fidaxomicina o trasplante microbiota).
                """.trimIndent(),

                criteriosRetirada = """
                    
El aislamiento podrá retirarse tras inicio de la pauta antibiótica dirigida, si el paciente se mantiene estable y SIN presencia de deposiciones diarreicas durante un mínimo de 72 horas.
NO se recomienda la repetición de las pruebas diagnósticas (PCR/Toxinas) una vez desaparecida la sintomatología. La decisión debe constar explícitamente en Orion Clinic.
                """.trimIndent(),

                imagenAlgoritmo = R.drawable.algoritmo_c_difficile
            ),

            // --- SEGUNDO MICROORGANISMO: Candida auris ---
            Infeccion(
                microorganismo = "Candidozyma Auris",
                introduccion = """
La Candidozyma Auris (C. auris) se identifica como un patógeno fúngico emergente de carácter levaduriforme, descrito inicialmente en 2009. Posee una elevada capacidad de persistencia ambiental y un perfil de multirresistencia a los antifúngicos convencionales. La mortalidad asociada a fungemias por este agente oscila entre el 30% y el 72%. Puede transmitirse entre pacientes con tiempos de contacto inferiores a cuatro horas.

SIGNOS Y SÍNTOMAS

Abarca desde colonización asintomática hasta infección invasiva grave (sepsis candidiásica). Los síntomas suelen ser inespecíficos, manifestándose frecuentemente como cuadros febriles que no responden a la terapia antibacteriana de amplio espectro.

CLASIFICACIÓN

- Caso Nuevo: Cultivo positivo confirmado sin antecedentes, o transcurridos al menos 6 meses desde el último positivo con evidencia de negativización.

- Caso Prevalente/Recurrente: Nuevo aislamiento positivo dentro del intervalo de 6 meses posterior a un episodio previo (implica reactivación inmediata del aislamiento estricto).

- Caso No Clasificable: Antecedentes en otro centro en las últimas 2 semanas (como caso nuevo) o entre 2 semanas y 6 meses (como prevalente).

Brote: Dos o más casos confirmados con vínculo temporoespacial.

CADENA EPIDEMIOLÓGICA

- Reservorio principal: Superficie cutánea humana (axilar, inguinal, anal, pliegues cutáneos), persistiendo durante meses en folículos pilosos.

- Reservorio secundario: Entorno hospitalario (biofilms secos en superficies viables durante ≥ 21 días, tolerantes a amonios cuaternarios).

- Transmisión: Contacto directo o indirecto (fómites).

- Factores de riesgo: Inmunosupresión, dispositivos invasivos (CVC), nutrición parenteral, cirugías recientes, estancias prolongadas en críticos y exposición previa a antifúngicos/antimicrobianos de amplio espectro.

                """.trimIndent(),

                precaucionesEstandar = """
Aplicación rigurosa de precauciones estándar con eje en la higiene de manos mediante SOLUCIÓN HIDROALCOHÓLICA, salvo suciedad visible (donde se requiere lavado con agua y jabón previo). Incluye higiene respiratoria y gestión segura de objetos punzantes y lencería para evitar diseminación de partículas fúngicas.
                """.trimIndent(),

                precaucionesAmpliadas = """
                    
PROCEDIMIENTOS / UBICACIÓN

Registro obligatorio de aislamiento estricto en Orion Clinic. Habitación individual de presión neutra con puerta permanentemente cerrada. (Cohorte solo agrupando pacientes con el mismo patógeno y bajo supervisión).

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: ESTRICTO)

EPI: Guantes, bata de manga larga desechable y mascarilla quirúrgica antes de entrar. Segregación como residuo Grupo III dentro de la habitación.

CONSIDERACIONES ESPECIALES

- Muestras: Transporte en envase rígido desechable. Relevo limpio-sucio en puerta con bolsa zip.

- Exitus: Circuito de mínima manipulación (celador interior EPI completo + asistente exterior sin EPI). Desinfección de camilla de acero con toallitas cloradas sin aclarado.

- Comida: Exclusivo con material desechable. Desecho en Grupo III en interior.

- Traslados/Grúa: Restringido a lo indispensable. Modelo "doble celador". Preaviso y desinfección terminal de la sala de destino con levaduricidas.

ENTORNO Y LIMPIEZA

Priorizar material desechable. Uso exclusivo de fonendoscopio/termómetro.

- Superficies no porosas (sin suciedad): toallitas cloradas, sin aclarar.

- Superficies no porosas (suciedad visible): espray detergente + biocida (aclarar y secar).

- Superficies porosas: inmersionar en lejía 1/10 preparado en el momento. Mantener el objeto 10mn completamente inmersionado en la solución, sacar y secar en la medida de lo posible y luego dejar secar al aire.  

- Limpieza diaria/terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). PROHIBIDO EL USO DE AMONIOS CUATERNARIOS.
                """.trimIndent(),

                higienePaciente = """
HIGIENE DEL PACIENTE

La atención requiere una higiene personal diaria rigurosa para mitigar la persistencia en piel y reducir el vertido ambiental.

- Piel SIN suciedad visible: Friccionado con toallitas de clorhexidina al 2%. NO SECAR al paciente después.

- Piel CON suciedad visible: Lavado previo con agua y jabón, SECADO COMPLETO, y posterior friccionado con toallitas de clorhexidina al 2% (no secar).

- Técnica: De zona más limpia a más sucia. Mínimo una toallita por extremidad/punto de carga. El personal debe usar bata impermeable para evitar contaminación de uniformes por salpicaduras.

- Para la recogida de muestras de portadores, es suficiente con recogerlas antes de la higiene diaria, es decir, dejando al menos 24h entre la última limpieza y la recogida.
                """.trimIndent(),

                diagnosticoTratamiento = """
DIAGNÓSTICO

1. Sospecha: Aislamiento de especies similares (C. haemulonii, C. famata) o CMI elevada a fluconazol (> 64 mg/L).

2. Confirmación obligatoria: Espectrometría de masas (MALDI-TOF) o secuenciación de ADN.

TRATAMIENTO

Coordinado por el equipo PROA según sensibilidad.

- 1ª Elección (empírico): Equinocandinas (anidulafungina, caspofungina o micafungina).

- Gravedad extrema: Combinación con Anfotericina B liposomial (requiere coordinación con Farmacia para preparación en Cabina de Flujo Laminar).

- Azoles (fluconazol, voriconazol): Supeditado a confirmación microbiológica (el 90% presenta resistencia intrínseca a fluconazol).
                """.trimIndent(),

                criteriosRetirada = """
La resolución clínica de una infección invasiva NO equivale a la eliminación del estado de portador.

- Norma general: Las precauciones se mantienen hasta el ALTA HOSPITALARIA.

- Excepción: Retirada anticipada solo si se obtienen tres muestras de vigilancia consecutivas negativas (frotis axilar y rectal). Aún así, se mantendrán controles semanales hasta el alta definitiva. Se valorará cada caso de manera individual. 

- Reingresos: Aislamiento preventivo automático salvo evidencia de negativización o paso de 6 meses.

- Fin de brote: 12 semanas consecutivas sin nuevos casos desde el alta del último paciente.
                """.trimIndent(),

                imagenAlgoritmo = R.drawable.algoritmo_c_auris
            ),
            // --- 3. Stenotrophomonas maltophilia ---
            Infeccion(
                microorganismo = "Stenotrophomonas Maltophilia",
                introduccion = """
La Stenotrophomonas maltophilia es un bacilo gramnegativo no fermentador, aerobio estricto y ubicuo en reservorios acuáticos ambientales. Actúa como patógeno oportunista de alta morbilidad, afectando a pacientes con estancias prolongadas, ventilación mecánica, inmunosupresión profunda o expuestos a antibióticos de amplio espectro. Presenta elevada resistencia intrínseca (metalobetalactamasas L1 y cefalosporinasas L2).

SIGNOS Y SÍNTOMAS

Subordinados al sitio anatómico y grado de inmunodepresión:

- Tracto respiratorio: Neumonía (fiebre, secreciones purulentas, desaturación, infiltrados).

- Bacteriemia: Fiebre de origen desconocido, escalofríos, taquicardia, posible shock séptico (vinculado a catéteres).

- Tracto urinario: Disuria, polaquiuria, fiebre (en portadores de sondaje prolongado).

- Piel/tejidos: Raro, pero cursa con celulitis profunda o necrosis focal (en puntos de inserción intravasculares).

CLASIFICACIÓN

- Caso Nuevo: Primer cultivo positivo o aislamiento tras ≥ 6 meses de negativización documentada.

- Caso Prevalente/Recurrente: Aislamiento en el intervalo de 6 meses post-episodio.

- Caso No Clasificable: Antecedentes en otro centro (últimas 2 semanas o hasta 6 meses previos).

Brote: Confirmación espacial y temporal de ≥ 3 casos en 30 días en una misma unidad.

CADENA EPIDEMIOLÓGICA

- Reservorio principal: Agua y entornos húmedos (grifería, sifones, duchas, hielo) y dispositivos médicos mal desinfectados (nebulizadores, tubuladuras).

- Transmisión: Contacto directo (manos colonizadas del personal) e indirecto (fómites y equipos).

- Factores de riesgo: UCI, ventilación mecánica prolongada, neoplasias hematológicas, trasplantes, portadores de dispositivos invasivos crónicos y presión selectiva por carbapenémicos.
                """.trimIndent(),

                precaucionesEstandar = """
Aplicación rigurosa de precauciones estándar con eje en la higiene de manos mediante SOLUCIONES HIDROALCOHÓLICAS (salvo suciedad visible, que requiere lavado de arrastre con agua y jabón antiséptico). Incluye manejo seguro de objetos cortopunzantes y gestión protocolizada de residuos y ropa hospitalaria minimizando la agitación.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

La Dirección General de Salud Pública recomienda precauciones ampliadas de CONTACTO para cualquier aislado de este microorganismo, independientemente de su resistencia.
Registro en Orion Clinic. Ubicación prioritaria en habitación individual con aseo exclusivo. Si no es posible, agrupamiento en cohortes espaciales asegurando la ausencia de co-infecciones con otros MMR divergentes.

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: CONTACTO)

EPI: Bata de aislamiento no estéril de manga larga y guantes de nitrilo/látex. Colocación en el exterior de la habitación. 

Retirada: Invariablemente en el INTERIOR de la habitación, próximo a la salida, desechando en Grupo III. Higiene de manos exhaustiva y obligatoria antes de abandonar la estancia y inmediatamente tras salir.

ENTORNO Y LIMPIEZA

Protocolo agresivo debido a su alta viabilidad ambiental. Material clínico de uso exclusivo siempre que sea viable.

- Reutilización superficies no porosas (sin suciedad): toallitas cloradas sin aclarar.

- Superficies porosas: inmersionar en lejía 1/10 preparado en el momento. Mantener el objeto 10mn completamente inmersionado en la solución, sacar y secar en la medida de lo posible y luego dejar secar al aire.  

- Limpieza diaria/terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS en limpieza terminal.

TRASLADOS Y VISITAS

Restringidos a procedimientos clínicamente inaplazables. Preaviso a destino. El paciente debe portar EPI (guantes, bata, mascarilla si secreciones) y ropa limpia cubriendo vías/estomas. Personal acompañante: sin EPI en tránsito, solo en manipulación directa. Visitas con aforo limitado y educación rigurosa sobre higiene de manos y prohibición de interactuar con el entorno del paciente.
                """.trimIndent(),
                higienePaciente = """

Los cuidados deben orientarse a minimizar la exposición a reservorios y mantener la asepsia absoluta de los dispositivos.

- Revisión médica diaria para retirada precoz de catéteres y sondas.

- Baño diario: Soluciones de clorhexidina jabonosa al 4% para reducir carga bacteriana cutánea, requiriendo SECADO COMPLETO posterior.

- Manipulación de vías/ventilación: Técnica estéril estricta, guantes limpios y desinfección de puertos/bioconectores con alcohol al 70% o clorhexidina alcohólica (fricción mecánica rotatoria).

- Para la recogida de muestras de portadores, es suficiente con recogerlas antes de la higiene diaria, es decir, dejando al menos 24h entre la última limpieza y la recogida.
                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

Aislamiento y confirmación mediante espectrometría de masas (MALDI-TOF MS) and antibiograma. Obligatoria la correlación con biomarcadores (procalcitonina, PCR) para diferenciar infección activa de colonización y evitar toxicidad y presión selectiva.

TRATAMIENTO

(Basado en perfil de susceptibilidad e individualizado)

- Infección Leve: Cotrimoxazol (15 mg/kg/día). Alternativas (si resistencia/alergia): levofloxacino (750 mg/día) o minociclina/tigeciclina.

- Infección Moderada/Grave (sepsis, neumonía, neutropenia febril): Terapia combinada. Cefiderocol (2g/8h iv) asociado a levofloxacino, cotrimoxazol o minociclina. Alternativa: ceftazidima/avibactam + aztreonam.

- Extracción inmediata de catéteres/sondas que constituyan el origen primario. Reevaluación clínica a las 48-72h.
                """.trimIndent(),
                criteriosRetirada = """
- Mantenimiento: Durante toda la estancia clínica salvo erradicación confirmada analítica y microbiológicamente.

- Retirada: Requiere resolución completa de signos/síntomas y un mínimo de DOS muestras de cultivo de control negativas (foco anatómico o frotis faríngeo/rectal).

- Importante: El primer cultivo de control no debe extraerse hasta transcurridas ≥ 72 horas desde la finalización del tratamiento antibiótico sistémico para evitar falsos negativos.
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_s_maltophilia
            ),
            // --- 4. Acinetobacter baumannii (MR) ---
            Infeccion(
                microorganismo = "Acinetobacter Baumannii (MR)",
                introduccion = """
El Acinetobacter baumannii multirresistente (MR) es un bacilo gramnegativo no fermentador, aerobio obligado e inmóvil. Destaca por su extraordinaria capacidad de supervivencia ambiental, permaneciendo viable durante periodos prolongados en superficies inanimadas y manos del personal bajo diversas condiciones de temperatura y pH. 

SIGNOS Y SÍNTOMAS

Ligados a la presencia de dispositivos invasivos y vulnerabilidad del huésped.

- Tracto respiratorio: Neumonías asociadas a ventilación mecánica (fiebre, inestabilidad hemodinámica, deterioro respiratorio).

- Bacteriemias: Relacionadas con catéteres venosos centrales.

- Heridas/Piel: Infección de heridas quirúrgicas y úlceras por presión.

CLASIFICACIÓN

- Caso Nuevo: Primera muestra (+) sin antecedentes.

- Caso Prevalente: Aislamiento en paciente con antecedentes, transcurridos ≥ 3 meses desde el último aislamiento.

- Caso No Clasificable: Antecedente en otro centro en los últimos 3 meses o paciente no hospitalizado físicamente.

Brote: Agregación de ≥ 3 casos en hospitalización convencional, tras 48h de ingreso.

CADENA EPIDEMIOLÓGICA
- Reservorio principal: Tracto gastrointestinal humano. (También coloniza piel en 25% de adultos sanos y faringe en 7%).

- Reservorio secundario: Superficies y equipos sanitarios de alta persistencia.

- Transmisión: Contacto indirecto mediado predominantemente por las manos del personal sanitario.

- Factores de riesgo: Procedimientos quirúrgicos, traqueotomías, dispositivos invasivos (CVC, sondas, ventilación), sexo varón, úlceras por presión y estancia previa en UCI.
                """.trimIndent(),
                precaucionesEstandar = """
Aplicación universal. La higiene de manos adquiere relevancia técnica diferencial: realizar obligatoriamente ANTES de entrar y AL SALIR de la habitación, inmediatamente tras la retirada de guantes y tras contacto con fluidos. Incluye higiene respiratoria, limpieza sistemática de superficies de alto contacto y manejo seguro de punzantes/residuos para evitar que las manos actúen como vehículo transmisor.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

Registro obligatorio en Orion Clinic. Ubicación prioritaria en habitación individual con presión neutra y puerta cerrada. (Cohorte permitida en alta ocupación solo con el mismo microorganismo). Prohibida la salida del paciente para terapias/rehabilitación grupales (deben realizarse en la propia habitación).

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: ESTRICTO)

6 Requisitos técnicos obligatorios:

1. Higiene de manos con solución hidroalcohólica al entrar y salir.

2. Uso permanente de mascarilla en el interior.

3. Puerta cerrada ininterrumpidamente.

4. Uso preceptivo de guantes.

5. Uso preceptivo de bata desechable.

6. Aforo máximo de 2 personas.

CONSIDERACIONES ESPECIALES

- Muestras: Envase rígido desechable. Relevo limpio-sucio en puerta con bolsa zip.

- Exitus: Circuito de mínima manipulación (celador interior EPI completo + asistente exterior sin EPI). Desinfección de camilla de acero con toallitas cloradas sin aclarado.

- Comida: Exclusivo material desechable.

- Traslados/Grúa: Restringido a lo indispensable. Preaviso a destino. Paciente cubierto (apósitos/sábanas). Modelo "doble celador". 

ENTORNO Y LIMPIEZA

- Equipos (fonendoscopios/termómetros) de uso exclusivo.

- Superficies no porosas (sin suciedad): toallitas cloradas sin aclarar.

- Superficies porosas: inmersionar en lejía 1/10 preparado en el momento. Mantener el objeto 10mn completamente inmersionado en la solución, sacar y secar en la medida de lo posible y luego dejar secar al aire.  

- Limpieza diaria/terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). PROHIBIDO EL USO DE AMONIOS CUATERNARIOS NEBULIZADOS.
                """.trimIndent(),
                higienePaciente = """

Higiene corporal sistemática diaria para reducir la carga microbiana y mitigar el vertido ambiental.

- Baño diario: Emplear soluciones de CLORHEXIDINA JABONOSA AL 4% para reducir la carga bacteriana cutánea. SECADO COMPLETO posterior obligatorio.

- Dispositivos: Manipulación de ventilación mecánica, aspiración y vías bajo técnica estéril estricta. Desinfección de puertos/bioconectores con alcohol 70% o clorhexidina alcohólica (fricción mecánica rotatoria).

CRIBADO

- Vigilancia activa mediante frotis faríngeos y rectales para identificar precozmente la colonización.

- Para la recogida de muestras de portadores, es suficiente con recogerlas antes de la higiene diaria, es decir, dejando al menos 24h entre la última limpieza y la recogida.
                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

Confirmación del género Acinetobacter (oxidasa negativos) y patrón de multirresistencia (falta de sensibilidad a ≥ 1 agente en ≥ 3 familias: aminoglucósidos, carbapenémicos, fluoroquinolonas, cefalosporinas extendidas, piperacilina-tazobactam, cotrimoxazol, polimixinas).

TRATAMIENTO

Alta complejidad por perfil de resistencias. Dirigido por el equipo PROA basándose en el antibiograma.

- Fármacos habituales: Polimixinas (colistina, polimixina B) suelen ser los únicos activos. 

- Terapia combinada: Según sensibilidad, asociar imipenem, sulbactam, tigeciclina, amikacina, tobramicina o rifampicina.
                """.trimIndent(),
                criteriosRetirada = """

- Control: Toma de muestras microbiológicas de vigilancia semanal (frotis faríngeo/rectal).

- Criterios de Retirada (Descolonización): Requiere validación de la Unidad de Preventiva. Se exigen al menos DOS cultivos consecutivos negativos (localización inicial/frotis faringeo-rectal), en momentos distintos, confirmación de curación clínica y que hayan transcurrido ≥ 72 horas desde el fin del tratamiento con ATB.
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_a_baumanii
            ),
            // --- 5. SARM / MRSA ---
            Infeccion(
                microorganismo = "SARM / MRSA",
                introduccion = """
El Staphylococcus aureus resistente a meticilina (SARM) es uno de los principales patógenos multirresistentes a nivel global. Su resistencia es de origen cromosómico (gen mecA, proteína PBP2a), confiriendo resistencia a todas las penicilinas, cefalosporinas y carbapenémicos.

SIGNOS Y SÍNTOMAS

La colonización precede a la infección en un 40-60% de los casos.

- Manifestaciones dependientes del foco: Bacteriemia asociada a catéter, neumonía, e infección de piel/partes blandas (signos inflamatorios y purulencia).

- Cuadros sistémicos: Sepsis con inestabilidad hemodinámica y fiebre.

CLASIFICACIÓN

- Caso Nuevo: Primera muestra (+) sin antecedentes.

- Caso Prevalente: Aislamiento con episodios previos registrados, transcurridos ≥ 3 meses desde el último positivo.

- Caso No Clasificable: Antecedente en otro centro en los últimos 3 meses o paciente no hospitalizado activamente.

Brote: Agregación de ≥ 3 casos en plantas convencionales, tras 48h de ingreso.

CADENA EPIDEMIOLÓGICA

- Reservorio primario: Pacientes colonizados/infectados (hasta un 30% de adultos sanos pueden estar colonizados). Localización preferente: vestíbulo nasal, orofaringe, perineo, ingles, axilas y recto.

- Reservorio secundario: Superficies del entorno (suelos, ropa de cama, cortinas).

- Transmisión: Fundamentalmente por manos del personal sanitario (vehículo tras contacto) y fómites.

- Factores de riesgo: Estancias prolongadas, UCI, antibióticos de amplio espectro, enfermedades graves, edad avanzada y procedimientos invasivos.
                """.trimIndent(),
                precaucionesEstandar = """
Aplicación transversal básica. La higiene de manos es la medida más importante: obligatoria ANTES de entrar y AL SALIR, tras retirada de guantes y tras contacto con fluidos. Incluye higiene respiratoria para minimizar aerosolización de secreciones.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

Registro obligatorio en Orion Clinic. Ubicación preferente en habitación individual. (Cohorte permitida exclusivamente con pacientes con el mismo microorganismo). Obligatoria la colocación de cartel de señalización en la puerta.

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: CONTACTO)

- Higiene de manos imperativa en los 5 momentos.

- EPI: Uso de guantes y bata desechable para cualquier contacto con paciente o entorno. 

CONSIDERACIONES ESPECIALES

- Muestras: Transporte en envase rígido desechable. Relevo limpio-sucio en puerta con bolsa zip.

- Exitus: Circuito de mínima manipulación (celador interior EPI completo + asistente exterior sin EPI). Desinfección de camilla de acero con toallitas cloradas sin aclarado.

- Comida: Exclusivo con material desechable. Desecho en Grupo III en interior.

- Traslados/Grúa: Restringido a lo indispensable. Modelo "doble celador". Preaviso y desinfección terminal de la sala de destino con levaduricidas.

ENTORNO Y LIMPIEZA

- Equipos (fonendoscopios/termómetros) de uso exclusivo.

- Limpieza diaria/terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS en la limpieza terminal. Lavandería a ≥ 65°C.
                """.trimIndent(),
                higienePaciente = """
CUIDADOS Y HIGIENE DEL PACIENTE

- Baño diario: Soluciones de CLORHEXIDINA JABONOSA AL 4% para control de carga bacteriana. SECADO COMPLETO posterior obligatorio.

- Dispositivos: Técnica aséptica rigurosa en manipulación de catéteres, sondas y heridas (principales vías de entrada). Material de curas desechable o exclusivo.

- Para la recogida de muestras de portadores, es suficiente con recogerlas antes de la higiene diaria, es decir, dejando al menos 24h entre la última limpieza y la recogida.

DESCOLONIZACIÓN Y CRIBADO

- Cribado (Vigilancia Activa): Frotis nasal, faríngeo y de heridas/lesiones.

- Descolonización Nasal: Aplicación de Mupirocina o Ácido fusídico (aprox. 5 días).

- Descolonización Corporal: Higiene diaria con esponja de clorhexidina 4%.

(En situación de brote intrafamiliar se valorará descolonizar a contactos).
                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

1. Cultivo: Medio de Chapman o cromogénicos (24-48h). Confirmación con coagulasa, látex o MALDI-TOF.

2. Resistencia: Fenotípica (disco cefoxitina < 22mm) o detección de PBP2a por látex.

3. Molecular: PCR para gen mecA (método de referencia, 2-6 horas).

TRATAMIENTO DE INFECCIÓN ACTIVA

Antibióticos monitorizados: Daptomicina, Linezolid, Teicoplanina y Trimetoprim-Sulfametoxazol (TMP-SMX), ajustados por antibiograma. (Vancomicina en desuso).
                """.trimIndent(),
                criteriosRetirada = """
- Descolonización técnica: Requiere al menos DOS cultivos consecutivos negativos (localización inicial + frotis nasal/faríngeo) en momentos distintos, sin evidencia de clínica activa.

- Periodo de seguridad: Las muestras de control NO deben obtenerse antes de transcurridas ≥ 48 HORAS desde el fin del tratamiento descolonizador/antibiótico para evitar falsos negativos.

- Alta: Criterio clínico. Si es dado de alta sin negativizar, el estado "Infectado/Colonizado" debe constar explícitamente en el informe para prevención en reingresos.
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_sarm
            ),
            // --- 6. ERV / VRE ---
            Infeccion(
                microorganismo = "Enterococos Resistentes a Vancomicina (ERV/VRE)",
                introduccion = """
Los enterococos son cocos grampositivos de la microbiota gastrointestinal/genitourinaria. Aunque E. faecalis es más prevalente, Enterococcus faecium presenta con mayor frecuencia resistencia adquirida a la vancomicina (ERV/VRE) mediante los genes vanA y vanB. Poseen extraordinaria resistencia ambiental (sobreviven semanas en superficies secas) y escasas opciones terapéuticas.

SIGNOS Y SÍNTOMAS

La colonización gastrointestinal precede a la infección invasiva.

- ITU: Presentación más frecuente (disuria, urgencia, fiebre; a menudo solo fiebre en sondados).

- Bacteriemia: Asociada a CVC o translocación. Cursa con inestabilidad hemodinámica y riesgo de endocarditis.

- Heridas/UPP: Exudado purulento, eritema o necrosis (suelen ser polimicrobianas).

- Intraabdominal: Secundaria a cirugía o patología biliar.

CLASIFICACIÓN

- Caso Nuevo: Primer aislamiento sin antecedentes registrados.

- Caso Prevalente: Aislamiento con antecedentes, transcurridos > 3 meses desde el último positivo. (Se asume estado de portador en reingresos frecuentes).

- Caso No Clasificable: Antecedente en otro centro en los últimos 3 meses o paciente no hospitalizado activamente.

Brote: ≥ 3 casos en hospitalización convencional en un mes.

CADENA EPIDEMIOLÓGICA

- Reservorio principal: Tracto gastrointestinal (heces con hasta 10^9 UFC/g, contaminando el periné).

- Reservorio secundario: Entorno inanimado (sobreviven de 5 días a 4 meses en superficies secas y equipos).

- Transmisión: Contacto directo (manos del personal) e indirecto (fómites).

- Factores de riesgo: Estancias prolongadas, inmunosupresión, IRC (hemodiálisis), dispositivos invasivos y, críticamente, exposición previa a antimicrobianos (vancomicina, cefalosporinas de 3ª generación, antianaerobios).
                """.trimIndent(),
                precaucionesEstandar = """
Aplicación universal. A diferencia de C. difficile (formador de esporas), la higiene de manos para ERV puede realizarse eficazmente con SOLUCIÓN HIDROALCOHÓLICA siguiendo los 5 momentos de la OMS. El lavado mecánico con agua y jabón se reserva únicamente para situaciones de suciedad visible.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

Registro obligatorio en Orion Clinic. Ubicación preferente en habitación individual con baño propio y puerta señalizada. Cohorte permitida solo con el mismo microorganismo. Contraindicado compartir habitación con inmunodeprimidos, heridas abiertas o portadores de CVC (salvo que también sean ERV+).

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: CONTACTO)

- Uso obligatorio de GUANTES al entrar para cualquier contacto con el paciente o entorno.

- Uso de BATA desechable obligatorio, retirada ANTES de salir de la habitación.

- Higiene de manos inmediata tras la retirada de guantes/bata, antes y después de abandonar la estancia.

ENTORNO Y LIMPIEZA

- Equipos (fonendoscopios/termómetros) de uso exclusivo en la medida de lo posible.

- Limpieza diaria/terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS en la limpieza terminal. Lavandería a ≥ 65°C.
                """.trimIndent(),
                higienePaciente = """

- Baño diario: Emplear soluciones de CLORHEXIDINA JABONOSA AL 4% para reducir carga cutánea. SECADO COMPLETO posterior obligatorio.

- Dispositivos: Técnica estéril estricta en ventilación, aspiración y vías. Desinfección de bioconectores con alcohol 70% o clorhexidina alcohólica (fricción rotatoria). Prioritaria la retirada precoz de sondas y CVC.

- Para la recogida de muestras de portadores, es suficiente con recogerlas antes de la higiene diaria, es decir, dejando al menos 24h entre la última limpieza y la recogida.

CRIBADO (VIGILANCIA ACTIVA)

Muestra de elección: frotis rectal. 
                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO
1. Identificación: Espectrometría de masas (MALDI-TOF).

2. Resistencia: Determinación de CMI (microdilución/E-test) o molecular (PCR para genes vanA/vanB).

Importante: Diferenciar infección de colonización (ej. orina en sondados sin clínica sistémica NO requiere tratamiento).

TRATAMIENTO

Reservado exclusivamente para infección clínica (nunca para colonización o bacteriuria asintomática). Supervisado por PROA.

- Sistémicas/Bacteriemias: Linezolid o Daptomicina.

- ITU baja no complicada: Nitrofurantoína o fosfomicina (si hay sensibilidad).

- Intraabdominal/UPP: Tigeciclina (controvertido en bacteriemias por bajos niveles séricos).
                """.trimIndent(),
                criteriosRetirada = """
- Criterios de Retirada: Requiere al menos DOS frotis rectales consecutivos negativos, separados por 1 semana.

- Periodo de seguridad: Esperar ≥ 72 HORAS tras finalizar tratamiento antibiótico contra el enterococo. Si el paciente es solo portador, esperar 7 DÍAS con medidas de higiene antes de iniciar el algoritmo de retirada.

- Alta: Consignar estatus de portador para seguridad en reingresos. No se recomiendan cultivos de curación ("test of cure") en asintomáticos.
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_erv
            ),
            // --- 7. Enterobacterias productoras de BLEE/AmpC/EPC ---
            Infeccion(
                microorganismo = "Enterobacterias BLEE / AmpC / EPC (OXA-48, VIM, NDM, IMP)",
                introduccion = """
Las enterobacterias son bacilos gramnegativos de la microbiota intestinal humana, con alta capacidad para acumular resistencia.

- BLEE y AmpC: Hidrolizan penicilinas y cefalosporinas de 3ª/4ª generación (E. coli, Klebsiella spp, P. mirabilis, E. cloacae).

- EPC (Carbapenemasas): Hidrolizan carbapenémicos y casi todos los betalactámicos. K. pneumoniae es el portador más frecuente (principalmente OXA-48). Se vigilan clase D (OXA-48, endémica) y clase B (VIM, NDM, IMP, con resistencia extrema).

SIGNOS Y SÍNTOMAS

La colonización rectal es el estado más prevalente y reservorio silencioso. La infección (ITU complicadas, neumonías por VM, bacteriemias por catéter) es indistinguible de cepas multisensibles. En geriatría/inmunodeprimidos puede cursar sin fiebre (deterioro agudo, confusión, inestabilidad hemodinámica).

CLASIFICACIÓN

- Caso Nuevo: Primer aislamiento confirmado sin antecedentes.

- Caso Prevalente: Reingreso/estancia con antecedentes de > 3 meses desde el último positivo.

Brote: ≥ 3 en planta. (Para VIM, NDM, IMP: 2 casos nosocomiales bastan para declarar alerta).

CADENA EPIDEMIOLÓGICA

- Reservorio principal: Tracto gastrointestinal (persiste meses).

- Transmisión: Contacto directo (manos del personal) e indirecto (fómites sin desinfectar).

- Factores de riesgo: Comorbilidades severas, edad avanzada, dispositivos invasivos, exposición a cefalosporinas/fluoroquinolonas.
                """.trimIndent(),
                precaucionesEstandar = """
Adherencia estricta en los 5 momentos de la OMS. El uso de SOLUCIÓN HIDROALCOHÓLICA es eficaz para eliminar enterobacterias en manos (salvo suciedad visible). Extremar precaución con fluidos corporales.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

- BLEE/AmpC: Dada su alta endemicidad, NO se aísla, SALVO brote de ≥ 3 casos de Klebsiella spp BLEE.

- EPC (Alto Riesgo: OXA-48, VIM, IMP, NDM): Aislamiento ESTRICTO obligatorio para toda la familia de enterobacterias. Habitación individual con baño y puerta cerrada. (Cohorte exclusiva del mismo mecanismo de resistencia). ¡NUNCA MEZCLAR EPC con BLEE, ni diferentes tipos de EPC!

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: ESTRICTO para EPC)

6 Requisitos técnicos obligatorios:

1. Higiene de manos al entrar y salir.

2. Uso permanente de mascarilla en el interior.

3. Puerta cerrada ininterrumpidamente.

4. Uso preceptivo de guantes.

5. Uso preceptivo de bata desechable.

6. Aforo máximo de 2 personas.

CONSIDERACIONES ESPECIALES

- Muestras: Transporte en envase rígido desechable. Relevo limpio-sucio en puerta con bolsa zip.

- Exitus: Circuito de mínima manipulación (celador interior EPI completo + asistente exterior sin EPI). Desinfección de camilla de acero con toallitas cloradas sin aclarado.

- Comida: Exclusivo con material desechable. Desecho en Grupo III en interior.

- Traslados/Grúa: Restringido a lo indispensable. Modelo "doble celador". Preaviso y desinfección terminal de la sala de destino con levaduricidas.


ENTORNO Y LIMPIEZA

- Equipos (fonendoscopios/termómetros) exclusivos.

- Limpieza diaria/terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS en limpieza terminal. Lavandería a ≥ 65°C.
                """.trimIndent(),
                higienePaciente = """

- Baño diario: Soluciones de CLORHEXIDINA JABONOSA AL 4%. SECADO COMPLETO posterior obligatorio.

- Dispositivos: Técnica estéril estricta. Desinfección de bioconectores con alcohol 70% o clorhexidina alcohólica.

- Para la recogida de muestras de portadores, es suficiente con recogerlas antes de la higiene diaria, es decir, dejando al menos 24h entre la última limpieza y la recogida.

                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

Cultivo y MALDI-TOF. Confirmación de carbapenemasas por inmunocromatografía o PCR. Fundamental diferenciar BLEE (sensibles a carbapenémicos) de EPC (requieren terapia dirigida).

TRATAMIENTO (Supervisado por PROA)

- BLEE/AmpC graves: Carbapenémicos (Meropenem preferible a Ertapenem de inicio; desescalar si hay sensibilidad).

- EPC (OXA-48): Ceftazidima-avibactam.

- EPC (VIM, NDM, IMP): Aztreonam + Ceftazidima-avibactam o Cefiderocol.

- ITU bajas no complicadas: Fosfomicina o nitrofurantoína.

                """.trimIndent(),
                criteriosRetirada = """
- BROTE Klebsiella spp BLEE: Requiere ≥ 2 muestras (faríngeo+rectal) de vigilancia negativas consecutivas (separadas 1 semana). Esperar 72h tras fin de antibiótico (o 7 días si es solo portador).

- AISLAMIENTO EPC: Requiere ≥ 3 muestras (faríngeo+rectal) de vigilancia negativas consecutivas (separadas 1 semana). Esperar 72h tras fin de antibiótico (o 7 días si es solo portador). Tras las 3 muestras, el paciente se mantiene en continuo cribado (estudio cada 7 días).
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_blee,
                imagenAlgoritmo2 = R.drawable.algoritmo_epc
            ),
            // --- 8. Pseudomonas aeruginosa (MR) ---
            Infeccion(
                microorganismo = "Pseudomonas Aeruginosa (MR)",
                introduccion = """
La Pseudomonas aeruginosa (PA) es un bacilo gramnegativo de crecimiento aerobio. Posee escasos requerimientos nutricionales, lo que le permite proliferar en ambientes húmedos (su reservorio principal en la clínica). Actúa como patógeno oportunista combinando resistencia natural a múltiples antimicrobianos con una extraordinaria capacidad para desarrollar resistencia adquirida (mutaciones cromosómicas).

SIGNOS Y SÍNTOMAS

Capaz de infectar casi cualquier localización, a menudo con alta gravedad.

- Respiratorio: Neumonía (frecuente en ventilación mecánica/broncoaspiración).

- Urinario: ITU vinculadas a sondaje vesical.

- Sistémico: Bacteriemia/sepsis (asociada a catéteres).

- Piel/Blandos: Infección de heridas quirúrgicas y UPP.

*Importante distinguir entre infección (aislamiento + clínica) y colonización (aislamiento sin clínica, frecuente en periné/faringe).*

CLASIFICACIÓN (PAMR: Resistencia a ≥ 3 grupos de fármacos centinela)

- Caso Nuevo: Primera muestra (+) sin antecedentes previos al ingreso.

- Caso Prevalente: Aislamiento con episodios previos registrados, transcurridos ≥ 3 meses desde el último positivo.

- Caso No Clasificable: Antecedente en otro centro en los últimos 3 meses o paciente no hospitalizado físicamente.

Brote: ≥ 3 en hospitalización convencional (tras 48h de ingreso).

CADENA EPIDEMIOLÓGICA

- Reservorio principal: Ambiental (ambientes húmedos, desagües, grifos, soluciones). Manos del personal (temporal) y microbiota endógena.

- Transmisión: Contacto directo e indirecto (fómites o manos contaminadas).

- Factores de riesgo: Tiempos de estancia prolongados, ventilación mecánica, sondaje y, críticamente, la presión selectiva por tratamientos antibióticos previos (quinolonas, carbapenémicos, betalactámicos).
                """.trimIndent(),
                precaucionesEstandar = """
Aplicación estricta de la higiene de manos (medida fundamental) antes de entrar y al salir, tras retirada de guantes y manipulación de fluidos. Asegurar la higiene entre pacientes para evitar la transmisión cruzada.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

Registro obligatorio en Orion Clinic. Ubicación técnica preferente en habitación individual. (Cohorte permitida exclusivamente con pacientes colonizados/infectados por el mismo microorganismo). Cartel de señalización obligatorio en puerta.

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: CONTACTO)

- Uso de GUANTES obligatorio para cualquier contacto con el paciente o su entorno.

- Uso de BATA desechable si se prevé contacto directo con el paciente o superficies contaminadas (desechar antes de salir).

- Higiene de manos posterior a la retirada del EPI obligatoria.

CONSIDERACIONES ESPECIALES

- Traslados: Limitados a motivos clínicos estrictamente necesarios. Notificar a destino. Paciente con barreras (guantes, bata, ropa limpia, cobertura de heridas). Visitas restringidas e instruidas.

ENTORNO Y LIMPIEZA

(Prioritario dada su altísima viabilidad en ambientes húmedos)

- Equipos (fonendoscopios/termómetros) de uso exclusivo.

- Limpieza diaria/terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS en la limpieza terminal. Lavandería a ≥ 65°C.
                """.trimIndent(),
                higienePaciente = """

La higiene corporal es una medida terapéutica activa para reducir la carga cutánea.

- Baño diario: Soluciones de CLORHEXIDINA JABONOSA AL 4%. SECADO COMPLETO posterior obligatorio.

- Dispositivos: Técnica estéril estricta en ventilación, aspiración y vías. Desinfección de bioconectores con alcohol 70% o clorhexidina alcohólica.

- Para la recogida de muestras de portadores, es suficiente con recogerlas antes de la higiene diaria, es decir, dejando al menos 24h entre la última limpieza y la recogida.


                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

Aislamiento microbiológico. Se confirma PAMR si el antibiograma muestra resistencia a ≥ 3 de los siguientes grupos: aminoglucósidos, carbapenémicos, fluoroquinolonas, cefalosporinas/penicilinas antipseudomónicas, monobactámicos o polimixinas.

TRATAMIENTO

Dirigido por antibiograma y supervisado por PROA. Es vital el uso racional por su alta capacidad de mutación intra-tratamiento.

Opciones: Combinaciones de betalactámicos con inhibidores, carbapenémicos (meropenem), aminoglucósidos (amikacina, tobramicina) o polimixinas (colistina) en resistencia extrema.
                """.trimIndent(),
                criteriosRetirada = """
- Alta clínica: Posible independientemente de los cultivos de control, consignando el estatus en el informe.

- Criterio de Descolonización (Retirada de Aislamiento): Requiere al menos DOS cultivos consecutivos negativos de la localización inicial o faríngeo + rectal, en momentos diferentes.

- Periodo de seguridad: No extraer muestras de control antes de transcurridas ≥ 48 HORAS desde el fin del tratamiento antibiótico. La mejoría clínica no descarta la colonización.
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_p_aeruginosa
            ),
// --- 9. Mycobacterium tuberculosis (Tuberculosis) ---
            Infeccion(
                microorganismo = "Mycobacterium Tuberculosis (Tuberculosis)",
                introduccion = """
La tuberculosis (TB) es producida por especies del complejo Mycobacterium, siendo M. tuberculosis (bacteria aerobia, ácido-alcohol resistente) el agente etiológico predominante en nuestro medio. La forma pulmonar es la más frecuente y relevante para el control epidemiológico.

SIGNOS Y SÍNTOMAS

- Presencia de signos, síntomas o hallazgos radiológicos compatibles con TB activa.

- La sintomatología respiratoria persistente es el indicador principal en la forma pulmonar.

- *Nota:* La administración de un ciclo completo de terapia antituberculosa ante un cuadro sugestivo ya constituye un criterio clínico definitorio de caso.

CLASIFICACIÓN (ECDC)

- Caso Confirmado: Aislamiento en cultivo o PCR(+) con baciloscopia(+).

- Caso Probable: Criterios clínicos + baciloscopia(+), PCR(+) sin baciloscopia, o presencia histológica de granulomas.

- TB Resistente: Resistencia a cualquier fármaco de 1ª línea (isoniazida, rifampicina, pirazinamida, etambutol, estreptomicina).

- MDR-TB (Multirresistente): Resistencia a isoniazida + rifampicina.

- XDR-TB (Extremadamente resistente): MDR + resistencia a fluoroquinolona y fármaco inyectable de 2ª línea.

CADENA EPIDEMIOLÓGICA

- Reservorio fundamental: Ser humano infectado con enfermedad activa (especialmente pulmonar/laríngea).

- Transmisión: Vía aérea predominante (gotas de 1-5 micras al toser/hablar).

- Factores de riesgo de progresión: Infección por VIH/SIDA (principal factor), diabetes, insuficiencia renal, terapias anti-TNF, neoplasias y malnutrición.

                """.trimIndent(),
                precaucionesEstandar = """
Aplicación de normas habituales de higiene de manos. NO se requieren medidas de descontaminación extraordinarias para fómites o vajilla. Es fundamental instruir al paciente en la HIGIENE RESPIRATORIA y uso de pañuelos desechables.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

Aislamiento obligatorio y precoz. Habitación individual con puerta RÍGIDAMENTE CERRADA. (Priorizar habitaciones con renovación de aire/ventilación natural). Cartel identificativo de Aislamiento Aéreo obligatorio en puerta. Cohorte solo bajo validación de Preventiva (misma cepa y sensibilidad).

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: AÉREO / RESPIRATORIO)

- Personal sanitario: Mascarilla FFP2 obligatoria al entrar. (FFP3 en caso de aerosolización de fármacos).

- EPI complementario: Guantes y bata desechable.

- Aforo máximo: 2 personas en la habitación.

- Paciente: Debe usar MASCARILLA QUIRÚRGICA si interactúa con personas en la habitación o si necesita salir (traslados).

CONSIDERACIONES ESPECIALES

- Traslados: Mínimo tiempo posible. Prohibida la asistencia a terapias en salas compartidas (ej. gimnasios); realizar RHB en la habitación o en espacios comunitarios al final de la jornada (sin otros pacientes y ventilando después).

- Visitas: Aforo restringido a 2 personas. Prohibido el contacto con niños y personas inmunocomprometidas.

ENTORNO Y LIMPIEZA

A diferencia de otros patógenos, NO precisa medidas especiales para fómites.

- Limpieza terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). PROHIBIDOS AMONIOS CUATERNARIOS NEBULIZADOS.

- Material reutilizable: Descontaminación según procesos de esterilización estándar del centro.
                """.trimIndent(),
                higienePaciente = """
CUIDADOS Y EDUCACIÓN DEL PACIENTE

La educación es crítica. 

- Instruir sobre la adherencia ESTRICTA al tratamiento farmacológico y medidas de higiene respiratoria.

- Enfermería debe supervisar la toma de medicación (valorar Terapia Directamente Observada si hay riesgo de incumplimiento).
                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

- TB Latente: Prueba de tuberculina (Mantoux) o IGRA.

- Enfermedad activa: Cultivo o PCR + baciloscopia(+). Granulomas caseificantes (probable).

TRATAMIENTO

El tratamiento correcto es la medida más eficaz de control. Notificación EDO obligatoria.

- Casos Nuevos (Pauta 6 meses): Fase inicial (2 meses) con Isoniazida, Rifampicina, Pirazinamida y Etambutol (2HRZE). Fase de continuación (4 meses) con Isoniazida y Rifampicina (4HR). *El Etambutol es obligatorio de inicio por la tasa de resistencia 1ª a isoniazida.*
                """.trimIndent(),
                criteriosRetirada = """
                    
- Criterio empírico: La contagiosidad disminuye apreciablemente tras 2-3 semanas de tratamiento efectivo (posible desaislamiento si el criterio clínico acompaña).

- Criterio Microbiológico (Hospital La Magdalena): El aislamiento aéreo se suspende al obtener 3 MUESTRAS CONSECUTIVAS de esputo con baciloscopia NEGATIVA, tomadas en días diferentes.

- Casos MDR: Desaislamiento estrictamente basado en negatividad microbiológica y respuesta clínica.
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_tb
            ),
            // --- 10. COVID-19 (SARS-CoV-2) ---
            Infeccion(
                microorganismo = "COVID-19 (SARS-CoV-2)",
                introduccion = """
La COVID-19 es una infección respiratoria aguda (IRA) causada por el betacoronavirus SARS-CoV-2. Su manejo intrahospitalario se centra en la detección precoz y mitigación del impacto en pacientes vulnerables para interrumpir la transmisión nosocomial.

SIGNOS Y SÍNTOMAS

Presentación clínica heterogénea (asintomática a fracaso multiorgánico). 

- Frecuentes: Fiebre, tos seca, disnea, fatiga, mialgias, cefalea, odinofagia, anosmia/ageusia.

- Población geriátrica (atípica): Deterioro funcional agudo, desorientación, caídas o descompensación sin fiebre evidente.

CLASIFICACIÓN

- Caso Sospechoso: IRA súbita + tos, fiebre o disnea.

- Caso Confirmado: Infección activa demostrada por PDIA (PCR o Antígenos).

- Caso Probable: Clínica compatible + vínculo estrecho/radiología sugestiva, sin laboratorio.

Brote: ≥ 3 casos confirmados vinculados en una unidad en un periodo de transmisión activa (aprox. 7 días).

CADENA EPIDEMIOLÓGICA

- Reservorio: Ser humano infectado (relevancia de asintomáticos/presintomáticos).

- Transmisión: Vía aérea (inhalación de aerosoles y gotas) y contacto indirecto (fómites). Riesgo exponencial en PGA (Procedimientos Generadores de Aerosoles: intubación, terapias respiratorias).

- Factores de riesgo de gravedad: >60 años, inmunodepresión y comorbilidades crónicas.
                """.trimIndent(),
                precaucionesEstandar = """
Cumplimiento riguroso de la higiene de manos (5 momentos OMS) y la higiene respiratoria. Asegurar limpieza y desinfección adecuada de equipos compartidos antes de su uso en otros pacientes.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN
Registro de Aislamiento Respiratorio en Orion Clinic. Habitación individual priorizando ventilación natural cruzada o renovación eficiente. Puerta siempre CERRADA y señalizada. (Cohorte permitida en brote/saturación bajo autorización de Preventiva).

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: AÉREO)

- Personal sanitario: Mascarilla FFP2 al entrar (FFP3 en caso de aerosolización de fármacos/PGA). Guantes y bata desechable.

- Aforo máximo: 2 personas en la habitación.

- Paciente: Mascarilla quirúrgica si el personal entra en la habitación o si necesita salir (traslados).

CONSIDERACIONES ESPECIALES

- Traslados: Mínimo tiempo posible. Prohibida asistencia a terapias compartidas; realizar RHB en la habitación. Paciente con mascarilla quirúrgica ajustada.

- Visitas: Acceso restringido. Prohibido contacto con niños/inmunocomprometidos. Aforo de 2 personas.

ENTORNO Y LIMPIEZA

NO precisa descontaminación especial de fómites.

- Limpieza terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). SÍ ESTÁ INDICADO UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS.

- Material reutilizable: Esterilización estándar.
                """.trimIndent(),
                higienePaciente = """
CUIDADOS Y EDUCACIÓN DEL PACIENTE

- Higiene corporal diaria.

- Monitorización estrecha de constantes vitales (saturación O2, FR) para detectar deterioro respiratorio temprano.

- Instruir al paciente para mantener la mascarilla puesta con el personal presente y realizar higiene respiratoria al toser.

                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

- Test rápido de antígenos: De elección para cribado inicial sintomático (rapidez).

- RT-PCR: Referencia para confirmación.

TRATAMIENTO

Fundamentalmente de soporte (oxigenoterapia, corticoides, profilaxis tromboembólica). Antivirales específicos (remdesivir, nirmatrelvir/ritonavir) se valorarán individualmente en coordinación con Farmacia y Medicina Interna.
                """.trimIndent(),
                criteriosRetirada = """
                    
- Cuadros leves-moderados: Mínimo 10 DÍAS desde inicio de síntomas (o diagnóstico en asintomáticos) + al menos 3 DÍAS sin fiebre/clínica aguda.

- Inmunodeprimidos / Graves: Extensión a 14-21 días. Requiere criterio microbiológico (PCR negativa o Ct alto) bajo supervisión de Preventiva.

- Límite temporal: Tras >21 días, si el paciente está recuperado, se desaislará INDEPENDIENTEMENTE del resultado de la PCR. 

*No se recomienda prueba diagnóstica de control rutinaria ("test of cure") salvo en vulnerables.*
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_covid19
            ),
            // --- 11. Virus Sincitial Respiratorio (VRS) ---
            Infeccion(
                microorganismo = "Virus Sincitial Respiratorio (VRS)",
                introduccion = """
El Virus Sincitial Respiratorio (VRS) es un virus ARN envuelto (familia Pneumoviridae). Aunque clásicamente pediátrico, es un agente nosocomial altamente contagioso de primer orden en adultos mayores, pacientes institucionalizados y con comorbilidades crónicas severas (EPOC, cardiopatías, inmunosupresión).

SIGNOS Y SÍNTOMAS

Periodo de incubación: 4-6 días. En adultos, difiere del cuadro pediátrico, manifestándose frecuentemente como exacerbación aguda de patologías base.

- Síntomas iniciales: Rinorrea, congestión nasal, odinofagia.

- Progresión: Tos persistente, disnea, sibilancias y taquipnea.

- Pacientes geriátricos: La fiebre puede estar ausente o ser de bajo grado (retrasando la sospecha).

- Casos severos: Neumonía viral o sobreinfección bacteriana (hipoxemia, necesidad de soporte ventilatorio).

CLASIFICACIÓN

Cualquier detección mediante técnicas moleculares o antigénicas en el contexto de un cuadro clínico agudo establece infección activa (el VRS no coloniza crónicamente a adultos inmunocompetentes).

- Caso Nosocomial: Detección tras 48h de ingreso.

Brote: Confirmación microbiológica de ≥ 2 casos nosocomiales con nexo epidemiológico en un periodo de 7 días. (Exige medidas extraordinarias: cierre técnico de sala, restricción de ingresos/visitas/traslados y cribado de contactos).

CADENA EPIDEMIOLÓGICA

- Reservorio exclusivo: Ser humano (pacientes, personal o visitas asintomáticas/paucisintomáticas).

- Transmisión: Contacto directo (mucosas) o indirecto (manos/fómites). Alta viabilidad ambiental (hasta 6h en superficies no porosas; 30-45 min en ropa/manos). Transmisión por gotas gruesas (< 1,5 metros).

- Huésped susceptible: Edad avanzada, senescencia inmunológica, patologías crónicas descompensadas o deterioro funcional que dificulte el aclaramiento de secreciones.
                """.trimIndent(),
                precaucionesEstandar = """
Higiene de manos estricta (5 momentos OMS). El VRS es un virus envuelto altamente susceptible a inactivación química; la fricción con SOLUCIÓN HIDROALCOHÓLICA es el método de elección. El lavado con agua y jabón se reserva para suciedad visible. Higiene respiratoria y manejo seguro de equipos de oxigenoterapia.
                """.trimIndent(),
                precaucionesAmpliadas = """
PROCEDIMIENTOS / UBICACIÓN

Registro de Aislamiento de Contacto/Gotas en Orion Clinic. Habitación individual con baño exclusivo y PUERTA CERRADA permanentemente. (Cohorte permitida si no hay habitaciones, con separación física mínima de 1,5m entre camas).

PRECAUCIONES AMPLIADAS (TIPO DE AISLAMIENTO: CONTACTO + GOTAS)

- EPI obligatorio ANTES de ingresar: Bata desechable (resistente a fluidos si procede) y guantes.

- Protección respiratoria: MASCARILLA QUIRÚRGICA obligatoria.

- En procedimientos generadores de aerosoles (PGA: aspiración, VMNI, fisioterapia intensiva): Sustituir por mascarilla FFP2 o superior + Protección Ocular ajustada.

- Retirada del EPI: En el interior de la habitación, junto a la puerta, seguido de higiene de manos. Si se utiliza mascarilla FFP2 por haber nebulizado, esa mascarilla debe desecharse en el exterior. 

CONSIDERACIONES ESPECIALES

- Traslados: Mínimo tiempo posible. Prohibida asistencia a terapias compartidas. Paciente con guantes, bata, ropa limpia y MASCARILLA QUIRÚRGICA ajustada.

- Fisioterapia respiratoria: Planificar en los últimos turnos de la jornada para minimizar riesgos de transmisión cruzada.

- Visitas: Aforo restringido. Prohibido contacto con niños/inmunocomprometidos.

ENTORNO Y LIMPIEZA

- Equipos (fonendoscopios/termómetros) exclusivos.

- Limpieza diaria: Intensificada en superficies de alto contacto (barandillas, monitores, mesillas) con desinfectantes validados (amonio cuaternario, peróxido, cloro).

- Limpieza terminal: Taski Sprint H-100 al 5% con fricción mecánica agresiva (15 min acción). UTILIZAR AMONIOS CUATERNARIOS NEBULIZADOS. Lavandería a ≥ 65°C.

                """.trimIndent(),
                higienePaciente = """

- Soporte clínico centrado en la monitorización de la función respiratoria y oxigenación.

- Manipulación de secreciones y oxigenoterapia bajo técnica aséptica estricta con EPI completo.

- No requiere consideraciones especiales en la higiene diaria general (baño estándar).
                """.trimIndent(),
                diagnosticoTratamiento = """
DIAGNÓSTICO

- PCR multiplex (exudado nasofaríngeo/aspirado). Es el método de confirmación.

- Los Test Rápidos de Antígenos tienen muy baja sensibilidad en adultos; un resultado negativo NO descarta la infección y obliga a realizar PCR.

TRATAMIENTO

Estrictamente de SOPORTE: Oxigenoterapia escalonada, hidratación (para aclaramiento mucociliar). 

- Broncodilatadores: Solo si hay broncoespasmo objetivable o exacerbación de EPOC/Asma.

- Antivirales (Ribavirina): Eficacia controvertida y alta toxicidad; relegada a excepcionalidad en inmunodepresión profunda bajo consenso.

- Antibioterapia: Contraindicada salvo sospecha de sobreinfección bacteriana secundaria.
                """.trimIndent(),
                criteriosRetirada = """
- Regla general (Adultos inmunocompetentes): El aislamiento se mantendrá hasta la curación clínica completa (resolución de sintomatología) + mínimo 24 a 48 HORAS de afebrilidad sin uso de antipiréticos.

- Inmunosupresión severa: La excreción viral puede prolongarse. Requiere evaluación de Preventiva y, en ocasiones, confirmación de negatividad mediante PCR de control.
                """.trimIndent(),
                imagenAlgoritmo = R.drawable.algoritmo_vrs
            )
        )
    }

    private fun configurarRecyclerView() {
        adaptador = InfeccionAdapter(listaCompleta)
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
            it.microorganismo.contains(textoBusqueda, ignoreCase = true) ||
                    it.introduccion.contains(textoBusqueda, ignoreCase = true) ||
                    it.precaucionesEstandar.contains(textoBusqueda, ignoreCase = true) ||
                    it.precaucionesAmpliadas.contains(textoBusqueda, ignoreCase = true) ||
                    it.diagnosticoTratamiento.contains(textoBusqueda, ignoreCase = true)
        }
        adaptador.actualizarLista(listaFiltrada)
    }
}
