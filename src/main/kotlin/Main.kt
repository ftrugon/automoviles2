fun Float.redondear(posiciones:Int):Float{
    var elevar = 1
    for (i in 1..posiciones) elevar *= 10
    return (this * elevar).toInt().toFloat() / elevar
}

fun main() {



    //val sedan = Vehiculo("sedan","holiwi",40F,15F,2)
//
    //println(sedan)
    //sedan.realizarviaje(180)
    //println(sedan)
    //sedan.repostar(21.4F)
    //println(sedan)
    //sedan.realizarviaje(183)
    //println(sedan)

    val coche1 = Automovil(true,"Nisan","pulsar",60F,"Nidea",12F,0F)
    println(coche1)
    coche1.realizarviaje(15.2F)
    println(coche1)

    val moto1 = Motocicleta("kawasaki","kriko",30F,125,5F,0F)
    println(moto1)
    moto1.realizarviaje(20.0F)
    println(moto1)
}




