/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.platinum.BD.ConexionBD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Finrot15
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InicializacionServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("intentando inicializar base de datos");
        ConexionBD.inicializarBaseDatos();
        System.out.println("La base de datos ha sido inicializada correctamente.");
    }
    

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}