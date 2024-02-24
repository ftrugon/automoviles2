import kotlin.math.ceil
import kotlin.math.floor
import kotlin.random.Random

class Carrera(
    val nombreCarrera: String,
    val distanciaTotal: Float,
    val participantes: List<Vehiculo>,

) {
    var estadoCarrera: Boolean = false
    var historialAcciones = mutableMapOf<String, MutableList<String>>()
    var posiciones =  mutableMapOf<Vehiculo, Int>()
    val ultimosectorrecorridoporvehiculo = mutableMapOf<Vehiculo,Float>()

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

    fun avanzarVehiculo(vehiculo: Vehiculo){


        val distacialeatoria = (Random.nextInt(1,201)).toFloat()
        registrarAccion(vehiculo.nombre,"Inicia viaje: A recorrer ${distacialeatoria}Kms (${vehiculo.kilometrosactuales}Kms y ${vehiculo.combustibleactual}L actuales)")


        var distacialeatoriamasultimo = distacialeatoria

        while (distacialeatoriamasultimo > 0) {
            val distanciaRecorrer = minOf(20F, distacialeatoriamasultimo)

            val aleatoriedad = Random.nextInt(1, 3)
            if (aleatoriedad == 1) {
                realizarFiligrana(vehiculo)
                registrarAccion(vehiculo.nombre, "Derrape: Combustible restante ${vehiculo.combustibleactual.redondear(2)}")
            } else {
                val combustibleConsumido = vehiculo.realizarviaje(distanciaRecorrer)
                registrarAccion(vehiculo.nombre, "Avance tramo: recorridos $distanciaRecorrer Kms")
                distacialeatoriamasultimo -= combustibleConsumido
            }

            if (vehiculo.combustibleactual <= 0) {
                repostarVehiculo(vehiculo)
                registrarAccion(vehiculo.nombre, "Repostaje: ${vehiculo.capacidadcombustible}")
            }
        }

        //for (i in 1..ceil(distacialeatoriamasultimo/20).toInt()) {
//
        //    val distanciarecorrida = minOf(20F,distacialeatoriamasultimo)
//
        //    if (distanciarecorrida >= 20F){
//
        //        val aleatoriedad = Random.nextInt(1,3)
        //        if (aleatoriedad == 1) {
        //            realizarFiligrana(vehiculo)
        //            registrarAccion(vehiculo.nombre,"Derrape: Combustible restante ${vehiculo.combustibleactual.redondear(2)}")
//
        //        }else if(aleatoriedad == 2){
        //            vehiculo.realizarviaje(20F)
        //            registrarAccion(vehiculo.nombre,"Avance tramo: recorridos 20 Kms")
        //            distacialeatoriamasultimo -= 20F
        //        }
        //    }else {
//
        //        val combustibleConsumido = vehiculo.realizarviaje(distanciarecorrida)
        //        registrarAccion(vehiculo.nombre, "Avance tramo: recorridos $distanciarecorrida Kms")
        //        distacialeatoriamasultimo -= combustibleConsumido
//
        //    }
//
        //    if (vehiculo.combustibleactual <= 0) {
        //        repostarVehiculo(vehiculo)
        //        registrarAccion(vehiculo.nombre, "Repostaje: ${vehiculo.capacidadcombustible}")
        //    }
        //}


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