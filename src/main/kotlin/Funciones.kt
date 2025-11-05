
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

    // Función para llamar al procedure
    fun llamarProcedure () {
        imprimirTapas()
        val idIntroducido = introducirDatos.leerDato("Introduce Id de Tapa que deseas calcula: ", Int::class.java, 0)
        getConnection()?.use { conn ->
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
}

