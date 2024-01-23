/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;
import com.platinum.BD.ConexionBD;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class ConexionTest {

    @Test
    public void testConexionExitosa() {
         try (Connection conexion = ConexionBD.conectar()) {
            assertNotNull("La conexión no debe ser nula", conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
