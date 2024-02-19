open class Vehiculo(private val marca: String,
                        private val modelo: String,
                        val capacidadcombustible:Float,
                        var combustibleactual: Float,
                        var kilometrosactuales: Int
){

    init {
        requirecosastring(marca)
        requirecosastring(modelo)
        require(capacidadcombustible > 0) {"La capidad no puedeser menor a 0"}
    }

    private fun requirecosastring(valorarequerir:String){
        require(valorarequerir.isNotEmpty()) {"El valor no puede estar vacio"}
    }

    companion object{
        const val KM_por_L = 10
    }


    fun obtenerinformacion():String{
        return "El vehiculo puede recorrer ${calcularautonomia()}km y le quedan ${combustibleactual}L"
    }

    open fun calcularautonomia(): Int {
        return (combustibleactual * KM_por_L).toInt()
    }

    open fun realizarviaje(distancia:Int):Int{
        val poderrecorrer = calcularautonomia()
        if (poderrecorrer >=  distancia){

            this.combustibleactual -= distancia / KM_por_L

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


    open fun repostar(cantidad:Float = 0F):Float{
        if (cantidad == 0F) {
            val arepostar = capacidadcombustible - combustibleactual
            combustibleactual = capacidadcombustible
            return arepostar

        }else if (cantidad < (capacidadcombustible - combustibleactual)){
            val arepostar = (capacidadcombustible - combustibleactual) - cantidad
            combustibleactual += cantidad
            println("Se tienen que repostar $arepostar L")
            return arepostar
        }else{
            val arepostar = capacidadcombustible - combustibleactual
            combustibleactual = capacidadcombustible
            println("Se tienen que repostar $arepostar L")
            return arepostar
        }
    }

    override fun toString(): String {
        return "Vehiculo (marca = $marca, modelo = $modelo, capacidad = $capacidadcombustible, combustibleactual = $combustibleactual, kilomentros actuales = $kilometrosactuales)"
    }

}
