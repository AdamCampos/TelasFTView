/**
 *
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
 * Dentro desta classe de controle há HTML misturado a código Java. Toda classe
 * que herda HttpServlet precisa overridar um de seus métodos pelo menos. Esta
 * classe é instanciada quando ocorre um evento dispardo por um cliente HTTP. No
 * descritor web.xml foi indicdo que esta classe seria o Servlet chamado por
 * /control
 *
 * @author Adam
 */
public class ServletControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Teste de Servlet</title>");
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
            out.println("<th>Index</th>");
            out.println("<th>Nome do arquivo</th>");
            out.println("<th>Tamanho do arquivo</th>");
            out.println("<th>Root</th>");
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
                    p.setPesquisa("");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
