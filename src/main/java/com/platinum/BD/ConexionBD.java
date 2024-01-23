/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platinum.BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    // URL de la base de datos
    private static final String URL_BD = "jdbc:mysql://localhost:3306/Platinium";
    private static final String nombreDeUsuario = "root";  
    private static final String pass = "Es-151515";
    // Método para establecer la conexión a la base de datos
    public static Connection conectar() {
        try {
            System.out.println("iniciando conexion a BD ");
            return DriverManager.getConnection(URL_BD, nombreDeUsuario, pass);
        } catch (SQLException e) {
            System.out.println("fallo de conexion a base de datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
   

    // Método para inicializar la base de datos
    public static void inicializarBaseDatos() {
        if (!existeBaseDatos()) {
            crearTablas();
            insertarRegistrosDePrueba();
        } else {
            System.out.println("La base de datos ya existe, no es necesario crearla nuevamente.");
        }
    }

    // Método para verificar si la base de datos ya existe
    public static boolean existeBaseDatos() {
        try {
            DriverManager.getConnection(URL_BD + ";shutdown=true");
            return true;  // La base de datos existe
        } catch (SQLException e) {
            if ("XJ015".equals(e.getSQLState())) {
                return false;  // La base de datos no existe
            } else {
                e.printStackTrace();
                return true;  // Ocurrió un error al intentar verificar la existencia de la base de datos
            }
        }
    }

    // Método para crear las tablas
    private static void crearTablas() {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            // Crear tabla Persona
            String sqlPersona = "CREATE TABLE Persona (" +
                    "Rut VARCHAR(10) PRIMARY KEY, " +
                    "nombre VARCHAR(50), " +
                    "apellido VARCHAR(50), " +
                    "direccion VARCHAR(100), " +
                    "correo VARCHAR(100), " +
                    "telefono VARCHAR(15)" +
                    ")";
            stmt.executeUpdate(sqlPersona);
            System.out.println("Tabla Persona creada exitosamente.");

            // Crear tabla CtaCorriente
            String sqlCtaCorriente = "CREATE TABLE CtaCorriente (" +
                    "idCuenta INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                    "RutCliente VARCHAR(10), " +
                    "monto DECIMAL(10, 2), " +
                    "ejecutivo VARCHAR(50), " +
                    "FOREIGN KEY (RutCliente) REFERENCES Persona(Rut)" +
                    ")";
            stmt.executeUpdate(sqlCtaCorriente);
            System.out.println("Tabla CtaCorriente creada exitosamente.");

            // Crear tabla Usuarios
            String sqlUsuarios = "CREATE TABLE Usuarios (" +
                    "idUsuario INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                    "nombreUsuario VARCHAR(50) NOT NULL, " +
                    "contrasena VARCHAR(50) NOT NULL" +
                    ")";
            stmt.executeUpdate(sqlUsuarios);
            System.out.println("Tabla Usuarios creada exitosamente.");
             // Crear tabla Transacción
            String sqlTransaccion = "CREATE TABLE Transaccion (" +
                    "id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                    "rutCliente VARCHAR(10), " +
                    "rutDueño VARCHAR(10), " +
                    "idCuenta INT, " +
                    "montoTransferencia DECIMAL(10, 2), " +
                    "cuentaTransferencia VARCHAR(20), " +
                    "tipoCuenta VARCHAR(20), " +
                    "FOREIGN KEY (rutCliente) REFERENCES Persona(Rut), " +
                    "FOREIGN KEY (rutDueño) REFERENCES Persona(Rut), " +
                    "FOREIGN KEY (idCuenta) REFERENCES CtaCorriente(idCuenta)" +
                    ")";
            stmt.executeUpdate(sqlTransaccion);
            System.out.println("Tabla Transaccion creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar registros de prueba
    private static void insertarRegistrosDePrueba() {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            // Insertar registros de prueba en la tabla Persona
            String insertPersona1 = "INSERT INTO Persona (Rut, nombre, apellido, direccion, correo, telefono) VALUES ('11111111-1', 'Juan', 'Pérez', 'Calle 123', 'juan@example.com', '12345678')";
            String insertPersona2 = "INSERT INTO Persona (Rut, nombre, apellido, direccion, correo, telefono) VALUES ('22222222-2', 'María', 'López', 'Avenida 456', 'maria@example.com', '87654321')";
            stmt.executeUpdate(insertPersona1);
            stmt.executeUpdate(insertPersona2);
            
            // Insertar registros de prueba en la tabla CtaCorriente
            String insertCtaCorriente1 = "INSERT INTO CtaCorriente (RutCliente, monto, ejecutivo) VALUES ('11111111-1', 1500.00, 'Ejecutivo1')";
            String insertCtaCorriente2 = "INSERT INTO CtaCorriente (RutCliente, monto, ejecutivo) VALUES ('22222222-2', 2500.00, 'Ejecutivo2')";
            stmt.executeUpdate(insertCtaCorriente1);
            stmt.executeUpdate(insertCtaCorriente2);
            
            // Insertar registros de prueba en la tabla Usuarios
            String insertUsuario1 = "INSERT INTO Usuarios (nombreUsuario, contrasena) VALUES ('usuario1', 'password1')";
            String insertUsuario2 = "INSERT INTO Usuarios (nombreUsuario, contrasena) VALUES ('usuario2', 'password2')";
            stmt.executeUpdate(insertUsuario1);
            stmt.executeUpdate(insertUsuario2);
            
            System.out.println("Registros de prueba insertados exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        inicializarBaseDatos();
    }
}
