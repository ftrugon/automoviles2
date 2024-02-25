import kotlin.math.ceil
import kotlin.random.Random

/**
 * Clase que representa una carrera entre varios vehículos.
 *
 * @property nombreCarrera El nombre de la carrera.
 * @property distanciaTotal La distancia total de la carrera en kilómetros.
 * @property participantes La lista de vehículos que participan en la carrera.
 */
class Carrera(
    nombreCarrera: String,
    private val distanciaTotal: Float,
    private val participantes: List<Vehiculo>,

    ) {
    private var estadoCarrera: Boolean = false
    private var historialAcciones = mutableMapOf<String, MutableList<String>>()

    init {
        require(nombreCarrera.isNotEmpty()) {"El nombre de la carrera no puede estar vacio"}
        require(distanciaTotal >= 1000) {"La distancia de la carrera tiene que ser mayor a 1000Km"}

    }

    /**
     * Inicia la carrera, permitiendo que los vehículos avancen hasta que uno de ellos llegue a la meta.
     */
    fun iniciarcarrera() {
        estadoCarrera = true
        while (estadoCarrera) {
            val aleatorio = participantes.random()
            avanzarVehiculo(aleatorio)
            determinarGanador()
        }
    }


    /**
     * Se encarga de hacer que los vehiculos se muevan una distancia aleatoria entre 10 y 200 km. Si el vehículo
     * necesita repostar, se llama al método repostarVehiculo() y tambien se llama a realizarfiligrana.
     * @param vehiculo es el vehiculo al que le ha tocado moverse
     */
    private fun avanzarVehiculo(vehiculo: Vehiculo){

        var aleatorio = Random.nextInt(10,201).toFloat()
        registrarAccion(vehiculo.nombre,"Iniciar viaje: A recorrer $aleatorio Kms (${vehiculo.kilometrosactuales.redondear(2)}kms y ${vehiculo.combustibleactual.redondear(2)}L actuales)")

        for (i in 1..ceil(aleatorio/20).toInt()){
            val arrecorrer = minOf(20f, aleatorio)

            val aleatoriedad = Random.nextInt(1,3)
            if (aleatoriedad == 1){
                val recorrido = vehiculo.realizarviaje(arrecorrer)
                if (recorrido == 0F){
                    registrarAccion(vehiculo.nombre,"Avance tramo: ${(arrecorrer-recorrido).redondear(2)} km")
                }else{
                    registrarAccion(vehiculo.nombre,"Avance tramo: ${(arrecorrer-recorrido).redondear(2)} km")
                    repostarVehiculo(vehiculo)
                    registrarAccion(vehiculo.nombre,"Se han repostado ${vehiculo.capacidadcombustible}")
                }
            }else{
                realizarFiligrana(vehiculo)
                if (vehiculo is Automovil) registrarAccion(vehiculo.nombre,"Derrape, quedan ${vehiculo.combustibleactual.redondear(2)}L")else registrarAccion(vehiculo.nombre,"Caballito, quedan ${vehiculo.combustibleactual.redondear(2)}L")
            }
            aleatorio -= 20F
        }
    }


    /**
     * Resposta el vehiculo llenando el combustible indicado, si no se indica el tanque se llena entero
     * @param vehiculo el vehiculo al que se le llenara el tanque
     * @param cantidad la cantidad de combustible a rellenar
     */
    private fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float = 0F){
        vehiculo.repostar(cantidad)
    }

    /**
     * Realiza una filigrana al vehiculo, si es una moto un caballito y si es un coche un derrape
     * @param vehiculo el vehiculo que eraliza el derrape o la filigrana
     */
    private fun realizarFiligrana(vehiculo: Vehiculo){
        if (vehiculo is Automovil) {
            vehiculo.realizarderrape()
        }else if (vehiculo is Motocicleta){
            vehiculo.realizarcaballito()
        }
    }

    /**
     * Comprueba si alguno de los corredores ha superado la longitud del campo
     */
    private fun determinarGanador(){
        for (vehi in participantes){
            if (vehi.kilometrosactuales >= distanciaTotal){
                println("!!!HAY UN GANADOR!!!")
                estadoCarrera = false
                println(vehi.nombre)
            }
        }
    }

    /**
     * Devuelve una lista de los resultados de cadda participante
     * @return la lista de los resultados de las carreras
     */
    fun obtenerResultados():List<ResultadoCarrera>{
        val listaresultados = mutableListOf<ResultadoCarrera>()
        var cont = 1
        for (vehi in participantes.sortedByDescending { it.kilometrosactuales }){
            listaresultados.add(ResultadoCarrera(vehi,cont,vehi.kilometrosactuales,vehi.paradasrepostajes,historialAcciones[vehi.nombre]?.toList()?: emptyList()))
            cont++
        }
        return listaresultados
    }


    /**
     * registra si un vehiculo ha hecho una cosa u otra
     * @param vehiculo el vehiculo el cual queremos guardar la accion
     * @param accion la accion que hace el vehiculo, un derrape, un avance, repostar
     */
    private fun registrarAccion(vehiculo: String, accion: String){
        if (vehiculo in historialAcciones){
            historialAcciones[vehiculo]?.add(accion)
        }else{
            historialAcciones[vehiculo] = mutableListOf(accion)
        }
    }


}