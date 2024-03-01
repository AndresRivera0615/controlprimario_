/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author andre
 */
@WebServlet(name = "detallesusuario", urlPatterns = {"/detallesusuario"})
public class detallesusuario extends HttpServlet {

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
        String tabla = "";

        String lot = "";
        String id_central = request.getParameter("id_central");
        String cod_persona = request.getParameter("cod_persona");
        String t_empleado = request.getParameter("tipo");
        String documento = request.getParameter("documento");
        String estado = request.getParameter("estado");

        System.out.print("la id_central es" + id_central);
        System.out.print("el cod_persona es: " + cod_persona);
        System.out.print("el t_empleado es:" + t_empleado);
        System.out.print("el documento es:" + documento);
        System.out.print("el direccion es:" + estado);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {

                //conectar al API
                URL url = new URL("http://100.126.18.42:8080/detalleusuario/?" + t_empleado + "&" + documento + "");
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

                    String cadenaXml = informacion.toString();
                    /*---------------*/
                    try {
                        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                        Document documentoXml = parser.parse(new ByteArrayInputStream(cadenaXml.getBytes()));

                        XPath xpath = XPathFactory.newInstance().newXPath();
                        /*-------------------------------------------------*/
                        String fullname = "//programa [@id=1001]/fullname";
                        String full = xpath.evaluate(fullname, documentoXml);
                        /*-------------------------------------------------*/
                        /*-------------------------------------------------*/
                        String firtname = "//programa [@id=1001]/firtname";
                        String firt = xpath.evaluate(firtname, documentoXml);
                        /*-------------------------------------------------*/ 
                        /*-------------------------------------------------*/
                        String middlename = "//programa [@id=1001]/middlename";
                        String middle = xpath.evaluate(middlename, documentoXml);
                        /*-------------------------------------------------*/ 
                        /*-------------------------------------------------*/
                        String primerapellido = "//programa [@id=1001]/primerapellido";
                        String primerape = xpath.evaluate(primerapellido, documentoXml);
                        /*-------------------------------------------------*/ 
                        /*-------------------------------------------------*/
                        String segundoapellido = "//programa [@id=1001]/segundoapellido";
                        String segundoape = xpath.evaluate(segundoapellido, documentoXml);
                        /*-------------------------------------------------*/ 
                        /*-------------------------------------------------*/
                        String employeenumber = "//programa [@id=1001]/employeenumber";
                        String employee = xpath.evaluate(employeenumber, documentoXml);
                        /*-------------------------------------------------*/ 
                        /*-------------------------------------------------*/
                        String telefono = "//programa [@id=1001]/telefono";
                        String tel = xpath.evaluate(telefono, documentoXml);
                        /*-------------------------------------------------*/
                        
                         /*-------------------------------------------------*/
                        String perfil = "//programa [@id=1001]/perfil";
                        String per = xpath.evaluate(perfil, documentoXml);
                        /*-------------------------------------------------*/
                        /*-------------------------------------------------*/
                        String correo = "//programa [@id=1001]/correo";
                        String mail = xpath.evaluate(correo, documentoXml);
                        /*-------------------------------------------------*/
                        /*-------------------------------------------------*/
                        String familia = "//programa [@id=1001]/familia";
                        String famili = xpath.evaluate(familia, documentoXml);
                        /*-------------------------------------------------*/
                        /*-------------------------------------------------*/
                        String codpersona = "//programa [@id=1001]/codigopersona";
                        String codper = xpath.evaluate(codpersona, documentoXml);
                        /*-------------------------------------------------*/
                        System.out.println(full);
                        System.out.println(firt);
                        System.out.println(middle);
                        System.out.println(primerape);
                        System.out.println(segundoape);
                        System.out.println(employee);
                        System.out.println(tel);
                        System.out.println(per);
                        System.out.println(mail);
                        System.out.println(famili);
                        System.out.println(codper);
                        HttpSession objsession = request.getSession();
                   objsession.setAttribute("full", full);
                   objsession.setAttribute("firt", firt);
                   objsession.setAttribute("middle", middle);
                   objsession.setAttribute("primerape", primerape);
                   objsession.setAttribute("segundoape", segundoape);
                   objsession.setAttribute("employee", employee);
                   objsession.setAttribute("tel", tel);
                   objsession.setAttribute("per", per);
                   objsession.setAttribute("mail", mail);
                   objsession.setAttribute("famili", famili);
                   objsession.setAttribute("codper", codper);
                   
                    request.getRequestDispatcher("detalleusuario.jsp").forward(request, response); // importante, este es el metodo mediante el cual se envian todos los requests obtenidos al jsp destino
                   

                    } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                        Logger.getLogger(detallesusuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
//        try {
//
//            //conectar al API
//            URL url = new URL("http://100.126.18.42:8080/detalleusuario/?"+t_empleado+"&"+documento+"");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.connect();
//
//            //Validar conexion peticion exitosa
//            int responseconexion = conn.getResponseCode();
//
//            if (responseconexion != 200) {
//                throw new RuntimeException("Fallo la peticion" + responseconexion);
//
//            } else {
//                //leer la información devuelta por el API
//                StringBuilder informacion = new StringBuilder();
//                Scanner scanner = new Scanner(url.openStream());
//                while (scanner.hasNext()) {
//                    informacion.append(scanner.nextLine());
//
//                }
//                scanner.close();
//                /*-----------------------------------*/
//                //mostrar la informacion del API
//                System.out.println(informacion);
//
//                tabla = tabla + "" + informacion.toString() + "";
//
//                /*---------------*/
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
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
