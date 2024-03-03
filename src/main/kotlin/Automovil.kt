/**
 * Clase que representa un automóvil.
 *
 * @property nombre El nombre del automóvil.
 * @property marca La marca del automóvil.
 * @property modelo El modelo del automóvil.
 * @property capacidadcombustible La capacidad de combustible del automóvil en litros.
 * @property combustibleactual El nivel actual de combustible del automóvil en litros.
 * @property kilometrosactuales Los kilómetros actuales recorridos por el automóvil.
 * @property esHibrido Indica si el automóvil es híbrido.
 */
open class Automovil(
    nombre: String,
    marca: String,
    modelo: String,
    capacidadcombustible: Float,
    combustibleactual: Float,
    kilometrosactuales: Float,
    val esHibrido: Boolean
) : Vehiculo(nombre, marca, modelo, capacidadcombustible, combustibleactual, kilometrosactuales) {


    init {
        requirecapacidadd()
    }

    companion object {
        var condicionBritanica: Boolean = false
    }

    open fun requirecapacidadd(){
        require(capacidadcombustible in 30f..60f){"La capacidad debe estar entre 30 y 60 Litros"}
    }

    /**
     * Obtiene la cantidad de kilómetros que el automóvil puede recorrer por litro de combustible,
     * teniendo en cuenta si es híbrido.
     *
     * @return La cantidad de kilómetros por litro.
     */
    override fun obtenerKmporL(): Float {
        return if (esHibrido) KM_por_L + 5 else KM_por_L
    }

    /**
     * Realiza un derrape con el automóvil, disminuyendo el nivel de combustible consumido.
     *
     * @return El nivel de combustible restante después del derrape.
     */
    fun realizarderrape(): Float {
        if (combustibleactual >= (7.5 / 10)) {
            if (esHibrido) {
                val litrosarestar = 6.25 / obtenerKmporL()
                combustibleactual -= litrosarestar.toFloat()
                combustibleactual.redondear(2)
            } else {
                val litrosarestar = 7.5 / obtenerKmporL()
                combustibleactual -= litrosarestar.toFloat()
                combustibleactual.redondear(2)
            }
        }
        return combustibleactual.redondear(2)
    }

    /**
     * Cambia la condición británica del automóvil.
     *
     * @param nuevacondicio La nueva condición británica.
     */
    fun cambiarCondicionBritanica(nuevacondicio: Boolean) {
        condicionBritanica = nuevacondicio
    }

    /**
     * Devuelve una representación en cadena del automóvil, cambiando el nombre de la clase a "Coche".
     *
     * @return Una representación en cadena del automóvil.
     */
    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo", "Coche")})"
    }
}
