/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Persona;
import ModeloDAO.PersonaDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import static java.lang.System.out;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author recarcamo
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String listar = "vistas/listar.jsp";
    String add = "vistas/agregar.jsp";
    String edit = "vistas/editar.jsp";

    Persona p = new Persona();
    PersonaDAO pdao = new PersonaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {

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
            
            ArrayList<Persona>atributos=new ArrayList<>();
            
            for (Object obj : json) {
                
                Persona per = new Persona();
                per.setIdUser((int) ((JSONObject) obj).get("id"));
                per.setDni(100);
                per.setTitulo((String) ((JSONObject) obj).get("username"));
                per.setCuerpo((String) ((JSONObject) obj).get("email"));
                atributos.add(per);
                //((JSONObject) obj).get("name");
            }
            
            request.setAttribute("atributos", atributos);
            acceso = listar;

        } else if (action.equalsIgnoreCase("agregar")) {
            
            ArrayList<Persona>atributos=new ArrayList<>();
            request.setAttribute("atributos", atributos);
            acceso = add;

        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idUser", request.getParameter("userId"));
            acceso = edit;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
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
        
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("Insertar")) {
            
            int dni = Integer.parseInt(request.getParameter("txtUserId").toString());
            String titulo = request.getParameter("txtTitulo");
            String cuerpo = request.getParameter("txtCuerpo");
            p.setDni(dni);
            p.setTitulo(titulo);
            p.setCuerpo(cuerpo);
            
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("title",titulo);
            params.put("body",cuerpo);
            params.put("userId",dni);
            
            StringBuilder postData = new StringBuilder();
            
            for(Map.Entry<String, Object> param : params.entrySet()){
                if(postData.length() != 0){
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            
            byte[] postBytes = postData.toString().getBytes("UTF-8");
            
            HttpURLConnection cx = (HttpURLConnection) url.openConnection();
            cx.setRequestMethod("POST");
            cx.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            cx.setRequestProperty("Content-Length", String.valueOf(postBytes.length));
            cx.setDoOutput(true);
            cx.getOutputStream().write(postBytes);
            
            //Recepcionar respuesta
            Reader in = new  BufferedReader(new InputStreamReader(cx.getInputStream(),"UTF-8"));
            String cntJson = "";
            ArrayList<Persona>atributos=new ArrayList<>();
            
            for(int c = in.read(); c !=-1; c = in.read()){
                cntJson += (char) c;
            }
            
            JSONObject json = new JSONObject(cntJson);
            request.setAttribute("Prueba2", json);
              
                Persona per = new Persona();
                per.setDni(Integer.valueOf((String) json.get("userId")));
                per.setTitulo((String) json.get("title"));
                per.setCuerpo((String) json.get("body"));
                atributos.add(per);
                pdao.add(per);
                request.setAttribute("atributos", atributos);
                acceso = listar;
        }else if (action.equalsIgnoreCase("Actualizar")){
            int idUser = Integer.parseInt(request.getParameter("idUser").toString());
            int dni = Integer.parseInt(request.getParameter("txtUserId").toString());
            String titulo = request.getParameter("txtTitulo");
            String cuerpo = request.getParameter("txtCuerpo");
            p.setIdUser(idUser);
            p.setDni(dni);
            p.setTitulo(titulo);
            p.setCuerpo(cuerpo);
            pdao.edit(p);
            ArrayList<Persona>atributos=new ArrayList<>();
            request.setAttribute("atributos", atributos);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
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
