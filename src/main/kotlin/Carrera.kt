import kotlin.random.Random

class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Float,
    val participantes: List<Vehiculo>,

) {
    var estadoCarrera: Boolean = false
    var historialAcciones = mutableMapOf<String, MutableList<String>>()
    var posiciones =  mutableMapOf<String, Int>()

    init {
        require(nombreCarrera.isNotEmpty()) {"El nombre de la carrera no puede estar vacio"}
        require(distanciaTotal >= 1000) {"La distancia de la carrera tiene que ser mayor a 1000Km"}

    }

    fun iniciarcarrera() {
        estadoCarrera = true

    }

    fun avanzarVehiculo(vehiculo: Vehiculo){
        val arealizar = Random.nextInt(10,201).toFloat()

        val quedaalgo = vehiculo.realizarviaje(arealizar)
        if (quedaalgo != 0F){
            println("nada")
            repostarVehiculo(vehiculo,1F)
        }
    }

    fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float){
        vehiculo.repostar(cantidad)
    }

    fun realizarFiligrana(vehiculo: Vehiculo){
        if (vehiculo is Automovil) {
            vehiculo.realizarderrape()
        }else if (vehiculo is Motocicleta){
            vehiculo.realizarcaballito()
        }
    }

    fun actualizarPosiciones(){

    }

    fun determinarGanador(){

    }

    fun obtenerResultados(){

    }

    fun registrarAccion(vehiculo: String, accion: String){

    }
}