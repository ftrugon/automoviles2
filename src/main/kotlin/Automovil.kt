import kotlin.reflect.KMutableProperty

class Automovil(val esHibrido:Boolean,
                marca: String,
                modelo: String,
                capacidadcombustible:Float,
                val tipo :String,
                combustibleactual: Float,
                kilometrosactuales: Float
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

    override fun calcularautonomia(): Float {
        KM_por_L = if (esHibrido) 15 else 10
        return super.calcularautonomia()
    }


    fun cambiarCondicionBritanica(nuevacondicio:Boolean){
        condicionBritanica = nuevacondicio
    }

    fun realizarderrape():Float{
        return if (combustibleactual >=  (7.5/10)){
            if (esHibrido){
                val litrosarestar = 7.5/ KM_por_L
                combustibleactual -= litrosarestar.toFloat()
                combustibleactual.redondear(2)
            }else{
                val litrosarestar = 7.5 / KM_por_L
                combustibleactual -= litrosarestar.toFloat()
                combustibleactual.redondear(2)
            }
        }else {
            println("No hay combustible como para derrapar")
            combustibleactual.redondear(2)
        }
    }

}
