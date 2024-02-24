import kotlin.reflect.KMutableProperty

class Automovil(
    nombre:String,
    marca: String,
    modelo: String,
    capacidadcombustible:Float,
    combustibleactual: Float,
    kilometrosactuales: Float,
    private val esHibrido:Boolean
):Vehiculo(nombre,marca,modelo,capacidadcombustible, combustibleactual, kilometrosactuales
){

    companion object{
        var condicionBritanica:Boolean = false
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Coche")})"
    }

    fun cambiarCondicionBritanica(nuevacondicio:Boolean){
        condicionBritanica = nuevacondicio
    }

    override fun obtenerKm_por_L(): Float {
        return if (esHibrido) KM_por_L + 5 else KM_por_L
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
