class Automovil(marca: String,
                modelo: String,
                capacidadcombustible:Float,
                val tipo :String,
                combustibleactual: Float,
                kilometrosactuales: Int
):Vehiculo(marca,modelo,capacidadcombustible,
    combustibleactual, kilometrosactuales
){

    companion object{
        const val KM_por_L = 10
    }

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Coche")}, tipo = $tipo,)"
    }

    override fun calcularautonomia(): Int {
        return super.calcularautonomia() + 100
    }

    override fun realizarviaje(distancia: Int): Int {
        return super.realizarviaje(distancia)
    }

    override fun repostar(cantidad: Float): Float {
        return super.repostar(cantidad)
    }
}
