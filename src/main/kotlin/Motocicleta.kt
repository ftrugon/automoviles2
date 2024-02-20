class Motocicleta(marca: String,
                  modelo: String,
                  capacidadcombustible:Float,
                  val cilindrada :Int,
                  combustibleactual: Float,
                  kilometrosactuales: Int
):Vehiculo(marca,modelo,capacidadcombustible, combustibleactual, kilometrosactuales){


    override fun calcularautonomia(): Int {
        return super.calcularautonomia() * 2
    }

    override fun realizarviaje(distancia: Int): Int {
        val poderrecorrer = calcularautonomia()
        KM_por_L = 20
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

    fun realizarcaballito():Float{
        if (combustibleactual > 0.25){
            combustibleactual -= 0.25F
            return combustibleactual
        }else{
            println("No queda combustible como para hacer un caballito")
            return combustibleactual
        }
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Moto")}, cilindrada = $cilindrada)"
    }

}