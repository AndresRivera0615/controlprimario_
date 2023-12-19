/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class JavaApplication1 {

    public static void main(String[] args) {
        String nombre = "KAREN";
        String apellido = "PERE";
        String t_empleado = "EXTERNO";
        String documento = "1024589";
        String estado = "fase3";
        try {

            //conectar al API
            System.out.println("http://100.126.18.42:8080/tesconsumo/?"+t_empleado+"&"+documento+"");
            URL url = new URL("http://100.126.18.42:8080/tesconsumo/?"+nombre+"&"+apellido+"&"+t_empleado+"&"+documento+"&"+estado+"");
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
                
                /*---------------*/
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
