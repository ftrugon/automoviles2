class Quad(
    nombre: String,
    capacidadcombustible: Float,
    combustibleactual: Float,
    kilometrosactuales: Float,
    cilindrada: Float,
    val tipo: TipoDeQuad
) : Motocicleta(nombre, "", "", capacidadcombustible, combustibleactual, kilometrosactuales, cilindrada) {

    init {
        requirecapacidadd()
    }

    override fun requirecapacidadd() {
        require(capacidadcombustible in 20f..40f){"La capacidad debe estar entre 20 y 40 Litros"}
    }

    override fun obtenerKmporL(): Float {
        return super.obtenerKmporL()/2
    }

    override fun toString(): String {
        return "Quad (Nombre = $nombre, Capacidad = $capacidadcombustible, combustible actual = ${combustibleactual.redondear(2)}, kilometrosactuales = ${kilometrosactuales.redondear(2)}, tipo = $tipo )"
    }

}