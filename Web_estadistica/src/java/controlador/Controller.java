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

        String lot = "";
        try {

            //conectar al API
            URL url = new URL("http://100.126.18.42:8080/Consultarusuarios");
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

                tabla = tabla + "" + informacion.toString() + "";

                /*---------------*/
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return tabla;
    }



    public static String modalmostrarerror() {
        String Msg = "<script>\n"
                + "alert('Fallo la peticion');\n"
                + "</script>";

        return Msg;
    }

}
