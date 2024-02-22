open class Vehiculo(
    val nombre: String,
    private val marca: String,
    private val modelo: String,
    val capacidadcombustible:Float,
    var combustibleactual: Float,
    var kilometrosactuales: Float
){

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

    open fun obtenerKm_por_L():Float{
        return KM_por_L
    }

    fun obtenerinformacion():String{
        return "El vehiculo puede recorrer ${calcularautonomia()}km y le quedan ${combustibleactual}L"
    }

    open fun calcularautonomia(): Float {
        return combustibleactual * KM_por_L
    }

    open fun realizarviaje(distancia:Float):Float{
        val poderrecorrer = calcularautonomia()
        return if (poderrecorrer >=  distancia){

            this.combustibleactual -= (distancia / obtenerKm_por_L()).redondear(2)
            this.kilometrosactuales += distancia
            0F

        }else {

            val quequeda = distancia - poderrecorrer
            this.combustibleactual = 0F
            this.kilometrosactuales += distancia - quequeda
            quequeda

        }
    }


    fun repostar(cantidad:Float = 0F):Float{
        val capacidadMenosActual = (capacidadcombustible - combustibleactual).redondear(2)
        return if (cantidad <= 0F) {
            combustibleactual = capacidadcombustible.redondear(2)
            capacidadMenosActual

        } else if (cantidad < capacidadMenosActual) {
            combustibleactual += cantidad
            cantidad

        } else {
            combustibleactual = capacidadcombustible.redondear(2)
            capacidadMenosActual
        }
    }

    override fun toString(): String {
        return "Vehiculo (nombre, $nombre, marca = $marca, modelo = $modelo, capacidad = ${capacidadcombustible.redondear(2)}, combustibleactual = ${combustibleactual.redondear(2)}, kilomentros actuales = $kilometrosactuales)"
    }

}
