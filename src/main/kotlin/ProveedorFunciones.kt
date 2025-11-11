

data class Proveedor(
    val idProveedor: Int? = null,
    val nombreProveedor: String
)
object ProveedorDAO {
    // Listar Proveedor
    fun listarProveedor(): List<Proveedor> {
        val lista = mutableListOf<Proveedor>()
        funciones.getConnection()?.use { conn ->
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
        funciones.getConnection()?.use { conn ->
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
        funciones.getConnection()?.use { conn ->
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
        funciones.getConnection()?.use { conn ->
            conn.prepareStatement(variables.queryActualizarProveedor).use { stmt ->
                stmt.setString(1, proveedor.nombreProveedor)
                stmt.setInt(2, proveedor.idProveedor)
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
        funciones.getConnection()?.use { conn ->
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
    val id = funciones.leerDato(variables.textoIntroducirProveedorID,
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
            nombreProveedor = funciones.leerDato("Introduce nombre: ", String::class.java, "Default")
        )
    )
}
fun escribirActualizarProveedor() {
    imprimirProveedor()
    ProveedorDAO.actualizarProveedor(
        Proveedor(
            idProveedor = funciones.leerDato("Introduce ID del Proveedor que quieres cambiar: ", Int::class.java, 0),
            nombreProveedor = funciones.leerDato("Introduce nombre: ", String::class.java, "Default")
        )
    )
}
fun eliminarProveedor() {
    imprimirProveedor()
    val id = funciones.leerDato(variables.textoIdBorrar, Int::class.java, 0)
    ProveedorDAO.eliminarProveedor(id)
}

