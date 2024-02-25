/**
 * Clase base que representa un vehículo.
 *
 * @property nombre El nombre del vehículo.
 * @property marca La marca del vehículo.
 * @property modelo El modelo del vehículo.
 * @property capacidadcombustible La capacidad de combustible del vehículo en litros.
 * @property combustibleactual El nivel actual de combustible del vehículo en litros.
 * @property kilometrosactuales Los kilómetros actuales recorridos por el vehículo.
 */
open class Vehiculo(
    val nombre: String,
    private val marca: String,
    private val modelo: String,
    val capacidadcombustible:Float,
    var combustibleactual: Float,
    var kilometrosactuales: Float
){

    // Supongo que se puede hacer de otra forma pero esta es la mas sencilla de contar las paradas para repostar
    var paradasrepostajes = 0


    init {
        val nombresupper = mutableListOf<String>()
        nombre.forEach { nombresupper.add(it.uppercase()) }
        require(this.nombre.uppercase() !in nombresupper){"El nombre no puede estar repetido"}
        nombres.add(this.nombre)


        requirecosastring(nombre)
        requirecosastring(marca)
        requirecosastring(modelo)


        require(capacidadcombustible > 0) {"La capidad no puede ser menor a 0"}
        require(combustibleactual in 0F..capacidadcombustible) {"El combustible actual no puede ser mayor a la capaciddad y menor a 0"}
    }

    private fun requirecosastring(valorarequerir:String){
        require(valorarequerir.isNotEmpty()) {"El valor no puede estar vacio"}
    }

    companion object{
        const val KM_por_L = 10F
        val nombres = mutableListOf<String>()
    }

    /**
     * Obtiene la cantidad de kilómetros que el vehículo puede recorrer por litro de combustible.
     *
     * @return La cantidad de kilómetros por litro.
     */
    open fun obtenerKmporL():Float{
        return KM_por_L
    }

    /**
     * Obtiene información sobre el vehículo, incluyendo su autonomía restante y nivel de combustible.
     *
     * @return Una cadena con la información del vehículo.
     */
    fun obtenerinformacion():String{
        return "El vehiculo puede recorrer ${calcularautonomia()}km y le quedan ${combustibleactual}L"
    }

    /**
     * Calcula la autonomía restante del vehículo en kilómetros.
     *
     * @return La autonomía restante del vehículo en kilómetros.
     */
    open fun calcularautonomia(): Float {
        return combustibleactual * KM_por_L
    }

    /**
     * Realiza un viaje con el vehículo, avanzando una distancia específica y consumiendo combustible en consecuencia.
     *
     * @param distancia La distancia a recorrer en kilómetros.
     * @return La cantidad de kilómetros que el vehículo no pudo recorrer debido a la falta de combustible.
     */
    open fun realizarviaje(distancia:Float):Float{
        val poderrecorrer = calcularautonomia()
        return if (poderrecorrer >=  distancia){

            this.combustibleactual -= (distancia / obtenerKmporL()).redondear(2)
            this.kilometrosactuales += distancia
            0F

        }else {
            val quequeda = distancia - poderrecorrer
            this.combustibleactual = 0F
            this.kilometrosactuales += distancia - quequeda
            quequeda

        }
    }


    /**
     * Reposta combustible en el vehículo.
     *
     * @param cantidad La cantidad de combustible a repostar en litros. Si no se proporciona, se repostará hasta alcanzar la capacidad máxima.
     * @return La cantidad de combustible repostado en litros.
     */
    fun repostar(cantidad:Float = 0F):Float{
        val capacidadMenosActual = (capacidadcombustible - combustibleactual).redondear(2)
        return if (cantidad <= 0F) {
            combustibleactual = capacidadcombustible.redondear(2)
            paradasrepostajes++
            capacidadMenosActual

        } else if (cantidad < capacidadMenosActual) {
            combustibleactual += cantidad
            paradasrepostajes++
            cantidad

        } else {
            combustibleactual = capacidadcombustible.redondear(2)
            paradasrepostajes++
            capacidadMenosActual
        }

    }

    override fun toString(): String {
        return "Vehiculo (nombre, $nombre, marca = $marca, modelo = $modelo, capacidad = ${capacidadcombustible.redondear(2)}, combustibleactual = ${combustibleactual.redondear(2)}, kilomentros actuales = $kilometrosactuales)"
    }

}
