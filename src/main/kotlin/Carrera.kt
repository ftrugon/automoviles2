class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Float,
    val participantes: List<Vehiculo>,
    val historialAcciones: MutableMap<String, MutableList<String>>,
    val posiciones: MutableMap<String, Int>
) {
    var estadoCarrera: Boolean = false

    fun iniciarcarrera() {
        estadoCarrera = true

    }

    fun avanzarVehiculo(vehiculo: Vehiculo){

    }

    fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float){

    }

    fun realizarFiligrana(vehiculo: Vehiculo){

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