import java.sql.SQLException


data class Cerveza(
    val idCerveza: Int? = null, // la genera SQLite automáticamente
    val nombreCerveza: String,
    val graduacionCerveza: Double,
    val tipoCerveza: String,
    val colorCerveza: String,
    val origenCerveza: String,
    val precioCerveza: Double,
    val cantidadCerveza: Int
)
object CervezasDAO {
    // Listar Cervezas
    fun listarCervezas(): List<Cerveza> {
        val lista = mutableListOf<Cerveza>()
        funciones.getConnection()?.use { conn ->
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
                                precioCerveza = rs.getDouble("precioCerveza"),
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
        funciones.getConnection()?.use { conn ->
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
                            precioCerveza = rs.getDouble("puntuacionCerveza"),
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
        funciones.getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryInsertarCerveza).use { stmt ->
                stmt.setString(1, cerveza.nombreCerveza)
                stmt.setDouble(2, cerveza.graduacionCerveza)
                stmt.setString(3, cerveza.tipoCerveza)
                stmt.setString(4, cerveza.colorCerveza)
                stmt.setString(5, cerveza.origenCerveza)
                stmt.setDouble(6, cerveza.precioCerveza)
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
        funciones.getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryActualizarCerveza).use { stmt ->
                stmt.setString(1, cerveza.nombreCerveza)
                stmt.setDouble(2, cerveza.graduacionCerveza)
                stmt.setString(3, cerveza.tipoCerveza)
                stmt.setString(4, cerveza.colorCerveza)
                stmt.setString(5, cerveza.origenCerveza)
                stmt.setDouble(6, cerveza.precioCerveza)
                stmt.setInt(7, cerveza.cantidadCerveza)
                stmt.setInt(8, cerveza.idCerveza)
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
        funciones.getConnection()?.use { conn ->
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
                "${it.precioCerveza}, " +
                "${it.cantidadCerveza}")
    }
}
fun imprimirCervezaSeleccionada() {
    imprimirCervezas()
    val id = funciones.leerDato(variables.textoIntroducirCervezaID,
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
                "Precio: ${cerveza.precioCerveza}, " +
                "Cantidad: ${cerveza.cantidadCerveza}")
    } else {
        println("No se encontró ninguna cerveza con ese ID.")
    }
}
fun escribirInsertarCerveza() {
    CervezasDAO.insertarCerveza(
        Cerveza(
            nombreCerveza = funciones.leerDato("Introduce nombre: ", String::class.java, "Default"),
            graduacionCerveza = funciones.leerDato("Introduce graduación: ", Double::class.java, 0.0),
            tipoCerveza = funciones.leerDato("Introduce tipo: ", String::class.java, "Default"),
            colorCerveza = funciones.leerDato("Introduce color: ", String::class.java, "Default"),
            origenCerveza = funciones.leerDato("Introduce origen: ", String::class.java, "Default"),
            precioCerveza = funciones.leerDato("Introduce precio: ", Double::class.java, 0.0),
            cantidadCerveza = funciones.leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun escribirActualizarCerveza() {
    imprimirCervezas()
    CervezasDAO.actualizarCerveza(
        Cerveza(
            idCerveza = funciones.leerDato("Introduce ID de la Cerveza que quieres cambiar: ", Int::class.java, 0),
            nombreCerveza = funciones.leerDato("Introduce nombre: ", String::class.java, "Default"),
            graduacionCerveza = funciones.leerDato("Introduce graduación: ", Double::class.java, 0.0),
            tipoCerveza = funciones.leerDato("Introduce tipo: ", String::class.java, "Default"),
            colorCerveza = funciones.leerDato("Introduce color: ", String::class.java, "Default"),
            origenCerveza = funciones.leerDato("Introduce origen: ", String::class.java, "Default"),
            precioCerveza = funciones.leerDato("Introduce precio: ", Double::class.java, 0.0),
            cantidadCerveza = funciones.leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun eliminarCerveza() {
    imprimirCervezas()
    val id = funciones.leerDato(variables.textoIdBorrar, Int::class.java, 0)
    CervezasDAO.eliminarCerveza(id)
}
fun calcularTotalPrecioCervezaPorId() {
    imprimirCervezas()
    val idIntroducido = funciones.leerDato("Introduce Id de Cerveza que deseas calcular: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
        val sql = "SELECT fn_total_valor_cerveza(?)"
        conn.prepareStatement(sql).use { stmt ->
            stmt.setInt(1, idIntroducido)
            stmt.executeQuery().use { rs ->
                if (rs.next()) {
                    val resultado = rs.getInt(1)
                    println("El precio total es: $resultado$")
                }
            }
        }
    }
}
fun sumarCervezasPorId() {
    imprimirCervezas()
    val idCerveza = funciones.leerDato("Introduce ID de la cerveza a aumentar: ", Int::class.java, 0)
    val cantidad = funciones.leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val idProveedor = funciones.leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Llamada al procedimiento almacenado
            conn.prepareCall("{ CALL sp_sumar_cerveza(?, ?, ?, ?) }").use { call ->
                call.setInt(1, idCerveza)
                call.setInt(2, cantidad)
                call.setString(3, "Suma")  // valor del parámetro p_sumaResta
                call.setInt(4, idProveedor)
                val resultSet = call.executeQuery()
                // Mostrar el mensaje que devuelve el procedimiento
                if (resultSet.next()) {
                    println("\n${resultSet.getString("mensaje")}")
                }
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
fun restarCervezaPorId() {
    imprimirCervezas()
    val idCerveza = funciones.leerDato("Introduce ID de la cerveza a reducir: ", Int::class.java, 0)
    val cantidad = funciones.leerDato("Introduce Cantidad a restar: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            // Llamada al procedimiento almacenado
            conn.prepareCall("{ CALL sp_restar_cerveza(?, ?, ?) }").use { call ->
                call.setInt(1, idCerveza)
                call.setInt(2, cantidad)
                call.setString(3, "Resta") // valor para el campo sumaResta
                val rs = call.executeQuery()
                // Mostrar mensaje devuelto por el procedimiento
                if (rs.next()) {
                    println("\n${rs.getString("mensaje")}")
                }
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

