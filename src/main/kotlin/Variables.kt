
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
    var salirOpcionesAWS = false
    val finPrograma = "\nFin del programa"
    // Menús
    val menuInicio = "\n" + """Menú Principal:
        1. Operaciones con Cervezas
        2. Operaciones con Tapas
        3. Operaciones con Proveedor
        4. Operaciones de Stock
        5. Operaciones con AWS
        6. Salir
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
    val menuOpcionesAWS = "\n" + """Menú operaciones AWS
        Funciones:
        1. Calcular precio total Cerveza por ID
        2. Calcular preio total Tapa por ID
        3. Mostrar Stock de Todo
        Procedimientos:
        4. Sumar Cervezas por ID
        5. Sumar Tapas por ID
        6. Restar Cervezas por ID
        7. Restar Tapas por ID
        8. Salir
    """.trimIndent()
    // Ruta del archivo de BD
    val urlBD = "jdbc:mysql://ec2-98-90-151-148.compute-1.amazonaws.com:3306/LaBirradeBrian"
    val user = "AlvaroGM"
    val pass = "AlvaroGM"
    // Query de las funciones
    // Cervezas
    val queryListarCervezas = "SELECT * FROM Cervezas"
    val queryConsultarCervezasPorId = "SELECT * FROM Cervezas WHERE idCerveza = ?"
    val queryInsertarCerveza = "INSERT INTO Cervezas(nombreCerveza, " +
            "graduacionCerveza, " +
            "tipoCerveza, " +
            "colorCerveza, " +
            "origenCerveza, " +
            "precioCerveza, " +
            "cantidadCerveza) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)"
    val queryActualizarCerveza = "UPDATE Cervezas SET " +
            "nombreCerveza = ?," +
            "graduacionCerveza = ?," +
            "tipoCerveza = ?," +
            "colorCerveza = ?," +
            "origenCerveza = ?," +
            "puntuacionCerveza = ?," +
            "cantidadCerveza = ? " +
            "WHERE idCerveza = ?"
    val queryEliminarCerveza = "DELETE FROM Cervezas WHERE idCerveza = ?"
    // Tapas
    val queryListarTapas = "SELECT * FROM Tapas"
    val queryConsultarTapasPorId = "SELECT * FROM Tapas WHERE idTapa = ?"
    val queryInsertarTapa = "INSERT INTO Tapas(nombreTapa, " +
            "precioTapa, " +
            "cantidadTapa) " +
            "VALUES (?, ?, ?)"
    val queryActualizarTapa = "UPDATE Tapas SET " +
            "nombreTapa = ?, " +
            "precioTapa = ?, " +
            "cantidadTapa = ? " +
            "WHERE idTapa = ?"
    val queryEliminarTapa = "DELETE FROM Tapas WHERE idTapa = ?"
    // Proveedor
    val queryListarProveedores = "SELECT * FROM Proveedores"
    val queryConsultarProveedorPorId = "SELECT * FROM Proveedores WHERE idProveedor = ?"
    val queryInsertarProveedor = "INSERT INTO Proveedores(" +
            "nombreProveedor) " +
            "VALUES (?)"
    val queryActualizarProveedor = "UPDATE Proveedores SET " +
            "nombreProveedor = ? " +
            "WHERE idProveedor = ?"
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
val funciones = Funciones()

