<%-- 
    Document   : index
    Created on : 19/12/2023, 11:35:34 AM
    Author     : andre
--%>
<%@ page import= "controlador.Controller" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table style="width:100%">
            <tr>
                <th>lote</th>
                <th>usuario</th>
                <th>documento</th>
                <th>fecha</th>
                <th>estado</th>
                <th>soncronizar lote</th>
                <th>sincronizar usuario</th>
                
            </tr>
            <%=Controller.Pintarusuarios()%>
        </table>

    </body>
    <style>
table, th, td {
  border:1px solid black;
}
</style>
</html>
