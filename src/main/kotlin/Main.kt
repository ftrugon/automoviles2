fun Float.redondear(posiciones:Int):Float{
    var elevar = 1
    for (i in 1..posiciones) elevar *= 10
    return (this * elevar).toInt().toFloat() / elevar
}

fun main() {



    val coche1 = Automovil(true,"Nisan","pulsar",60F,"Nidea",12F,0F)
    println(coche1)
    coche1.realizarviaje(15F)
    println(coche1)

    //val moto1 = Motocicleta("kawasaki","kriko",30F,1000,20F,0F)
    //println(moto1)
    //moto1.realizarviaje(100.0F)
    //println(moto1)

}