

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
                                precioCerveza = rs.getDouble("puntuacionCerveza"),
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
                "${it.precioCerveza}*, " +
                "${it.cantidadCerveza}")
    }
}
fun imprimirCervezaSeleccionada() {
    imprimirCervezas()
    val id = introducirDatos.leerDato(variables.textoIntroducirCervezaID,
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
            nombreCerveza = introducirDatos.leerDato("Introduce nombre: ", String::class.java, "Default"),
            graduacionCerveza = introducirDatos.leerDato("Introduce graduación: ", Double::class.java, 0.0),
            tipoCerveza = introducirDatos.leerDato("Introduce tipo: ", String::class.java, "Default"),
            colorCerveza = introducirDatos.leerDato("Introduce color: ", String::class.java, "Default"),
            origenCerveza = introducirDatos.leerDato("Introduce origen: ", String::class.java, "Default"),
            precioCerveza = introducirDatos.leerDato("Introduce precio: ", Double::class.java, 0.0),
            cantidadCerveza = introducirDatos.leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun escribirActualizarCerveza() {
    imprimirCervezas()
    CervezasDAO.actualizarCerveza(
        Cerveza(
            idCerveza = introducirDatos.leerDato("Introduce ID de la Cerveza que quieres cambiar: ", Int::class.java, 0),
            nombreCerveza = introducirDatos.leerDato("Introduce nombre: ", String::class.java, "Default"),
            graduacionCerveza = introducirDatos.leerDato("Introduce graduación: ", Double::class.java, 0.0),
            tipoCerveza = introducirDatos.leerDato("Introduce tipo: ", String::class.java, "Default"),
            colorCerveza = introducirDatos.leerDato("Introduce color: ", String::class.java, "Default"),
            origenCerveza = introducirDatos.leerDato("Introduce origen: ", String::class.java, "Default"),
            precioCerveza = introducirDatos.leerDato("Introduce precio: ", Double::class.java, 0.0),
            cantidadCerveza = introducirDatos.leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun eliminarCerveza() {
    imprimirCervezas()
    val id = introducirDatos.leerDato(variables.textoIdBorrar, Int::class.java, 0)
    CervezasDAO.eliminarCerveza(id)
}
fun calcularTotalPrecioCervezaPorId () {
    imprimirTapas()
    val idIntroducido = introducirDatos.leerDato("Introduce Id de Tapa que deseas calcular: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
        val sql = "SELECT fn_total_valor_tapa(?)"
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

