import java.sql.SQLException
import kotlin.use

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
        funciones.getConnection()?.use { conn ->
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
        funciones.getConnection()?.use { conn ->
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
        funciones.getConnection()?.use { conn ->
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
        funciones.getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryActualizarTapa).use { stmt ->
                stmt.setString(1, tapa.nombreTapa)
                stmt.setDouble(2, tapa.precioTapa)
                stmt.setInt(3, tapa.cantidadTapa)
                stmt.setInt(4, tapa.idTapa)
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
        funciones.getConnection()?.use { conn ->
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
    val id = funciones.leerDato(variables.textoIntroducirTapaID,
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
            nombreTapa = funciones.leerDato("Introduce nombre: ", String::class.java, "Default"),
            precioTapa = funciones.leerDato("Introduce graduación: ", Double::class.java, 0.0),
            cantidadTapa = funciones.leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun escribirActualizarTapa() {
    imprimirTapas()
    TapasDAO.actualizarTapa(
        Tapa(
            idTapa = funciones.leerDato("Introduce ID de la Tapa que quieres cambiar: ", Int::class.java, 0),
            nombreTapa = funciones.leerDato("Introduce nombre: ", String::class.java, "Default"),
            precioTapa = funciones.leerDato("Introduce graduación: ", Double::class.java, 0.0),
            cantidadTapa = funciones.leerDato("Introduce cantidad: ", Int::class.java, 0)
        )
    )
}
fun eliminarTapa() {
    imprimirTapas()
    val id = funciones.leerDato(variables.textoIdBorrar, Int::class.java, 0)
    TapasDAO.eliminarTapa(id)
}
fun calcularTotalPrecioTapaPorId () {
    imprimirTapas()
    val idIntroducido = funciones.leerDato("Introduce Id de Tapa que deseas calcular: ", Int::class.java, 0)
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
fun sumarTapasPorId() {
    imprimirTapas()
    val idTapa = funciones.leerDato("Introduce ID de la tapa a aumentar: ", Int::class.java, 0)
    val cantidad = funciones.leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val idProveedor = funciones.leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            conn.prepareCall("{ CALL sp_sumar_tapa(?, ?, ?, ?) }").use { call ->
                call.setInt(1, idTapa)
                call.setInt(2, cantidad)
                call.setString(3, "Suma")
                call.setInt(4, idProveedor)
                val rs = call.executeQuery()
                if (rs.next()) println("\n${rs.getString("mensaje")}")
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
fun restarTapasPorId() {
    imprimirTapas()
    val idTapa = funciones.leerDato("Introduce ID de la tapa a reducir: ", Int::class.java, 0)
    val cantidad = funciones.leerDato("Introduce Cantidad a restar: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
        try {
            conn.autoCommit = false
            conn.prepareCall("{ CALL sp_restar_tapa(?, ?, ?) }").use { call ->
                call.setInt(1, idTapa)
                call.setInt(2, cantidad)
                call.setString(3, "Resta")

                val rs = call.executeQuery()
                if (rs.next()) println("\n${rs.getString("mensaje")}")
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

