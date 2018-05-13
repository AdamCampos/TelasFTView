/**
 *
 */
package br.com.adam.xmlftview.controller;

import br.com.adam.xmlftview.modelo.DiretorioXML;
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

        /*Passa para a classe DiretorioXML o local pré configurado onde estão os XMLs*/
        ///Leitor.setDiretorioRaiz(getServletContext().getInitParameter("diretorio"));

        try (PrintWriter out = response.getWriter()) {
            cabeçalho(out);

            //Instancia um objeto leitor. Este objeto traz a lista de arquivos encontrados.
            final DiretorioXML leitor = new DiretorioXML();
            int i = 0;

            //Faz loop por todos os arquivos XML encontrados no diretório
            for (File f : leitor.getListaArquivosXML()) {
                i++;
                System.out.println(String.valueOf(i * 100 / leitor.getListaArquivosXML().size()) + "%");
                //Inicia uma linha
                out.println("<tr>");
                out.println("<td>");
                out.println(i);
                out.println("</td>");
                //Inicia uma célula
                out.println("<td>");
                //Exibe o nome do arquivo na célula, retirando a extensão
                out.println(f.getName().replace(".xml", ""));
                out.println("</td>");
                //Preenche provisoriamente a segunda coluna
                out.println("<td>");
                out.println(f.length() / 1024 + "kB");
                out.println("</td>");
                out.println("<td>");

                //Inicia um parser para o arquivo atual encontrado
                Parser p = new Parser(f,"");
                out.println("</td>");
                out.println("</tr>");
            }
            System.out.println("Fim do Servlet");
            out.println("</td>");
            out.println("</table>");
            out.println("\n-----------------------------------------------------"
                    + "---------------------------------------------------------"
                    + "---------------------------------------------------------");
            out.println("</body>");
            out.println("</html>");
            this.destroy();

        } catch (Exception e) {
            System.err.println("Erro no ServletControl " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Apenas um método para melhorar a leitrura do HTML do Servlet
     */
    private static void cabeçalho(PrintWriter out) {
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
    }
}
