<%-- 
    Document   : agregar
    Created on : 29 mar. 2023, 15:51:26
    Author     : recarcamo
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Agregar Persona</h1>
        <form action="Controlador" method="Post">
            Título:<br><!-- comment -->
            <input type="text" name="txtTitulo"><br>
            Cuerpo:<br><!-- comment -->
            <input type="text" name="txtCuerpo"><br>
            User Id:<br><!-- comment -->
            <input type="text" name="txtUserId"><br>
            <input type="submit" name="accion" value="Insertar"><br>
        </form>
        
        <h1>RESULTADO DEL POST</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Código Usuario</th>
                    <th>Nombre</th>
                    <th>Nombre Usuario</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%List<Persona> lstAtributos = (List)request.getAttribute("atributos");
                for(Persona str : lstAtributos){
                        
                %>
                <tr>
                    <td><%= str.getIdUser()%></td>
                    <td><%= str.getDni()%></td>
                    <td><%= str.getTitulo()%></td>
                    <td><%= str.getCuerpo()%></td>
                    <td>
                        <a>Editar</a>
                        <a>Quitar</a>
                    </td>
                </tr>
                
                <%
                    }
                %>
            </tbody>
            
        </table>
    </body>
</html>
