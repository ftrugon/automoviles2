fun Float.redondear(posiciones:Int):Float{
    var elevar = 1
    for (i in 1..posiciones) elevar *= 10
    return (this * elevar).toInt().toFloat() / elevar
}

fun main() {
    val listacoches = listOf<Vehiculo>(
        Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0f, true), // Coche eléctrico con capacidad de 50 litros, inicia con el 10%
        Automovil("Boreal", "BMW", "M8", 80f, 80f * 0.1f, 0f, false), // SUV híbrido con capacidad de 80 litros, inicia con el 10%
        Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0f, 500F), // Motocicleta de gran cilindrada con capacidad de 15 litros, inicia con el 10%
        Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0f, true), // Camioneta eléctrica con capacidad de 70 litros, inicia con el 10%
        Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0f, false), // Coupé deportivo con capacidad de 60 litros, inicia con el 10%
        Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 6f, 250F) // Motocicleta eléctrica con capacidad de 20 litros, inicia con el 10%

    )

    val carrera = Carrera("Formula 1",2000F,listacoches)

    carrera.iniciarcarrera()
    val listadoresultaddos = carrera.obtenerResultados()

    println("PODIO:")
    var cont = 1
    listadoresultaddos.forEach{
        println("$cont -> ${it.vehiculo.nombre}")
        cont++
    }

    cont = 1
    listadoresultaddos.forEach{
        println("$cont -> ${it.vehiculo.nombre}")
        it.historialAcciones.forEach{ println(it)}
        cont++
    }

    //val moto1 = Motocicleta("AyO","kawasaki","kriko",30F,20F,0F,220F)
    //println(moto1)
    //moto1.realizarviaje(100.0F)
    //println(moto1)
    //moto1.realizarcaballito()
    //moto1.realizarcaballito()
    //moto1.realizarcaballito()
    //println(moto1)
//
//
    //val coche1 = Automovil("aayer","kowalski","opciones",50F,50F,0F,false)
    //println(coche1)
    //coche1.realizarviaje(100F)
    //println(coche1)
    //coche1.realizarderrape()
    //coche1.realizarderrape()
    //coche1.realizarderrape()
    //println(coche1)


}