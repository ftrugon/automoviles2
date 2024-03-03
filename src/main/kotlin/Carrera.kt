import kotlin.math.ceil
import kotlin.random.Random

/**
 * Clase que representa una carrera entre varios vehículos.
 * @property distanciaTotal La distancia total de la carrera en kilómetros.
 * @property participantes La lista de vehículos que participan en la carrera.
 */
class Carrera(
    nombreCarrera: String,
    private val distanciaTotal: Float,
    private val participantes: MutableList<Vehiculo>,

    ) {
    private var estadoCarrera: Boolean = false
    private var historialAcciones = mutableMapOf<String, MutableList<String>>()
    private var acabados = mutableListOf<Vehiculo>()


    init {
        require(nombreCarrera.isNotEmpty()) {"El nombre de la carrera no puede estar vacio"}
        require(distanciaTotal >= 1000) {"La distancia de la carrera tiene que ser mayor a 1000Km"}

    }


    companion object {
        const val KM_TRAMOS = 20f
    }

    /**
     * Inicia la carrera, permitiendo que los vehículos avancen hasta que uno de ellos llegue a la meta.
     */
    fun iniciarcarrera() {
        var ronda = 1
        estadoCarrera = true
        while (estadoCarrera) {

            val aleatorio = participantes.random()
            avanzarVehiculo(aleatorio)

            val acabado = vehiculoFinalizado()

            if ( acabado != null){
                participantes.remove(acabado)
                acabados.add(acabado)
            }

            if (!acabaron()){
                printearRonda(ronda)
            }
            ronda++
        }
    }


    fun acabaron():Boolean{
        if (participantes.isEmpty()){
            estadoCarrera = false
            return true
        }
        return false
    }

    fun printearRonda(ronda:Int){
        if (ronda % 5 == 0){
            println("\n*** CLASIFICACION PARCIAL (ronda $ronda) ***")
            val resultados = obtenerResultados()
            resultados.forEach{
                //No sabia como poner el tipo de las clases
                println("${it.posicion}. ${it.vehiculo.nombre} ${it.vehiculo::class.java.simpleName}(Km = ${it.vehiculo.kilometrosactuales}, Combustible = ${it.vehiculo.combustibleactual.redondear(2)}L)")
            }
        }
    }


    /**
     * Comprueba si la distancia supera la distancia de la carrera y actualiza la distancia.
     * @author DCS
     *
     * @return distancia de viaje que puede realizar el vehículo
     */
    private fun comprobarDistanciaMeta(vehiculo: Vehiculo, distancia: Float): Float {
        return if ((vehiculo.kilometrosactuales + distancia) > distanciaTotal) {
            distanciaTotal - vehiculo.kilometrosactuales
        } else {
            distancia
        }
    }


    /**
     * Avanza un vehículo a través de un tramo específico de la carrera, ajustando su combustible y kilometraje según
     * la distancia recorrida.
     * Realiza las operaciones necesarias para repostar si el combustible no es suficiente para completar el tramo.
     * @author DCS
     *
     * @param vehiculo El [Vehiculo] que avanza.
     * @param distancia La distancia del tramo a recorrer por el vehículo.
     */
    private fun avanzarTramo(vehiculo: Vehiculo, distancia: Float) {
        var distanciaNoRecorrida = vehiculo.realizarviaje(distancia)

        if (distanciaNoRecorrida == 0F) {
            registrarAccion(vehiculo.nombre, "Avance tramo: $distancia km")
        } else {
            registrarAccion(vehiculo.nombre, "Avance tramo: ${(distancia - distanciaNoRecorrida).redondear(2)} km")

            while (distanciaNoRecorrida > 0) {
                repostarVehiculo(vehiculo)

                //DCS: Necesitamos guardar la distancia restante original para después compararla con la distanciaNorecorrida que devuelve realizarViaje()
                val distanciaRestante = distanciaNoRecorrida
                distanciaNoRecorrida = vehiculo.realizarviaje(distanciaNoRecorrida)
                registrarAccion(vehiculo.nombre, "Avance tramo: ${(distanciaRestante - distanciaNoRecorrida).redondear(2)} km")
            }
        }
    }


    /**
     * Se encarga de hacer que los vehiculos se muevan una distancia aleatoria entre 10 y 200 km. Si el vehículo
     * necesita repostar, se llama al método repostarVehiculo() y tambien se llama a realizarfiligrana.
     * @param vehiculo es el vehiculo al que le ha tocado moverse
     */
    private fun avanzarVehiculo(vehiculo: Vehiculo){

        val kmAntesViaje = vehiculo.kilometrosactuales

        var distanciaViaje = Random.nextInt(10,201).toFloat()

        //DCS: Actualizar la distancia del viaje dependiendo de la distancia a meta
        distanciaViaje = comprobarDistanciaMeta(vehiculo, distanciaViaje)

        registrarAccion(vehiculo.nombre,"Iniciar viaje: A recorrer $distanciaViaje Kms (${vehiculo.kilometrosactuales.redondear(2)}kms y ${vehiculo.combustibleactual.redondear(2)}L actuales)")

        val totalTramos = ceil(distanciaViaje/KM_TRAMOS).toInt()

        for (i in 1..totalTramos) {
            val distanciaTramo = minOf(KM_TRAMOS, distanciaViaje).redondear(2)

            avanzarTramo(vehiculo, distanciaTramo)
            distanciaViaje -= distanciaTramo

            //DCS: Repite de 1 a 2 veces una filigrana...
            //Yo: Ahora puede no hacer una filigrana o hacer 3
            val numeroFiligranas = Random.nextInt(0, 4  )
            repeat(numeroFiligranas)
            {
                //DCS: Es mejor registrar la acción dentro del método realizarFiligrana(), te evitas otro if...
                realizarFiligrana(vehiculo)
            }
        }

        registrarAccion(vehiculo.nombre,"Fin viaje: ${vehiculo.kilometrosactuales - kmAntesViaje} Kms recorridos (${vehiculo.kilometrosactuales.redondear(2)}kms y ${vehiculo.combustibleactual.redondear(2)}L actuales)")
    }


    /**
     * Resposta el vehiculo llenando el combustible indicado, si no se indica el tanque se llena entero
     * @param vehiculo el vehiculo al que se le llenara el tanque
     * @param cantidad la cantidad de combustible a rellenar
     */
    private fun repostarVehiculo(vehiculo: Vehiculo, cantidad: Float = 0F){
        val litrosRepostados = vehiculo.repostar(cantidad)
        //DCS: Es mejor registrar la acción en el método que lo realiza... es más claro.
        registrarAccion(vehiculo.nombre,"Se han repostado ${litrosRepostados}L")
    }

    /**
     * Realiza una filigrana al vehiculo, si es una moto un caballito y si es un coche un derrape
     * @param vehiculo el vehiculo que eraliza el derrape o la filigrana
     */
    private fun realizarFiligrana(vehiculo: Vehiculo){


        val distaciaRestar = Random.nextInt(1,6)
        vehiculo.kilometrosactuales -= distaciaRestar
        registrarAccion(vehiculo.nombre,"Se ha retrasado $distaciaRestar Kms en esa filigrana")

        if (vehiculo is Automovil) {
            vehiculo.realizarderrape()
            registrarAccion(vehiculo.nombre,"Derrape, quedan ${vehiculo.combustibleactual.redondear(2)}L")
        }else if (vehiculo is Motocicleta){
            vehiculo.realizarcaballito()
            registrarAccion(vehiculo.nombre,"Caballito, quedan ${vehiculo.combustibleactual.redondear(2)}L")
        }
    }

    /**
     * Comprueba si alguno de los corredores ha superado la longitud del campo
     */
    private fun vehiculoFinalizado():Vehiculo?{
        for (vehi in participantes){
            if (vehi.kilometrosactuales >= distanciaTotal){
                return vehi
            }
        }
        return null
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


    fun obtenerResultadosFinales():List<ResultadoCarrera>{
        val listaresultados = mutableListOf<ResultadoCarrera>()
        var cont = 1
        for (vehi in acabados.sortedByDescending { it.kilometrosactuales }){
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