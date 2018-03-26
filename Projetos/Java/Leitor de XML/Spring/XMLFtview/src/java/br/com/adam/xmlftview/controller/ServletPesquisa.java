/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.controller;

import br.com.adam.xmlftview.modelo.Leitor;
import br.com.adam.xmlftview.modelo.Parser;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Adam
 */
public class ServletPesquisa extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Pesquisa Termo</title>");
            out.println("<style type=\"text/css\">");
            out.println("td,th{border: 1px solid black;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            //Inicia uma tabela para receber a lista de arquivos encontrados.
            out.println("<table>");
            //Inicia uma linha. Esta linha será a de cabeçalho
            out.println("<tr>");
            //Este é o cabeçalho da coluna (há duas colunas)
            out.println("<th>xxxx</th>");
            out.println("<th>xxxx</th>");
            out.println("<th>xxxx</th>");
            out.println("<th>xxxx</th>");
            //Fim da linha cabeçalho
            out.println("</tr>");

            //Instancia um objeto leitor. Este objeto traz a lista de arquivos encontrados.
            Leitor leitor = new Leitor();
            int i = 0;

            for (File f : leitor.getListaArquivosXML()) {
                if (f.getName().contains(".xml")) {
                    i++;
                    //Inicia uma linha
                    out.println("<tr>");
                    out.println("<td>");
                    out.println(i);
                    out.println("</td>");
                    //Inicia uma célula
                    out.println("<td>");
                    //Exibe o nome do arquivo na célula
                    out.println(f.getName().replace(".xml", ""));
                    out.println("</td>");
                    //Preenche provisoriamente a segunda coluna
                    out.println("<td>");
                    out.println(f.length() / 1024 + "kB");
                    out.println("</td>");
                    out.println("<td>");
                    Parser p = new Parser(f);
                    p.setPesquisa(request.getParameter("termoPesquisa"));
                    out.println(p.getRoot().getNodeName());
                    out.println("</td>");
                    out.println("</tr>");

                }
            }
            out.println("</td>");
            out.println("</table>");
            out.println("\n-----------------------------------------------------"
                    + "---------------------------------------------------------"
                    + "---------------------------------------------------------");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
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
        return "Short description";
    }// </editor-fold>

}
