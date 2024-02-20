class Motocicleta(marca: String,
                  modelo: String,
                  capacidadcombustible:Float,
                  val cilindrada :Int,
                  combustibleactual: Float,
                  kilometrosactuales: Float
):Vehiculo(marca,modelo,capacidadcombustible, combustibleactual, kilometrosactuales){

    override fun calcularautonomia(): Float {
        KM_por_L = 20
        return super.calcularautonomia()
    }

    fun realizarcaballito():Float{
        return if (combustibleactual > 0.25){
            combustibleactual -= 0.25F
            combustibleactual
        }else{
            println("No queda combustible como para hacer un caballito")
            combustibleactual
        }
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Moto")}, cilindrada = $cilindrada)"
    }

}