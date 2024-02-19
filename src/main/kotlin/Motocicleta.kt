class Motocicleta(marca: String,
                  modelo: String,
                  capacidadcombustible:Float,
                  val cilindrada :Int,
                  combustibleactual: Float,
                  kilometrosactuales: Int
):Vehiculo(marca,modelo,capacidadcombustible, combustibleactual, kilometrosactuales){

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Moto")}, cilindrada = $cilindrada)"
    }

    override fun calcularautonomia(): Int {
        return super.calcularautonomia() - 40
    }

}