import kotlin.reflect.KMutableProperty

class Automovil(val esElectrico:Boolean,
                marca: String,
                modelo: String,
                capacidadcombustible:Float,
                val tipo :String,
                combustibleactual: Float,
                kilometrosactuales: Int
):Vehiculo(marca,modelo,capacidadcombustible,
    combustibleactual, kilometrosactuales
){
    init {
        require(tipo.isNotEmpty()){"Tipo no puede estar vacio"}
    }

    companion object{
        var condicionBritanica:Boolean = false
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Coche")}, tipo = $tipo)"
    }

    override fun calcularautonomia(): Int {
        return if (esElectrico) super.calcularautonomia() + (super.calcularautonomia()/2) else super.calcularautonomia()
    }


    override fun realizarviaje(distancia: Int): Int {
        val poderrecorrer = calcularautonomia()

        KM_por_L = if (esElectrico) 15 else 10

        if (poderrecorrer >=  distancia){

            this.combustibleactual -= (distancia / KM_por_L.toFloat()).redondear(1)

            this.kilometrosactuales += distancia

            println("Se han recorrido todos los kilometros")

            return 0

        }else {

            val quequeda = distancia - poderrecorrer

            this.combustibleactual = 0F

            this.kilometrosactuales += distancia - quequeda

            println("Quedan algunos kilometros $quequeda")

            return quequeda
        }
    }


    fun cambiarCondicionBritanica(nuevacondicio:Boolean){
        condicionBritanica = nuevacondicio
    }

    fun realizarderrape():Float{
        if (esElectrico && combustibleactual > 0.33){
            combustibleactual -= 0.33F
            return 0.33F
        }else if (!esElectrico && combustibleactual > 0.33){
            combustibleactual -= 0.5F
            return 0.5F
        }else{
            println("No hay combustible como para hacer un derrape")
            return 0F
        }
    }
}
