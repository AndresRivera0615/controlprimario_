/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andre
 */
@WebServlet(name = "sincronizarusuario", urlPatterns = {"/sincronizarusuario"})
public class sincronizarusuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String t_empleado = request.getParameter("tipo_empleado");
        String documento = request.getParameter("documento");
        String estado = request.getParameter("estado");
        
        System.out.print("la cedula es" + nombre);
        System.out.print("el nombre es: " + apellido);
        System.out.print("el apellido es:" + t_empleado);
        System.out.print("el telefono es:" + documento);
        System.out.print("el direccion es:" + estado);
         try {

            //conectar al API
            URL url = new URL("http://100.126.18.42:8080/tesconsumo/?"+t_empleado+"&"+documento+"");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Validar conexion peticion exitosa
            int responseconexion = conn.getResponseCode();

            if (responseconexion != 200) {
                throw new RuntimeException("Fallo la peticion" + responseconexion);

            } else {
                //leer la información devuelta por el API
                StringBuilder informacion = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informacion.append(scanner.nextLine());
                    
                }
                scanner.close();
                /*-----------------------------------*/
                //mostrar la informacion del API
                System.out.println(informacion);
                out.println("$( document ).ready(function() {");
                out.println("$('#myModal').modal('toggle')");
                out.println("});");
                
                
                request.getRequestDispatcher("tables.jsp").forward(request, response);
                
                /*---------------*/
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
