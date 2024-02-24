import kotlin.math.ceil
import kotlin.math.floor
import kotlin.random.Random

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

    fun iniciarcarrera() {
        estadoCarrera = true
        while (estadoCarrera) {
            val aleatorio = participantes.random()
            avanzarVehiculo(aleatorio)
            determinarGanador()
        }
    }

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


    private fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float = 0F){
        vehiculo.repostar(cantidad)
    }

    private fun realizarFiligrana(vehiculo: Vehiculo){
        if (vehiculo is Automovil) {
            vehiculo.realizarderrape()
        }else if (vehiculo is Motocicleta){
            vehiculo.realizarcaballito()
        }
    }

    private fun determinarGanador(){
        for (vehi in participantes){
            if (vehi.kilometrosactuales >= distanciaTotal){
                println("!!!HAY UN GANADOR!!!")
                estadoCarrera = false
                println(vehi.nombre)
            }
        }
    }


    fun obtenerResultados():List<ResultadoCarrera>{
        val listaresultados = mutableListOf<ResultadoCarrera>()
        var cont = 1
        for (vehi in participantes.sortedByDescending { it.kilometrosactuales }){
            listaresultados.add(ResultadoCarrera(vehi,cont,vehi.kilometrosactuales,vehi.paradasrepostajes,historialAcciones[vehi.nombre]?.toList()?: emptyList()))
            cont++
        }
        return listaresultados
    }

    fun registrarAccion(vehiculo: String, accion: String){
        if (vehiculo in historialAcciones){
            historialAcciones[vehiculo]?.add(accion)
        }else{
            historialAcciones[vehiculo] = mutableListOf(accion)
        }
    }


}