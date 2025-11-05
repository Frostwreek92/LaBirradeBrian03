
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Funciones {
    // Función para conectar a la base de datos
    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection(variables.urlBD, variables.user, variables.pass)

        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }
    // Fin para cualquier when
    fun finEleccion(): Boolean {
        return true
    }
}

