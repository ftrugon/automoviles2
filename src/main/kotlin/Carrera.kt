import kotlin.random.Random

class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Float,
    val participantes: MutableList<Vehiculo>,

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
        //while (estadoCarrera) {
            val aleatorio = participantes.random()
            avanzarVehiculo(aleatorio)
            //Todo: actualizar posiciones
            actualizarPosiciones()
            //estadocarrera = determinar ganador

        //}
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
        val copiadeparticipantes: MutableList<Vehiculo> = participantes
        do {
            var mayorastaelmoment = participantes[0]
            for (vehicu in copiadeparticipantes){
                if (vehicu.kilometrosactuales > mayorastaelmoment.kilometrosactuales){
                    mayorastaelmoment = vehicu
                }
            }
            posiciones[mayorastaelmoment.nombre] = cont
            copiadeparticipantes.remove(mayorastaelmoment)
            cont++
            println(posiciones)
        }while (copiadeparticipantes.isNotEmpty())
    }

    fun determinarGanador(){
        for (vehi in participantes){
            if (vehi.kilometrosactuales >= distanciaTotal){

            }
        }
    }

    fun obtenerResultados(){

    }

    fun registrarAccion(vehiculo: String, accion: String){

    }
}