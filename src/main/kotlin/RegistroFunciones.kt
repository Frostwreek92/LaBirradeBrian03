import java.sql.SQLException
import kotlin.use


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
        funciones.getConnection()?.use { conn ->
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
    val sumarIdCerveza = introducirDatos.leerDato("Introduce ID de la cerveza a aumentar: ", Int::class.java, 0)
    val sumarCantidadCerveza = introducirDatos.leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val sumarIdProveedor = introducirDatos.leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
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
    val sumarIdTapa = introducirDatos.leerDato("Introduce ID de la tapa a aumentar: ", Int::class.java, 0)
    val sumarCantidadTapa = introducirDatos.leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val sumarIdProveedor = introducirDatos.leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
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
    val sumarIdCerveza = introducirDatos.leerDato("Introduce ID de la cerveza a aumentar: ", Int::class.java, 0)
    val sumarCantidadCerveza = introducirDatos.leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirTapas()
    val sumarIdTapa = introducirDatos.leerDato("Introduce ID de la tapa a aumentar: ", Int::class.java, 0)
    val sumarCantidadTapa = introducirDatos.leerDato("Introduce Cantidad: ", Int::class.java, 0)
    imprimirProveedor()
    val sumarIdProveedor = introducirDatos.leerDato("Introduce ID del Proveedor: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
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
    val idCerveza = introducirDatos.leerDato("Introduce ID de la cerveza a vender: ", Int::class.java, 0)
    val cantidadVenta = introducirDatos.leerDato("Introduce cantidad a vender: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
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
    val idTapa = introducirDatos.leerDato("Introduce ID de la tapa a vender: ", Int::class.java, 0)
    val cantidadVenta = introducirDatos.leerDato("Introduce cantidad a vender: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
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
    val idCerveza = introducirDatos.leerDato("Introduce ID de la cerveza a vender: ", Int::class.java, 0)
    val cantidadCerveza = introducirDatos.leerDato("Introduce cantidad de cerveza: ", Int::class.java, 0)
    imprimirTapas()
    val idTapa = introducirDatos.leerDato("Introduce ID de la tapa a vender: ", Int::class.java, 0)
    val cantidadTapa = introducirDatos.leerDato("Introduce cantidad de tapa: ", Int::class.java, 0)
    funciones.getConnection()?.use { conn ->
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
fun mostrarStockTotal() {
    funciones.getConnection()?.use { conn ->
        try {
            conn.prepareCall("{ CALL sp_mostrar_stock_total() }").use { call ->
                val rs = call.executeQuery()
                println("\n📦 STOCK TOTAL 📦")
                println("----------------------------------------------------------")
                println(String.format("%-10s %-20s %-10s %-10s %-10s",
                    "Tipo", "Nombre", "Cantidad", "Precio", "Total"))
                println("----------------------------------------------------------")
                while (rs.next()) {
                    val tipo = rs.getString("Tipo")
                    val nombre = rs.getString("Nombre")
                    val cantidad = rs.getInt("Cantidad")
                    val precio = rs.getDouble("PrecioUnidad")
                    val total = rs.getDouble("PrecioTotal")

                    println(String.format("%-10s %-20s %-10d %-10.2f %-10.2f",
                        tipo, nombre, cantidad, precio, total))
                }
                println("----------------------------------------------------------")
            }
        } catch (e: SQLException) {
            println("\nError: ${e.message}")
        }
    }
}

