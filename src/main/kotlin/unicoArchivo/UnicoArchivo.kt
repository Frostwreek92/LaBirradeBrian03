package unicoArchivo

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.use

fun main() {
    iniciar.iniciarPrograma()
}

// Variables
class Variables {
    // Cosas Main
    val titulo = "La Birra de Brian: La Venganza es Dulce"
    var salirGeneral = false
    var salirCervezas = false
    var salirTapas = false
    var salirProveedor = false
    var salirRegistro = false
    var salirSumarStock = false
    var salirVentaStock = false
    val finPrograma = "\nFin del programa"
    // Menús
    val menuInicio = "\n" + """Menú Principal:
        1. Operaciones con Cervezas
        2. Operaciones con Tapas
        3. Operaciones con Proveedor
        4. Operaciones de Stock
        5. Salir
    """.trimIndent()
    val menuCervezas = "\n" + """Menú Cervezas:
        1. Listar Cervezas actuales
        2. Consultar Cerveza por ID
        3. Insertar Cerveza nueva
        4. Actualizar Cerveza por ID
        5. Eliminar Cerveza por ID
        6. Volver
    """.trimIndent()
    val menuTapas = "\n" + """Menú Tapas:
        1. Listar Tapas actuales
        2. Consultar Tapas por ID
        3. Insertar Tapas nueva
        4. Actualizar Tapas por ID
        5. Eliminar Tapa por ID
        6. Volver
    """.trimIndent()
    val menuProveedor = "\n" + """Menu Proveedor:
        1. Listar Proveedores
        2. Consultar Proveedor por ID
        3. Insertar Proveedor nuevo
        4. Actualizar Proveedor por ID
        5. Eliminar Proveedor por ID
        6. Volver
    """.trimIndent()
    val menuRegistro = "\n" + """Menú Almacén:
        1. Sumar stock
        2. Venta de Artículos
        3. Mostrar Registro
        4. Volver
    """.trimIndent()
    val menuSumarStock = "\n" + """Menú para sumar stock:
        1. Sumar Cervezas
        2. Sumar Tapas
        3. Sumar ambos a la vez
        4. Volver
    """.trimIndent()
    val menuVentaStock = "\n" + """Menú de venta stock:
        1. Vender Cerveza
        2. Vender Tapa
        3. Vender ambos a la vez
        4. Volver
    """.trimIndent()
    // Ruta del archivo de BD SQLite
    val nombreArchivoSQLITE = "LaBirradeBrian.sqlite"
    val urlBD = "jdbc:sqlite:src/main/resources/$nombreArchivoSQLITE"
    // Query de las funciones
    // Cervezas
    val queryListarCervezas = "SELECT * FROM Cervezas"
    val queryConsultarCervezasPorId = "SELECT * FROM Cervezas WHERE idCerveza = ?"
    val queryInsertarCerveza = "INSERT INTO Cervezas(nombreCerveza, " +
            "graduacionCerveza, " +
            "tipoCerveza, " +
            "colorCerveza, " +
            "origenCerveza, " +
            "puntuacionCerveza, " +
            "cantidadCerveza) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)"
    val queryActualizarCerveza = "UPDATE Cervezas SET (nombreCerveza, " +
            "graduacionCerveza, " +
            "tipoCerveza, " +
            "colorCerveza, " +
            "origenCerveza, " +
            "puntuacionCerveza, " +
            "cantidadCerveza) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)"
    val queryEliminarCerveza = "DELETE FROM Cervezas WHERE idCerveza = ?"
    // Tapas
    val queryListarTapas = "SELECT * FROM Tapas"
    val queryConsultarTapasPorId = "SELECT * FROM Tapas WHERE idTapa = ?"
    val queryInsertarTapa = "INSERT INTO Tapas(nombreTapa, " +
            "precioTapa, " +
            "cantidadTapa) " +
            "VALUES (?, ?, ?)"
    val queryActualizarTapa = "UPDATE Tapas SET (nombreTapa, " +
            "precioTapa, " +
            "cantidadTapa) " +
            "VALUES (?, ?, ?)"
    val queryEliminarTapa = "DELETE FROM Tapas WHERE idTapa = ?"
    // Proveedor
    val queryListarProveedores = "SELECT * FROM Proveedores"
    val queryConsultarProveedorPorId = "SELECT * FROM Proveedores WHERE idProveedor = ?"
    val queryInsertarProveedor = "INSERT INTO Proveedores(nombreProveedor) VALUES (?)"
    val queryActualizarProveedor = "UPDATE Proveedores SET (nombreProveedor) VALUES (?)"
    val queryEliminarProveedor = "DELETE FROM Proveedores WHERE idProveedor = ?"
    // Registro
    val queryListarRegistro = "SELECT * FROM Registro"
    // Textos Varios
    val textoListaCervezas = "\nLista de Cervezas"
    val textoIntroducirCervezaID = "\nIntroduce ID de Cerveza: "
    val textoListaTapas = "\nLista de Tapas"
    val textoIntroducirTapaID = "\nIntroduce ID de Tapa: "
    val textoListaProveedor = "\nLista de Proveedores: "
    val textoIntroducirProveedorID = "\nIntroduce ID de Proveedor"
    val textoListaRegistros = "\nRegistro:"
    val textoIdBorrar = "\nIntroduce ID a Borrar: "
    val noConexion = "\nNo se ha podido establecer la conexión."
}
// Variables para usar clases
val variables = Variables()
val iniciar = Iniciar()

// Inicio del programa
class Iniciar {
    fun iniciarPrograma() {
        println(variables.titulo)
        try {
            while (!variables.salirGeneral) {
                println(variables.menuInicio)
                val opcion = leerDato("Introduce una opción entre 1 y 5: ", Int::class.java, 0)
                when (opcion) {
                    1 -> opcionCervezas()
                    2 -> opcionTapas()
                    3 -> opcionProveedor()
                    4 -> opcionRegistro()
                    5 -> variables.salirGeneral = finEleccion()
                    else -> println("Introduce un número entre 1 y 5")
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
            val opcion = leerDato("Introduce una opción entre 1 y 6: ", Int::class.java, 0)
            when (opcion) {
                1 -> imprimirCervezas()
                2 -> imprimirCervezaSeleccionada()
                3 -> escribirInsertarCerveza()
                4 -> escribirActualizarCerveza()
                5 -> eliminarCerveza()
                6 -> variables.salirCervezas = finEleccion()
                else -> println("\nIntroduce un número entre el 1 y 6")
            }
        }
    }
    fun opcionTapas() {
        variables.salirTapas = false
        while (!variables.salirTapas) {
            println(variables.menuTapas)
            val opcion = leerDato("Introduce una opción entre 1 y 6: ", Int::class.java, 0)
            when (opcion) {
                1 -> imprimirTapas()
                2 -> imprimirTapaSeleccionada()
                3 -> escribirInsertarTapa()
                4 -> escribirActualizarTapa()
                5 -> eliminarTapa()
                6 -> variables.salirTapas = finEleccion()
                else -> println("\nIntroduce un número entre el 1 y 6")
            }
        }
    }
    fun opcionProveedor() {
        variables.salirProveedor = false
        while (!variables.salirProveedor) {
            println(variables.menuProveedor)
            val opcion = leerDato("Introduce una opción entre 1 y 6: ", Int::class.java, 0)
            when (opcion) {
                1 -> imprimirProveedor()
                2 -> imprimirProveedorSeleccionado()
                3 -> escribirInsertarProveedor()
                4 -> escribirActualizarProveedor()
                5 -> eliminarProveedor()
                6 -> variables.salirProveedor = finEleccion()
                else -> println("\nIntroduce un número entre el 1 y 6")
            }
        }
    }
    fun opcionRegistro() {
        variables.salirRegistro = false
        while (!variables.salirRegistro) {
            println(variables.menuRegistro)
            val opcion = leerDato("Introduce una opción entre 1 y 4: ", Int::class.java, 0)
            when (opcion) {
                1 -> sumarStock()
                2 -> ventaStock()
                3 -> mostrarRegistro()
                4 -> variables.salirRegistro = finEleccion()
                else -> println("\nIntroduce un numero entre el 1 y el 4")
            }
        }
    }
    fun sumarStock() {
        variables.salirSumarStock = false
        while (!variables.salirSumarStock) {
            println(variables.menuSumarStock)
            val opcion = leerDato("Introduce una opción entre 1 y 4: ", Int::class.java, 0)
            when (opcion) {
                1 -> sumarCerveza()
                2 -> sumarTapa()
                3 -> sumarAmbas()
                4 -> variables.salirSumarStock = finEleccion()
                else -> println("\nIntroduce un numero entre el 1 y el 4")
            }
        }
    }
    fun ventaStock() {
        variables.salirVentaStock = false
        while (!variables.salirVentaStock) {
            println(variables.menuVentaStock)
            val opcion = leerDato("Introduce una opción entre 1 y 4: ", Int::class.java, 0)
            when (opcion) {
                1 -> ventaCerveza()
                2 -> ventaTapa()
                3 -> ventaAmbas()
                4 -> variables.salirVentaStock = finEleccion()
                else -> println("\nIntroduce un numero entre el 1 y el 4")
            }
        }
    }
}

// Función para conectar a la base de datos
fun getConnection(): Connection? {
    return try {
        DriverManager.getConnection(variables.urlBD)
    } catch (e: SQLException) {
        e.printStackTrace()
        null
    }
}
// Fin para cualquier when
fun finEleccion(): Boolean {
    return true
}


/* Función para ahorrar líneas que se parece a Python al pedir datos
* En la que puedes escribir un mensaje a mostrar*/
fun input(mensaje: String): String? {
    print(mensaje)
    return readlnOrNull()
}
// Función genérica para pedir datos seguros
/* Formato de escritura:
* "Mensaje entre comillas que mostrará", Int.String.Double::class.java, valor por defecto*/
@Suppress("Unchecked_cast") // Elimina los warnings de las T
fun <T> leerDato(mensaje: String, tipo: Class<T>, valorPorDefecto: T? = null): T {
    while (true) {
        val inputUsuario = input(mensaje)
        try {
            return when (tipo) {
                String::class.java -> (inputUsuario ?: valorPorDefecto ?: "") as T
                Int::class.java -> (inputUsuario?.toIntOrNull() ?: throw Exception("No es un número entero")) as T
                Double::class.java -> (inputUsuario?.toDoubleOrNull() ?: throw Exception("No es un número válido")) as T
                else -> throw Exception("Tipo no soportado")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}. Por favor, introduce un valor válido.")
        }
    }
}

// CervezasDAO
data class Cerveza(
    val idCerveza: Int? = null,
    val nombreCerveza: String,
    val graduacionCerveza: Double,
    val tipoCerveza: String,
    val colorCerveza: String,
    val origenCerveza: String,
    val puntuacionCerveza: Double,
    val cantidadCerveza: Int
)
object CervezasDAO {
    // Listar Cervezas
    fun listarCervezas(): List<Cerveza> {
        val lista = mutableListOf<Cerveza>()
        getConnection()?.use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery(variables.queryListarCervezas).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Cerveza(
                                idCerveza = rs.getInt("idCerveza"),
                                nombreCerveza = rs.getString("nombreCerveza"),
                                graduacionCerveza = rs.getDouble("graduacionCerveza"),
                                tipoCerveza = rs.getString("tipoCerveza"),
                                colorCerveza = rs.getString("colorCerveza"),
                                origenCerveza = rs.getString("origenCerveza"),
                                puntuacionCerveza = rs.getDouble("puntuacionCerveza"),
                                cantidadCerveza = rs.getInt("cantidadCerveza")
                            )
                        )
                    }
                }
            }
        } ?: println(variables.noConexion)
        return lista
    }
    // Consultar Cerveza por ID
    fun consultarCervezaPorId(id: Int): Cerveza? {
        var cerveza: Cerveza? = null
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryConsultarCervezasPorId).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        cerveza = Cerveza(
                            idCerveza = rs.getInt("idCerveza"),
                            nombreCerveza = rs.getString("nombreCerveza"),
                            graduacionCerveza = rs.getDouble("graduacionCerveza"),
                            tipoCerveza = rs.getString("tipoCerveza"),
                            colorCerveza = rs.getString("colorCerveza"),
                            origenCerveza = rs.getString("origenCerveza"),
                            puntuacionCerveza = rs.getDouble("puntuacionCerveza"),
                            cantidadCerveza = rs.getInt("cantidadCerveza")
                        )
                    }
                }
            }
        } ?: println(variables.noConexion)
        return cerveza
    }
    // Insertar Cerveza
    fun insertarCerveza(cerveza: Cerveza) {
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryInsertarCerveza).use { stmt ->
                stmt.setString(1, cerveza.nombreCerveza)
                stmt.setDouble(2, cerveza.graduacionCerveza)
                stmt.setString(3, cerveza.tipoCerveza)
                stmt.setString(4, cerveza.colorCerveza)
                stmt.setString(5, cerveza.origenCerveza)
                stmt.setDouble(6, cerveza.puntuacionCerveza)
                stmt.setInt(7, cerveza.cantidadCerveza)
                stmt.executeUpdate()
                println("\nCerveza '${cerveza.nombreCerveza}' insertada con éxito")
            }
        } ?: println(variables.noConexion)
    }
    // Actualizar Cerveza
    fun actualizarCerveza(cerveza: Cerveza) {
        if (cerveza.idCerveza == null) {
            println("\nNo se puede actualizar la cerveza sin un ID.")
            return
        }
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryActualizarCerveza).use { stmt ->
                stmt.setString(1, cerveza.nombreCerveza)
                stmt.setDouble(2, cerveza.graduacionCerveza)
                stmt.setString(3, cerveza.tipoCerveza)
                stmt.setString(4, cerveza.colorCerveza)
                stmt.setString(5, cerveza.origenCerveza)
                stmt.setDouble(6, cerveza.puntuacionCerveza)
                stmt.setInt(7, cerveza.cantidadCerveza)
                val filas = stmt.executeUpdate()
                if (filas > 0) {
                    println("\nCerveza con ID = ${cerveza.idCerveza} actualizada con éxito.")
                } else {
                    println("\nNo se encontró ninguna cerveza con el ID = ${cerveza.idCerveza}")
                }
            }
        } ?: println(variables.noConexion)
    }
    // Eliminar Cerveza
    fun eliminarCerveza(id: Int) {
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryEliminarCerveza).use { stmt ->
                stmt.setInt(1, id)
                val filas = stmt.executeUpdate()
                if (filas > 0) {
                    println("\nCerveza con id = $id eliminada correctamente.")
                } else {
                    println("\nNo se encontró ninguna cerveza con id = $id.")
                }
            }
        } ?: println(variables.noConexion)
    }
}
fun imprimirCervezas() {
    println(variables.textoListaCervezas)
    CervezasDAO.listarCervezas().forEach {
        println("- [${it.idCerveza}], " +
                "${it.nombreCerveza}, " +
                "${it.graduacionCerveza}%, " +
                "${it.tipoCerveza}, " +
                "${it.colorCerveza}, " +
                "${it.origenCerveza}, " +
                "${it.puntuacionCerveza}*, " +
                "${it.cantidadCerveza}")
    }
}
fun imprimirCervezaSeleccionada() {
    imprimirCervezas()
    val id = leerDato(variables.textoIntroducirCervezaID,
        Int::class.java,
        0)
    val cerveza = CervezasDAO.consultarCervezaPorId(id)
    if (cerveza != null) {
        println("\nCerveza encontrada:\n[${cerveza.idCerveza}], " +
                "Nombre: ${cerveza.nombreCerveza}, " +
                "Graduación: ${cerveza.graduacionCerveza}%, " +
                "Tipo: ${cerveza.tipoCerveza}, " +
                "Color: ${cerveza.colorCerveza}, " +
                "Origen: ${cerveza.origenCerveza}, " +
                "Puntuación: ${cerveza.puntuacionCerveza}, " +
                "Cantidad: ${cerveza.cantidadCerveza}")
    } else {
        println("No se encontró ninguna cerveza con ese ID.")
    }
}
fun escribirInsertarCerveza() {
    CervezasDAO.insertarCerveza(
        Cerveza(
            nombreCerveza = leerDato("Introduce nombre: ", String::class.java, "Default"),
            graduacionCerveza = leerDato("Introduce graduación: ", Double::class.java, 0.0),
            tipoCerveza = leerDato("Introduce tipo: ", String::class.java, "Default"),
            colorCerveza = leerDato("Introduce color: ", String::class.java, "Default"),
            origenCerveza = leerDato("Introduce origen: ", String::class.java, "Default"),
            puntuacionCerveza = leerDato("Introduce puntuación: ", Double::class.java, 0.0),
            cantidadCerveza = leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun escribirActualizarCerveza() {
    imprimirCervezas()
    CervezasDAO.actualizarCerveza(
        Cerveza(
            idCerveza = leerDato("Introduce ID de la Cerveza que quieres cambiar: ", Int::class.java, 0),
            nombreCerveza = leerDato("Introduce nombre: ", String::class.java, "Default"),
            graduacionCerveza = leerDato("Introduce graduación: ", Double::class.java, 0.0),
            tipoCerveza = leerDato("Introduce tipo: ", String::class.java, "Default"),
            colorCerveza = leerDato("Introduce color: ", String::class.java, "Default"),
            origenCerveza = leerDato("Introduce origen: ", String::class.java, "Default"),
            puntuacionCerveza = leerDato("Introduce puntuación: ", Double::class.java, 0.0),
            cantidadCerveza = leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun eliminarCerveza() {
    imprimirCervezas()
    val id = leerDato(variables.textoIdBorrar, Int::class.java, 0)
    CervezasDAO.eliminarCerveza(id)
}

// TapasDAO
data class Tapa(
    val idTapa: Int? = null,
    val nombreTapa: String,
    val precioTapa: Double,
    val cantidadTapa: Int
)
object TapasDAO {
    // Listar Tapas
    fun listarTapas(): List<Tapa> {
        val lista = mutableListOf<Tapa>()
        getConnection()?.use { conn ->
            conn.createStatement().use {stmt ->
                stmt.executeQuery(variables.queryListarTapas).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Tapa(
                                idTapa = rs.getInt("idTapa"),
                                nombreTapa = rs.getString("nombreTapa"),
                                precioTapa = rs.getDouble("precioTapa"),
                                cantidadTapa = rs.getInt("cantidadTapa")
                            )
                        )
                    }
                }
            }
        } ?: println(variables.noConexion)
        return lista
    }
    // Consultar Tapa por ID
    fun consultarTapaPorId(id: Int): Tapa? {
        var tapa: Tapa? = null
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryConsultarTapasPorId).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        tapa = Tapa(
                            idTapa = rs.getInt("idTapa"),
                            nombreTapa = rs.getString("nombreTapa"),
                            precioTapa = rs.getDouble("precioTapa"),
                            cantidadTapa = rs.getInt("cantidadTapa")
                        )
                    }
                }
            }
        } ?: println(variables.noConexion)
        return tapa
    }
    // Insertar Tapa
    fun insertarTapa(tapa: Tapa) {
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryInsertarTapa).use { stmt ->
                stmt.setString(1, tapa.nombreTapa)
                stmt.setDouble(2, tapa.precioTapa)
                stmt.setInt(3, tapa.cantidadTapa)
                stmt.executeUpdate()
                println("\nTapa '${tapa.nombreTapa}' insertada con éxito")
            }
        } ?: println(variables.noConexion)
    }
    // Actualizar Tapa
    fun actualizarTapa(tapa: Tapa) {
        if (tapa.idTapa == null) {
            println("\nNo se puede actualizar la tapa sin un ID.")
            return
        }
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryActualizarTapa).use { stmt ->
                stmt.setString(1, tapa.nombreTapa)
                stmt.setDouble(2, tapa.precioTapa)
                stmt.setInt(3, tapa.cantidadTapa)
                val filas = stmt.executeUpdate()
                if (filas > 0) {
                    println("\nTapa con ID = ${tapa.idTapa} actualizada con éxito.")
                } else {
                    println("\nNo se encontró ninguna tapa con el ID = ${tapa.idTapa}")
                }
            }
        } ?: println(variables.noConexion)
    }
    // Eliminar Tapa
    fun eliminarTapa(id: Int) {
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryEliminarTapa).use { stmt ->
                stmt.setInt(1, id)
                val filas = stmt.executeUpdate()
                if (filas > 0) {
                    println("\nTapa con id = $id eliminada correctamente.")
                } else {
                    println("\nNo se encontró ninguna tapa con id = $id.")
                }
            }
        } ?: println(variables.noConexion)
    }
}
fun imprimirTapas() {
    println(variables.textoListaTapas)
    TapasDAO.listarTapas().forEach {
        println("- [${it.idTapa}], " +
                "${it.nombreTapa}, " +
                "${it.precioTapa}$, " +
                "${it.cantidadTapa}")
    }
}
fun imprimirTapaSeleccionada() {
    imprimirTapas()
    val id = leerDato(variables.textoIntroducirTapaID,
        Int::class.java,
        0)
    val tapa = TapasDAO.consultarTapaPorId(id)
    if (tapa != null) {
        println("\nTapa encontrada:\n[${tapa.idTapa}], " +
                "Nombre: ${tapa.nombreTapa}, " +
                "Precio Tapa: ${tapa.precioTapa}, " +
                "Cantidad: ${tapa.cantidadTapa}")
    } else {
        println("No se encontró ninguna cerveza con ese ID.")
    }
}
fun escribirInsertarTapa() {
    TapasDAO.insertarTapa(
        Tapa(
            nombreTapa = leerDato("Introduce nombre: ", String::class.java, "Default"),
            precioTapa = leerDato("Introduce graduación: ", Double::class.java, 0.0),
            cantidadTapa = leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun escribirActualizarTapa() {
    imprimirTapas()
    TapasDAO.actualizarTapa(
        Tapa(
            idTapa = leerDato("Introduce ID de la Tapa que quieres cambiar: ", Int::class.java, 0),
            nombreTapa = leerDato("Introduce nombre: ", String::class.java, "Default"),
            precioTapa = leerDato("Introduce graduación: ", Double::class.java, 0.0),
            cantidadTapa = leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun eliminarTapa() {
    imprimirTapas()
    val id = leerDato(variables.textoIdBorrar, Int::class.java, 0)
    TapasDAO.eliminarTapa(id)
}

// ProveedorDAO
data class Proveedor(
    val idProveedor: Int? = null,
    val nombreProveedor: String
)
object ProveedorDAO {
    // Listar Proveedor
    fun listarProveedor(): List<Proveedor> {
        val lista = mutableListOf<Proveedor>()
        getConnection()?.use { conn ->
            conn.createStatement().use { stmt ->
                stmt.executeQuery(variables.queryListarProveedores).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Proveedor(
                                idProveedor = rs.getInt("idProveedor"),
                                nombreProveedor = rs.getString("nombreProveedor")
                            )
                        )
                    }
                }
            }
        } ?: println(variables.noConexion)
        return lista
    }
    // Consultar Proveedor por ID
    fun consultarProveedorPorId(id: Int): Proveedor? {
        var proveedor: Proveedor? = null
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryConsultarProveedorPorId).use { stmt ->
                stmt.setInt(1, id)
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        proveedor = Proveedor(
                            idProveedor = rs.getInt("idProveedor"),
                            nombreProveedor = rs.getString("nombreProveedor")
                        )
                    }
                }
            }
        } ?: println(variables.noConexion)
        return proveedor
    }
    // Insertar Proveedor
    fun insertarProveedor(proveedor: Proveedor) {
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryInsertarProveedor).use { stmt ->
                stmt.setString(1, proveedor.nombreProveedor)
                stmt.executeUpdate()
                println("\nProveedor '${proveedor.nombreProveedor}' insertada con éxito")
            }
        } ?: println(variables.noConexion)
    }
    // Actualizar Proveedor
    fun actualizarProveedor(proveedor: Proveedor) {
        if (proveedor.idProveedor == null) {
            println("\nNo se puede actualizar el proveedor sin un ID.")
            return
        }
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryActualizarProveedor).use { stmt ->
                stmt.setString(1, proveedor.nombreProveedor)
                val filas = stmt.executeUpdate()
                if (filas > 0) {
                    println("\nProveedor con ID = ${proveedor.idProveedor} actualizada con éxito.")
                } else {
                    println("\nNo se encontró ningun proveedor con el ID = ${proveedor.idProveedor}")
                }
            }
        } ?: println(variables.noConexion)
    }
    // Eliminar Proveedor
    fun eliminarProveedor(id: Int) {
        getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryEliminarProveedor).use { stmt ->
                stmt.setInt(1, id)
                val filas = stmt.executeUpdate()
                if (filas > 0) {
                    println("\nProveedor con id = $id eliminada correctamente.")
                } else {
                    println("\nNo se encontró ningun proveedor con id = $id.")
                }
            }
        } ?: println(variables.noConexion)
    }
}
fun imprimirProveedor() {
    println(variables.textoListaProveedor)
    ProveedorDAO.listarProveedor().forEach {
        println("- [${it.idProveedor}], " +
                "Proveedor: ${it.nombreProveedor}")
    }
}
fun imprimirProveedorSeleccionado() {
    imprimirProveedor()
    val id = leerDato(variables.textoIntroducirProveedorID,
        Int::class.java,
        0)
    val proveedor = ProveedorDAO.consultarProveedorPorId(id)
    if (proveedor != null) {
        println("\nProveedor encontrada:\n[${proveedor.idProveedor}], " +
                "Nombre: ${proveedor.nombreProveedor}")
    } else {
        println("No se encontró ningun proveedor con ese ID.")
    }
}
fun escribirInsertarProveedor() {
    ProveedorDAO.insertarProveedor(
        Proveedor(
            nombreProveedor = leerDato("Introduce nombre: ", String::class.java, "Default")
        )
    )
}
fun escribirActualizarProveedor() {
    imprimirProveedor()
    ProveedorDAO.actualizarProveedor(
        Proveedor(
            idProveedor = leerDato("Introduce ID del Proveedor que quieres cambiar: ", Int::class.java, 0),
            nombreProveedor = leerDato("Introduce nombre: ", String::class.java, "Default")
        )
    )
}
fun eliminarProveedor() {
    imprimirProveedor()
    val id = leerDato(variables.textoIdBorrar, Int::class.java, 0)
    ProveedorDAO.eliminarProveedor(id)
}

// Funciones de Registro
data class Registro(
    val idRegistro: Int? = null,
    val idCerveza: Int,
    val cantidadCerveza: Int,
    val idTapa: Int,
    val cantidadTapa: Int,
    val sumaResta: String,
    val idProveedor: Int,
    val fechaDia: String
)
object RegistroDAO {
    // Listar Registro
    fun listarRegistro(): List<Registro> {
        val lista = mutableListOf<Registro>()
        getConnection()?.use { conn ->
            conn.createStatement().use {stmt ->
                stmt.executeQuery(variables.queryListarRegistro).use { rs ->
                    while (rs.next()) {
                        lista.add(
                            Registro(
                                idRegistro = rs.getInt("idRegistro"),
                                idCerveza = rs.getInt("idCerveza"),
                                cantidadCerveza = rs.getInt("cantidadCerveza"),
                                idTapa = rs.getInt("idTapa"),
                                cantidadTapa = rs.getInt("cantidadTapa"),
                                sumaResta = rs.getString("sumaResta"),
                                idProveedor = rs.getInt("idProveedor"),
                                fechaDia = rs.getString("fechaDia")
                            )
                        )
                    }
                }
            }
        } ?: println(variables.noConexion)
        return lista
    }
}
fun mostrarRegistro() {
    println(variables.textoListaRegistros)
    RegistroDAO.listarRegistro().forEach {
        println("- [${it.idRegistro}] -> " +
                "IDCerveza: [${it.idCerveza}], " +
                "Cantidad: ${it.cantidadCerveza}, " +
                "IDTapa: [${it.idTapa}], " +
                "Cantidad: ${it.cantidadTapa}, " +
                "${it.sumaResta}, " +
                "Proveedor: ${it.idProveedor}, " +
                it.fechaDia
        )
    }
}
fun sumarCerveza() {
    imprimirCervezas()
    val sumarIdCerveza = leerDato("Introduce ID de la cerveza a aumentar: ", Int::class.java, 0)
    val sumarCantidadCerveza = leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val sumarIdProveedor = leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Sumar Stock a la Cerveza
            conn.prepareStatement("UPDATE Cervezas SET cantidadCerveza = cantidadCerveza + $sumarCantidadCerveza WHERE idCerveza = ?").use { stock ->
                stock.setInt(1, sumarIdCerveza)
                stock.executeUpdate()
            }
            // Marcar en el registro lo que se ha realizado
            conn.prepareStatement("INSERT INTO Registro(idCerveza, cantidadCerveza, sumaResta, idProveedor, fechaDia) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)").use { sumar ->
                sumar.setInt(1, sumarIdCerveza)
                sumar.setInt(2, sumarCantidadCerveza)
                sumar.setString(3, "Suma")
                sumar.setInt(4, sumarIdProveedor)
                sumar.executeUpdate()
            }
            conn.commit()
            println("\nTarea realizada con éxito.")
        } catch (e: SQLException) {
            println("\nError: ${e.message}")
            conn.rollback()
            println("\nTransacción revertida.")
        }
    }
}
fun sumarTapa() {
    imprimirTapas()
    val sumarIdTapa = leerDato("Introduce ID de la tapa a aumentar: ", Int::class.java, 0)
    val sumarCantidadTapa = leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val sumarIdProveedor = leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Sumar Stock a la Cerveza
            conn.prepareStatement("UPDATE Tapas SET cantidadTapa = cantidadTapa + $sumarCantidadTapa WHERE idTapa = ?").use { stock ->
                stock.setInt(1, sumarIdTapa)
                stock.executeUpdate()
            }
            // Marcar en el registro lo que se ha realizado
            conn.prepareStatement("INSERT INTO Registro(idTapa, cantidadTapa, sumaResta, idProveedor, fechaDia) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)").use { sumar ->
                sumar.setInt(1, sumarIdTapa)
                sumar.setInt(2, sumarCantidadTapa)
                sumar.setString(3, "Suma")
                sumar.setInt(4, sumarIdProveedor)
                sumar.executeUpdate()
            }
            conn.commit()
            println("\nTarea realizada con éxito.")
        } catch (e: SQLException) {
            println("\nError: ${e.message}")
            conn.rollback()
            println("\nTransacción revertida.")
        }
    }
}
fun sumarAmbas() {
    imprimirCervezas()
    val sumarIdCerveza = leerDato("Introduce ID de la cerveza a aumentar: ", Int::class.java, 0)
    val sumarCantidadCerveza = leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirTapas()
    val sumarIdTapa = leerDato("Introduce ID de la tapa a aumentar: ", Int::class.java, 0)
    val sumarCantidadTapa = leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val sumarIdProveedor = leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Sumar Stock a la Cerveza
            conn.prepareStatement("UPDATE Cervezas SET cantidadCerveza = cantidadCerveza + $sumarCantidadCerveza WHERE idCerveza = ?").use { stock ->
                stock.setInt(1, sumarIdCerveza)
                stock.executeUpdate()
            }
            // Sumar Stock a la Cerveza
            conn.prepareStatement("UPDATE Tapas SET cantidadTapa = cantidadTapa + $sumarCantidadTapa WHERE idTapa = ?").use { stock ->
                stock.setInt(1, sumarIdTapa)
                stock.executeUpdate()
            }
            // Marcar en el registro lo que se ha realizado
            conn.prepareStatement("INSERT INTO Registro(idCerveza, cantidadCerveza, idTapa, cantidadTapa, sumaResta, idProveedor, fechaDia) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)").use { sumar ->
                sumar.setInt(1, sumarIdCerveza)
                sumar.setInt(2, sumarCantidadCerveza)
                sumar.setInt(3, sumarIdTapa)
                sumar.setInt(4, sumarCantidadTapa)
                sumar.setString(5, "Suma")
                sumar.setInt(6, sumarIdProveedor)
                sumar.executeUpdate()
            }
            conn.commit()
            println("\nTarea realizada con éxito.")
        } catch (e: SQLException) {
            println("\nError: ${e.message}")
            conn.rollback()
            println("\nTransacción revertida.")
        }
    }
}
fun ventaCerveza() {
    imprimirCervezas()
    val idCerveza = leerDato("Introduce ID de la cerveza a vender: ", Int::class.java, 0)
    val cantidadVenta = leerDato("Introduce cantidad a vender: ", Int::class.java, 0)
    getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Comprobar stock disponible
            val stockActual = conn.prepareStatement("SELECT cantidadCerveza FROM Cervezas WHERE idCerveza = ?").use { ps ->
                ps.setInt(1, idCerveza)
                val rs = ps.executeQuery()
                if (rs.next()) rs.getInt("cantidadCerveza") else -1
            }
            if (stockActual < cantidadVenta || stockActual == -1) {
                println("\nStock insuficiente o cerveza no encontrada.")
                conn.rollback()
                return
            }
            // Restar stock
            conn.prepareStatement("UPDATE Cervezas SET cantidadCerveza = cantidadCerveza - ? WHERE idCerveza = ?").use { ps ->
                ps.setInt(1, cantidadVenta)
                ps.setInt(2, idCerveza)
                ps.executeUpdate()
            }
            // Registrar operación
            conn.prepareStatement("INSERT INTO Registro(idCerveza, cantidadCerveza, sumaResta, fechaDia) VALUES (?, ?, ?, CURRENT_TIMESTAMP)").use { ps ->
                ps.setInt(1, idCerveza)
                ps.setInt(2, cantidadVenta)
                ps.setString(3, "Resta")
                ps.executeUpdate()
            }
            conn.commit()
            println("\nVenta realizada con éxito.")
        } catch (e: SQLException) {
            println("\nError: ${e.message}")
            conn.rollback()
            println("\nTransacción revertida.")
        }
    }
}
fun ventaTapa() {
    imprimirTapas()
    val idTapa = leerDato("Introduce ID de la tapa a vender: ", Int::class.java, 0)
    val cantidadVenta = leerDato("Introduce cantidad a vender: ", Int::class.java, 0)
    getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Comprobar stock disponible
            val stockActual = conn.prepareStatement("SELECT cantidadTapa FROM Tapas WHERE idTapa = ?").use { ps ->
                ps.setInt(1, idTapa)
                val rs = ps.executeQuery()
                if (rs.next()) rs.getInt("cantidadTapa") else -1
            }
            if (stockActual < cantidadVenta || stockActual == -1) {
                println("\nStock insuficiente o tapa no encontrada.")
                conn.rollback()
                return
            }
            // Restar stock
            conn.prepareStatement("UPDATE Tapas SET cantidadTapa = cantidadTapa - ? WHERE idTapa = ?").use { ps ->
                ps.setInt(1, cantidadVenta)
                ps.setInt(2, idTapa)
                ps.executeUpdate()
            }
            // Registrar operación
            conn.prepareStatement("INSERT INTO Registro(idTapa, cantidadTapa, sumaResta, fechaDia) VALUES (?, ?, ?, CURRENT_TIMESTAMP)").use { ps ->
                ps.setInt(1, idTapa)
                ps.setInt(2, cantidadVenta)
                ps.setString(3, "Resta")
                ps.executeUpdate()
            }
            conn.commit()
            println("\nVenta realizada con éxito.")
        } catch (e: SQLException) {
            println("\nError: ${e.message}")
            conn.rollback()
            println("\nTransacción revertida.")
        }
    }
}
fun ventaAmbas() {
    imprimirCervezas()
    val idCerveza = leerDato("Introduce ID de la cerveza a vender: ", Int::class.java, 0)
    val cantidadCerveza = leerDato("Introduce cantidad de cerveza: ", Int::class.java, 0)
    imprimirTapas()
    val idTapa = leerDato("Introduce ID de la tapa a vender: ", Int::class.java, 0)
    val cantidadTapa = leerDato("Introduce cantidad de tapa: ", Int::class.java, 0)
    getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Comprobar stock de cerveza
            val stockCerveza = conn.prepareStatement("SELECT cantidadCerveza FROM Cervezas WHERE idCerveza = ?").use { ps ->
                ps.setInt(1, idCerveza)
                val rs = ps.executeQuery()
                if (rs.next()) rs.getInt("cantidadCerveza") else -1
            }
            // Comprobar stock de tapa
            val stockTapa = conn.prepareStatement("SELECT cantidadTapa FROM Tapas WHERE idTapa = ?").use { ps ->
                ps.setInt(1, idTapa)
                val rs = ps.executeQuery()
                if (rs.next()) rs.getInt("cantidadTapa") else -1
            }
            if (stockCerveza < cantidadCerveza || stockTapa < cantidadTapa || stockCerveza == -1 || stockTapa == -1) {
                println("\nStock insuficiente o producto no encontrado.")
                conn.rollback()
                return
            }
            // Restar ambas cantidades
            conn.prepareStatement("UPDATE Cervezas SET cantidadCerveza = cantidadCerveza - ? WHERE idCerveza = ?").use { ps ->
                ps.setInt(1, cantidadCerveza)
                ps.setInt(2, idCerveza)
                ps.executeUpdate()
            }
            conn.prepareStatement("UPDATE Tapas SET cantidadTapa = cantidadTapa - ? WHERE idTapa = ?").use { ps ->
                ps.setInt(1, cantidadTapa)
                ps.setInt(2, idTapa)
                ps.executeUpdate()
            }
            // Registrar operación
            conn.prepareStatement(
                "INSERT INTO Registro(idCerveza, cantidadCerveza, idTapa, cantidadTapa, sumaResta, fechaDia) VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)"
            ).use { ps ->
                ps.setInt(1, idCerveza)
                ps.setInt(2, cantidadCerveza)
                ps.setInt(3, idTapa)
                ps.setInt(4, cantidadTapa)
                ps.setString(5, "Resta")
                ps.executeUpdate()
            }
            conn.commit()
            println("\nVenta realizada con éxito.")
        } catch (e: SQLException) {
            println("\nError: ${e.message}")
            conn.rollback()
            println("\nTransacción revertida.")
        }
    }
}

