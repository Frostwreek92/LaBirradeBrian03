
class Iniciar {
    fun iniciarPrograma() {
        println(variables.titulo)
        try {
            while (!variables.salirGeneral) {
                println(variables.menuInicio)
                val opcion = funciones.leerDato("Introduce una opción entre 1 y 6: ", Int::class.java, 0)
                when (opcion) {
                    1 -> opcionCervezas()
                    2 -> opcionTapas()
                    3 -> opcionProveedor()
                    4 -> opcionRegistro()
                    5 -> opcionAWS()
                    6 -> variables.salirGeneral = funciones.finEleccion()
                    else -> println("Introduce un número entre 1 y 6")
                }
            }
        } catch (e: Exception) {
            println("Excepcion: $e")
        }
        println(variables.finPrograma)
    }
    fun opcionCervezas() {
        variables.salirCervezas = false
        while (!variables.salirCervezas) {
            println(variables.menuCervezas)
            val opcion = funciones.leerDato("Introduce una opción entre 1 y 9: ", Int::class.java, 0)
            when (opcion) {
                1 -> imprimirCervezas()
                2 -> imprimirCervezaSeleccionada()
                3 -> escribirInsertarCerveza()
                4 -> escribirActualizarCerveza()
                5 -> eliminarCerveza()
                6 -> variables.salirCervezas = funciones.finEleccion()
                else -> println("\nIntroduce un número entre el 1 y 9")
            }
        }
    }
    fun opcionTapas() {
        variables.salirTapas = false
        while (!variables.salirTapas) {
            println(variables.menuTapas)
            val opcion = funciones.leerDato("Introduce una opción entre 1 y 9: ", Int::class.java, 0)
            when (opcion) {
                1 -> imprimirTapas()
                2 -> imprimirTapaSeleccionada()
                3 -> escribirInsertarTapa()
                4 -> escribirActualizarTapa()
                5 -> eliminarTapa()
                6 -> variables.salirTapas = funciones.finEleccion()
                else -> println("\nIntroduce un número entre el 1 y 9")
            }
        }
    }
    fun opcionProveedor() {
        variables.salirProveedor = false
        while (!variables.salirProveedor) {
            println(variables.menuProveedor)
            val opcion = funciones.leerDato("Introduce una opción entre 1 y 6: ", Int::class.java, 0)
            when (opcion) {
                1 -> imprimirProveedor()
                2 -> imprimirProveedorSeleccionado()
                3 -> escribirInsertarProveedor()
                4 -> escribirActualizarProveedor()
                5 -> eliminarProveedor()
                6 -> variables.salirProveedor = funciones.finEleccion()
                else -> println("\nIntroduce un número entre el 1 y 6")
            }
        }
    }
    fun opcionRegistro() {
        variables.salirRegistro = false
        while (!variables.salirRegistro) {
            println(variables.menuRegistro)
            val opcion = funciones.leerDato("Introduce una opción entre 1 y 4: ", Int::class.java, 0)
            when (opcion) {
                1 -> sumarStock()
                2 -> ventaStock()
                3 -> mostrarRegistro()
                4 -> variables.salirRegistro = funciones.finEleccion()
                else -> println("\nIntroduce un numero entre el 1 y el 4")
            }
        }
    }
    fun sumarStock() {
        variables.salirSumarStock = false
        while (!variables.salirSumarStock) {
            println(variables.menuSumarStock)
            val opcion = funciones.leerDato("Introduce una opción entre 1 y 4: ", Int::class.java, 0)
            when (opcion) {
                1 -> sumarCerveza()
                2 -> sumarTapa()
                3 -> sumarAmbas()
                4 -> variables.salirSumarStock = funciones.finEleccion()
                else -> println("\nIntroduce un numero entre el 1 y el 4")
            }
        }
    }
    fun ventaStock() {
        variables.salirVentaStock = false
        while (!variables.salirVentaStock) {
            println(variables.menuVentaStock)
            val opcion = funciones.leerDato("Introduce una opción entre 1 y 4: ", Int::class.java, 0)
            when (opcion) {
                1 -> ventaCerveza()
                2 -> ventaTapa()
                3 -> ventaAmbas()
                4 -> variables.salirVentaStock = funciones.finEleccion()
                else -> println("\nIntroduce un numero entre el 1 y el 4")
            }
        }
    }
    fun opcionAWS() {
        variables.salirOpcionesAWS = false
        while (!variables.salirOpcionesAWS) {
            println(variables.menuOpcionesAWS)
            val opcion = funciones.leerDato("Introduce una opción entre 1 y 8: ", Int::class.java, 0)
            when (opcion) {
                1 -> calcularTotalPrecioCervezaPorId()
                2 -> calcularTotalPrecioTapaPorId()
                3 -> mostrarStockTotal()
                4 -> sumarCervezasPorId()
                5 -> restarCervezaPorId()
                6 -> sumarTapasPorId()
                7 -> restarTapasPorId()
                8 -> variables.salirOpcionesAWS = funciones.finEleccion()
                else -> println("\nIntroduce un numero entre el 1 y el 8")
            }
        }
    }
}

