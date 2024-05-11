/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;

/**
 *
 * @author Usuario
 */
public class calcula_edad extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet calcula_edad</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Edad Calculada</h1>");
            out.println("<p>");
            out.println("En: "+ request.getContextPath() ); 
            out.println("</p>"); 
            out.println("<p>");
            if (request.getAttribute("edad") != null) {
                out.print("Edad = " + request.getAttribute("edad"));
            }
            out.println("</p>");
            out.println("<p>"+this.getServletInfo() + "</p>");
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
    String strfecha = request.getParameter("fechanacimiento");

    if (strfecha == null) {
        processRequest(request, response);
    }

    Date fechaNac = null;
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaNac = sdf.parse(strfecha);
    } catch (ParseException ex) {
        // Manejo de la excepción: puedes imprimir un mensaje de error o realizar otra acción
        ex.printStackTrace();
    }

    if (fechaNac != null) {
        Calendar fechaNacimiento = Calendar.getInstance();
        Calendar fechaActual = Calendar.getInstance();
        fechaNacimiento.setTime(fechaNac);

        int edad = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

        // Se asigna la edad al atributo 'edad' en el request
        request.setAttribute("edad", edad);
    }
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "﻿\n" +
"\n" +
"Servlet que calcula la edad, pasando como parámetro la fecha de nacimiento (fechanacimiento-dd/MM/yyyy)";
    }// </editor-fold>

}
