open class Vehiculo(private val marca: String, private val modelo: String, val capacidadcombustible:Float){

    init {
        requirecosastring(marca)
        requirecosastring(modelo)
        require(capacidadcombustible > 0) {}
    }

    private fun requirecosastring(valorarequerir:String){
        require(valorarequerir.isNotEmpty()) {"El valor no puede estar vacio"}
    }


    fun obtenerinformacion():String{
        return "El vehiculo puede recorrer"
    }

    open fun calcularautonomia(): Float {
        return capacidadcombustible * 10
    }

    override fun toString(): String {
        return "Vehiculo (marca = $marca, modelo = $modelo, capacidad = $capacidadcombustible)"
    }



}

class Automovil(marca: String, modelo: String, capacidadcombustible:Float, val tipo :String):Vehiculo(marca,modelo,capacidadcombustible){

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Coche")}, tipo = $tipo)"
    }

    override fun calcularautonomia(): Float {
        return super.calcularautonomia() + 100
    }

}

class Motocicleta(marca: String, modelo: String, capacidadcombustible:Float, val cilindrada :Int):Vehiculo(marca,modelo,capacidadcombustible){

    override fun toString(): String {
        return "${super.toString().dropLast(1).replace("Vehiculo","Moto")}, cilindrada = $cilindrada)"
    }

    override fun calcularautonomia(): Float {
        return super.calcularautonomia() - 40
    }

}

fun main() {
    val coche = Vehiculo("asad","asdassad",2121F)
    println(coche)
    println(coche.calcularautonomia())

    val sedan = Automovil("sedan","holiwi",40F,"nose")
    println(sedan)
    println(sedan.calcularautonomia())

    val kawasaki = Motocicleta("kawasaki","kriko",12F,50)
    println(kawasaki)
    println(kawasaki.capacidadcombustible)
}




