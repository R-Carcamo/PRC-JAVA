<%-- 
    Document   : mostrarJSON
    Created on : 30 mar. 2023, 18:36:33
    Author     : recarcamo
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            HttpURLConnection cx = (HttpURLConnection) url.openConnection();
            cx.setRequestMethod("GET");

            InputStream strmContenido = cx.getInputStream();
            byte[] arrStream = strmContenido.readAllBytes();

            String cntJson = "";

            for (byte tmp : arrStream) {
                cntJson += (char) tmp;
            }

            JSONArray json = new JSONArray(cntJson);
            System.out.println("PRUEBA");
            for (Object obj : json) {
                
                out.println(((JSONObject) obj).get("name"));
            }
        %>
    </body>
</html>
