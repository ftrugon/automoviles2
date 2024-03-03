import com.sun.jdi.Value

fun Float.redondear(posiciones:Int):Float{
    var elevar = 1
    for (i in 1..posiciones) elevar *= 10
    return (this * elevar).toInt().toFloat() / elevar
}

fun generarCoches(listaAletorios:List<Vehiculo>):MutableList<Vehiculo>{
    val listaDevolver = mutableListOf<Vehiculo>()

    print("Cuantos coches quieres?: ")
    var numero = readln().toIntOrNull()
    if (numero == null){
        println("Opcion no valida, se hara una carrera con solo 6 Vehiculos")
        numero = 6
    }


    var cont = 1
    while (cont <= numero){
        try {

            print("* Nombre del vehiculo $cont: ")

            val nombre = readln()

            val vehiculoAleatorio = listaAletorios.random()

            val vehiculoConNombre:Vehiculo = when (vehiculoAleatorio){
                is Camion -> {
                    Camion(
                        nombre,
                        vehiculoAleatorio.capacidadcombustible,
                        vehiculoAleatorio.combustibleactual,
                        vehiculoAleatorio.kilometrosactuales,
                        vehiculoAleatorio.peso
                    )
                }
                is Quad -> {
                    Quad(
                        nombre,

                        vehiculoAleatorio.capacidadcombustible,
                        vehiculoAleatorio.combustibleactual,
                        vehiculoAleatorio.kilometrosactuales,
                        vehiculoAleatorio.cilindrada,
                        vehiculoAleatorio.tipo
                    )
                }
                is Automovil -> {
                    Automovil(
                        nombre,
                        vehiculoAleatorio.marca,
                        vehiculoAleatorio.modelo,
                        vehiculoAleatorio.capacidadcombustible,
                        vehiculoAleatorio.combustibleactual,
                        vehiculoAleatorio.kilometrosactuales,
                        vehiculoAleatorio.esHibrido
                    )
                }
                is Motocicleta -> {
                    Motocicleta(
                        nombre,
                        vehiculoAleatorio.marca,
                        vehiculoAleatorio.modelo,
                        vehiculoAleatorio.capacidadcombustible,
                        vehiculoAleatorio.combustibleactual,
                        vehiculoAleatorio.kilometrosactuales,
                        vehiculoAleatorio.cilindrada
                    )
                }

                else -> {
                    Automovil("a","a","a",30f,30f * 0.3f,0f,true)
                }
            }

            println("Te ha tocado: $vehiculoConNombre")
            listaDevolver.add(vehiculoConNombre)
            cont++

        }catch (e:Exception){
            println(e.message)
        }
    }


    return listaDevolver
}

fun main() {



    val listaAletorios = listOf<Vehiculo>(
        Automovil("Aleatorio1", "Seat", "Panda", 60f, 50f * 0.3f, 0f, true),
        Automovil("Aleatorio2", "BMW", "M8", 50f, 80f * 0.3f, 0f, false),
        Automovil("Aleatorio3", "Cintroen", "Sor", 40f, 70f * 0.3f, 0f, true),
        Automovil("Aleatorio4", "Renault", "Espacio", 30f, 60f * 0.3f, 0f, false),
        Camion("Aleatorio5", 150f, 150f * 0.3f, 0f, 7000),
        Camion("Aleatorio6", 130f, 130f * 0.3f, 0f, 5000),
        Camion("Aleatorio7", 110f, 110f * 0.3f, 0f, 3000),
        Camion("Aleatorio8", 90f, 90f * 0.3f, 0f, 1000),
        Motocicleta("Aleatorio9", "Derbi", "Motoreta", 30f, 30f * 0.3f, 0f, 900f),
        Motocicleta("Aleatorio10", "Honda", "Vital", 25f, 25f * 0.3f, 0f, 500f),
        Motocicleta("Aleatorio11", "Que", "Motoreta", 20f, 20f * 0.3f, 0f, 400f),
        Motocicleta("Aleatorio12", "Que", "Motoreta", 15f, 15f * 0.3f, 0f, 250f),
        Quad("Aleatorio13", 40f, 40f * 0.3f, 0f, 900f, TipoDeQuad.CUADRICICLOS_NO_LIGEROS),
        Quad("Aleatorio14", 35f, 35f * 0.3f, 0f, 500f, TipoDeQuad.CUADRICICLOS_NO_LIGEROS),
        Quad("Aleatorio15", 30f, 30f * 0.3f, 0f, 400f, TipoDeQuad.VEHICULOS_ESPECIALES),
        Quad("Aleatorio16", 25f, 25f * 0.3f, 0f, 250f, TipoDeQuad.CUADRICICLOS_LIGEROS)
    )

    val participantes = generarCoches(listaAletorios)


    val carrera = Carrera("Formula 1",1000F,participantes)

    carrera.iniciarcarrera()
    val listadoresultaddos = carrera.obtenerResultadosFinales()



    println("\nPODIO:")
    println()
    var cont = 1
    listadoresultaddos.forEach{
        println("$cont -> ${it.vehiculo.nombre} : ${it.kilometraje}")
        cont++
    }

    println()

    cont = 1
    listadoresultaddos.forEach {
        println("\n$cont -> ${it.vehiculo.nombre}")
        it.historialAcciones.forEach { println(it) }
        cont++
    }

}