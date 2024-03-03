import kotlin.math.floor

class Camion(
    nombre: String,
    capacidadcombustible: Float,
    combustibleactual: Float,
    kilometrosactuales: Float,
    val peso: Int
):Automovil(nombre, "", "", capacidadcombustible, combustibleactual, kilometrosactuales, false){


    init {
        requirecapacidadd()
        require(peso in 1000..10000){"El peso no puede ser menor a 1000 ni mayor a 10000"}
    }

    override fun requirecapacidadd() {
        require(capacidadcombustible in 90f..150f){"La capacidad debe estar entre 90 y 150 Litros"}
    }

    override fun obtenerKmporL(): Float {
        var KmPorL= 100F/16F
        val KmARestar = floor(peso.toDouble()/1000).toInt()
        for (i in 1..KmARestar){
            KmPorL -= 0.2F
        }
        return KmPorL
    }

    override fun toString(): String {
        return "Camion (Nombre = $nombre, Capacidad = $capacidadcombustible, combustible actual = ${combustibleactual.redondear(2)}, kilometrosactuales = ${kilometrosactuales.redondear(2)}, peso = $peso)"
    }
}