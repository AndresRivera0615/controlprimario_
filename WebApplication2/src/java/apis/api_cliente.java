/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paiz2
 */
public class api_cliente {

    private String url_api = "http://100.126.18.42:8080/Consultarusuarios";

    private String get() {
        String salida = "";
        try {
            URL url = new URL(url_api);
            HttpURLConnection c_api = (HttpURLConnection) url.openConnection();
            c_api.setRequestMethod("GET");
            c_api.setRequestProperty("Accept", "application/json");
            if (c_api.getResponseCode() == 200) {
//                InputStreamReader entrada = new InputStreamReader(c_api.getInputStream());
//                BufferedReader lectura = new BufferedReader(entrada);
                StringBuilder informacion = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informacion.append(scanner.nextLine());
                }
                scanner.close();
                salida = informacion.toString();
                System.out.println("SALIDAINFO " + salida);

            } else {
                salida = "";
                System.out.println("No se puede conectar a la api : " + c_api.getResponseCode());

            }

            c_api.disconnect();

        } catch (IOException ex) {
            System.out.println("Error api:" + ex.getMessage());
        }
        return salida;
    }

    public DefaultTableModel leer() {
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            String encabezado[] = {"Lote", "Lote", "Estado", "Fecha Ejecucion", "Usuario", "Documento", "Cod Transaccion","legado","existe"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            System.out.println("encabezado:" + encabezado.toString());
            JSONArray arreglo = new JSONArray(get());
            for (int indice = 0; indice < arreglo.length(); indice++) {
                JSONObject atributo = arreglo.getJSONObject(indice);
                datos[0] = atributo.getString("id_cliente");
                datos[1] = atributo.getString("nit");
                datos[2] = atributo.getString("nombres");
                datos[3] = atributo.getString("apellidos");
                datos[4] = atributo.getString("direccion");
                datos[5] = atributo.getString("telefono");
                datos[6] = atributo.getString("fecha_nacimiento");
                datos[7] = atributo.getString("legado");
                datos[8] = atributo.getString("existe");
                tabla.addRow(datos);
            }
        } catch (JSONException ex) {
            System.out.println("Error tabla:" + ex.getMessage());
        }
        return tabla;
    }

//    public int post() {
//        int salida = 0;
//        try {
//            URL url = new URL(url_api);
//            HttpURLConnection c_api = (HttpURLConnection) url.openConnection();
//            c_api.setRequestMethod("POST");
//            c_api.setRequestProperty("Content-Type", "application/json; utf-8");
//            // c_api.setRequestProperty("Accept", "application/json");
//            c_api.setDoOutput(true);
//            String jsonS = "{\"nit\":\"1234\",\n"
//                    + "\"nombres\":\"Jose Jose\",\n"
//                    + "\"apellidos\":\"Lopez Lopez\",\n"
//                    + "\"direccion\":\"Direccion\",\n"
//                    + "\"telefono\":\"555\",\n"
//                    + "\"fecha_nacimiento\":\"1995-01-01\"}";
//            OutputStream os = c_api.getOutputStream();
//            os.write(jsonS.getBytes());
//            os.flush();
//
//            if (c_api.getResponseCode() == 200) {
//
//                salida = 1;
//
//            } else {
//
//                System.out.println("No se puede conectar a la api : " + c_api.getResponseCode());
//
//            }
//
//            c_api.disconnect();
//
//        } catch (IOException ex) {
//
//            System.out.println("Error api:" + ex.getMessage());
//        }
//        return salida;
//
//    }
}
