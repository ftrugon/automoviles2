/**
 * Clase que representa una motocicleta.
 *
 * @property nombre El nombre de la motocicleta.
 * @property marca La marca de la motocicleta.
 * @property modelo El modelo de la motocicleta.
 * @property capacidadcombustible La capacidad de combustible de la motocicleta en litros.
 * @property combustibleactual El nivel actual de combustible de la motocicleta en litros.
 * @property kilometrosactuales Los kilómetros actuales recorridos por la motocicleta.
 * @property cilindrada La cilindrada de la motocicleta en centímetros cúbicos.
 */
open class Motocicleta(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadcombustible: Float,
    combustibleactual: Float,
    kilometrosactuales: Float,
    val cilindrada: Float
) : Vehiculo(nombre, marca, modelo, capacidadcombustible, combustibleactual, kilometrosactuales) {

    init {
        val listaCilindradas = listOf(125, 250, 400, 500, 750, 900, 1000)
        require(cilindrada.toInt() in listaCilindradas) {"Las cilindradas solo pueden ser 125, 250, 400, 500, 750, 900 y 1000"}
        requirecapacidadd()
    }

    open fun requirecapacidadd() {
        require(capacidadcombustible in 15f..30f){"La capacidad debe estar entre 15 y 30 Litros"}
    }

    /**
     * Obtiene la cantidad de kilómetros que la motocicleta puede recorrer por litro de combustible,
     * teniendo en cuenta su cilindrada.
     *
     * @return La cantidad de kilómetros por litro.
     */
    override fun obtenerKmporL(): Float {
        return 19 + (cilindrada / 1000)
    }

    /**
     * Realiza un caballito con la motocicleta, disminuyendo el nivel de combustible consumido.
     *
     * @return El nivel de combustible restante después del caballito.
     */
    fun realizarcaballito(): Float {
        val litrosarestar = 6.5F / obtenerKmporL()
        return if (combustibleactual > litrosarestar){
            combustibleactual -= litrosarestar
            combustibleactual.redondear(2)
        } else {
            combustibleactual.redondear(2)
        }
    }

    /**
     * Devuelve una representación en cadena de la motocicleta, cambiando el nombre de la clase a "Moto".
     *
     * @return Una representación en cadena de la motocicleta.
     */
    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo", "Moto")}, cilindrada = $cilindrada)"
    }
}