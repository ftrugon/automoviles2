import kotlin.random.Random

class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Float,
    val participantes: List<Vehiculo>,

) {
    var estadoCarrera: Boolean = false
    var historialAcciones = mutableMapOf<String, MutableList<String>>()
    var posiciones =  mutableMapOf<Vehiculo, Int>()

    init {
        require(nombreCarrera.isNotEmpty()) {"El nombre de la carrera no puede estar vacio"}
        require(distanciaTotal >= 1000) {"La distancia de la carrera tiene que ser mayor a 1000Km"}

    }

    fun iniciarcarrera() {
        estadoCarrera = true
        while (estadoCarrera) {
            val aleatorio = participantes.random()
            avanzarVehiculo(aleatorio)
            actualizarPosiciones()
            determinarGanador()
            registrarAccion(aleatorio.nombre,"fokar")
        }
    }

    fun avanzarVehiculo(vehiculo: Vehiculo){


    }

    fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float = 0F){
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
        var cont = 1
        val copiadeparticipantes = participantes.toMutableList()
        do {

            var mayorastaelmoment = copiadeparticipantes[0]
            copiadeparticipantes.forEach { if (it.kilometrosactuales > mayorastaelmoment.kilometrosactuales){mayorastaelmoment = it} }

            posiciones[mayorastaelmoment] = cont
            copiadeparticipantes.remove(mayorastaelmoment).also { cont++ }

        }while (copiadeparticipantes.isNotEmpty())
    }


    fun determinarGanador(){
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


    }

    fun registrarAccion(vehiculo: String, accion: String){
        if (vehiculo in historialAcciones.keys){
            historialAcciones[vehiculo]?.add(accion)
        }else{
            historialAcciones[vehiculo] = mutableListOf()
            historialAcciones[vehiculo]?.add(accion)
        }
    }
}