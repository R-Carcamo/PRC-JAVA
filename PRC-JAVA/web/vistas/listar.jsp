<%-- 
    Document   : listar
    Created on : 29 mar. 2023, 15:51:12
    Author     : recarcamo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Persona"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Personas</h1>
        <a href="Controlador?accion=agregar">Agregar Nuevo</a>
        <h2>${Prueba}</h2>
        
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
        <table border="1">
            <thead>
                <tr>
                    <th>Id User</th>
                    <th>Identificacion</th>
                    <th>Titulo</th>
                    <th>Cuerpo</th>
                </tr>
            </thead>
            <%
                PersonaDAO dao=new PersonaDAO();
                List<Persona> list=dao.listar();
                Iterator<Persona>iter=list.iterator();
                Persona per=null;
                while(iter.hasNext()){
                per=iter.next();
            %>
            <tbody>
                <tr>
                    <td><%= per.getIdUser()%></td>
                    <td><%= per.getDni()%></td>
                    <td><%= per.getTitulo()%></td>
                    <td><%= per.getCuerpo()%></td>
                    <td>
                        <a href="Controlador?accion=editar&userId=<%= per.getIdUser()%>">Editar</a>
                        <a>Quitar</a>
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
