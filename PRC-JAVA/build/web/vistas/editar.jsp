<%-- 
    Document   : editar
    Created on : 29 mar. 2023, 15:51:38
    Author     : recarcamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modificar Persona</h1>
        <form action="Controlador" method="Post">
            Título:<br><!-- comment -->
            <input type="text" name="txtTitulo" value=""><br>
            Cuerpo:<br><!-- comment -->
            <input type="text" name="txtCuerpo" value=""><br>
            Id:<br><!-- comment -->
            <input type="text" name="txtUserId"><br>
            <input type="hidden" name="idUser" value="${idUser}"><br>
            <input type="submit" name="accion" value="Actualizar"><br>
        </form>
    </body>
</html>
