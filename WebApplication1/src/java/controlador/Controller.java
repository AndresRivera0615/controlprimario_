/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Controller {
    public static String Pintarusuarios() {
        String tabla = "";
        
       String lot ="";
        try {

            //conectar al API
            URL url = new URL("http://100.126.18.42:8080/Consultarusuarios?hola");
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

//                System.out.println("INFORMACION SERVICIO" + informacion);

                String cadenaXml = informacion.toString();
                
                try {
                    DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    Document documentoXml = parser.parse(new ByteArrayInputStream(cadenaXml.getBytes()));

                    XPath xpath = XPathFactory.newInstance().newXPath();

                    String idlote = "//programa [@id=1]/id_lote";
                    String codtransaccion = "//programa [@id=1]/cod_transaccion";
                    String estadoU = "//programa [@id=1]/estado";
                    String fechaprocesamiento = "//programa [@id=1]/fecha_procesamiento";
                    String codpersona = "//programa [@id=1]/cod_persona";
                    String documentoU = "//programa [@id=1]/documento";

                    String id_lote = xpath.evaluate(idlote, documentoXml);
                    String cod_transaccion = xpath.evaluate(codtransaccion, documentoXml);
                    String estado = xpath.evaluate(estadoU, documentoXml);
                    String fecha_procesamiento = xpath.evaluate(fechaprocesamiento, documentoXml);
                    String cod_persona = xpath.evaluate(codpersona, documentoXml);
                    String documento = xpath.evaluate(documentoU, documentoXml);
                    
//                    System.out.println("id_lote" + id_lote);
//                    System.out.println("cod_transaccion" + cod_transaccion);
//                    System.out.println("estado" + estado);
//                    System.out.println("fecha_procesamiento" + fecha_procesamiento);
//                    System.out.println("cod_persona" + cod_persona);
//                    System.out.println("documento" + documento);
                    String[] lote = id_lote.split(",");
//                     System.out.println("INFOR LOTE SPLIT");
//                    System.out.println(Arrays.toString(lote));
                    String[] usuario = cod_persona.split(",");
                    
//                    tabla = tabla + "<thead>\n" +
//"                                        <tr>\n" +
//"                                            <th>Id lote</th>\n" +
//"                                            <th>Usuario</th>\n" +
//"                                            <th>Documento</th>\n" +
//"                                            <th>Fecha Ejcucion</th>\n" +
//"                                            <th>Estado</th>\n" +
//"                                            <th>Sincronizar</th>\n" +
//"                                        </tr>\n" +
//"                                    </thead>";
                    tabla = tabla + "<tr>";
                    System.out.println("<tr> ");
                    for (int i = 0; i < lote.length; i++) {
                        
                        lot = lot + ""+ lote[i] +"";
//                        System.out.println("<tr><td>" + lote[i] + "</td>");
                        return lot;
                    }
//                    tabla = tabla + "<td>informacion</td>";
//                    tabla = tabla + "<td>informacion2</td>";
//                    tabla = tabla + "";
                    for (int a = 0; a < usuario.length; a++) {
                        tabla = tabla + "<td>"+ usuario[a] +"</td>";
//                        System.out.println("<td> " + usuario[a] + "</td>");
                    }
                    
                     tabla = tabla + "</tr>";
                     
//                     System.out.println("TABLA " + tabla);
                    
                     System.out.println("LOTESEUSU " + lot);

//                    
//                            + "<td>" + id_lote + "</td>"
//                            + "<td>" + cod_persona + "</td>"
//                            + "<td>" + documento + "</td>"
//                            + "<td>" + fecha_procesamiento + "</td>"
//                            + "<td>" + estado + "</td>"
//                            + "<td><input type='submit' value='Editar' class='btn btn-primary'></td>"
//                            + "</tr>";

                } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*---------------*/
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return tabla;
    }
}
