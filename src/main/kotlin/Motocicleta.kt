class Motocicleta(marca: String,
                  modelo: String,
                  capacidadcombustible:Float,
                  val cilindrada :Int,
                  combustibleactual: Float,
                  kilometrosactuales: Float
):Vehiculo(marca,modelo,capacidadcombustible, combustibleactual, kilometrosactuales){

    init {
        require(cilindrada in 125..1000){"La cilindrada no puede ser mayor a 1000 y menor a 125"}
    }

    override fun obtenerKm_por_L(): Float {
        return 19 + (cilindrada.toFloat() / 1000)
    }

    fun realizarcaballito():Float{
        val litrosarestar = 6.5F / KM_por_L
        return if (combustibleactual > litrosarestar){
            combustibleactual -= litrosarestar
            combustibleactual.redondear(2)
        }else{
            println("No queda combustible como para hacer un caballito")
            combustibleactual.redondear(2)
        }
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Moto")}, cilindrada = $cilindrada)"
    }

}