/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.controller;

import br.com.adam.xmlftview.modelo.DiretorioXML;
import br.com.adam.xmlftview.modelo.Parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Adam
 */
public class ServletPesquisa extends HttpServlet {

    private static int arquivos = 0;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static PrintWriter out;

    protected void processRequest(final HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        arquivos = 0;
        System.out.println("Session ID: " + session.getId());

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
        System.out.println("ServletPesquisa recebendo GET " + request);
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
    protected void doPost(final HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        System.out.println("Recebendo Post: " + request.getServletPath());

        out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Teste de Servlet</title>");
        out.println("<style type=\"text/css\">");
        out.println("td,th{border: 1px solid black;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        ////Leitor.setDiretorioRaiz(getServletContext().getInitParameter("diretorio"));
        //Instancia um objeto leitor. Este objeto traz a lista de arquivos encontrados.
        DiretorioXML leitor = new DiretorioXML();
        final int numeroArquivos = leitor.getListaArquivosXML().size();

        //Conteúdo da tabela
        for (final File f : leitor.getListaArquivosXML()) {
            Runnable thread = new Runnable() {
                @Override
                public void run() {
                    arquivos++;
                    //Para cada arquivo encontrado é instanciado um objeto Parse
                    Parser p = new Parser(f, request.getParameter("termoPesquisa"));
                    System.out.println(100 * arquivos / numeroArquivos);
                    //out.print(100 * arquivos / numeroArquivos);
                    out.flush();
                }
            };
            thread.run();
        }
        /**
         * Inicia a leitura do arquivo output.txt
         */
        this.tokenizer();

        //Impresso após todo o conteúdo do tokenizer
        out.println("FIM");
        out.println("<hr>");
        out.println("</body>");
        out.println("</html>");

        this.init();

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

    /**
     * Este método instancia um objeto BufferedReader que receberá o arquivo
     * temporário com os resultados da busca. Este arquivo é utiizado como
     * despejo de memória do DOM. Dentro deste método o arquivo de texto é lido
     * linha a linha. Tokenizer é analisar cada String em busca de tokens
     * conhecidos. Cada termo encontrado na busca tem um caracter
     * <b>"|"</b> inserido para marcar o fim daquela busca, e cada caracter "?"
     * indica uma quebra de linha no HTML de saída.
     */
    private void tokenizer() {

        BufferedReader br;
        int contaItens = 0;
        try {
            Parser parser = new Parser();
            br = new BufferedReader(new FileReader(Parser.arquivoDeDespejo));
            String strB;
            try {
                //Lê cada linha até a última, qunado readline retorna null
                while ((strB = br.readLine()) != null) {
                    if (!strB.isEmpty()) {

                        //Separador de novas linhas (documento). Cada vez que encontra 
                        //a "|" instancia-se um novo pedaço de String para se analisar.
                        StringTokenizer strT = new StringTokenizer(strB, "|");

                        //Analisa todos os pedaços tokenizados por "|"
                        while (strT.hasMoreElements()) {

                            String _s0 = (String) strT.nextElement();

                            if (_s0.contains("?!")) {
                                contaItens++;
                                out.println("[" + contaItens + "]");
                            }
                            //Se encontrar na String um "?!", criará uma nova linha no HTML
                            out.println(_s0.replace("?!", "<br>"));
                            out.println("<br>");
                            out.flush();
                        }

                        out.println("Número de termos encontrados: " + new Parser().getCounter() + "<br>");
                        out.println();
                        out.println("<br>");
                    }
                }
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(ServletPesquisa.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Fim de leitura do arquivo TEMP");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServletPesquisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
