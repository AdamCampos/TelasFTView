package br.com.adam.xmlftview.controller;

import br.com.adam.xmlftview.modelo.DiretorioXML;
import br.com.adam.xmlftview.modelo.Parser;
import br.com.adam.xmlftview.modelo.Resultado;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Adam
 */
public final class ControleDOM extends SimpleTagSupport {

    private String diretorioDePesquisaInicial;
    private static String pesquisa;
    private ArrayList<File> listaArquivosXML;

    private DiretorioXML leitorControle;

    private ThreadGroup thG = new ThreadGroup("grupoThreads");
    static Thread thread;
    static int nomeThread;

    public ControleDOM() {

        this.leitorControle = new DiretorioXML();
        this.thG = new ThreadGroup("grupoThreads");
        Resultado.listaResultados.clear();

    }

    public String getDiretorioDePesquisaInicial() {
        return this.diretorioDePesquisaInicial;
    }

    //javaBean (usado pela JSP)
    public String getPesquisa() {
        return ControleDOM.pesquisa;
    }

    //javaBean (usado pela JSP)
    public void setPesquisa(String pesquisa) {
        ControleDOM.pesquisa = pesquisa;
        System.out.println("Pesquisa setada pela JSP " + pesquisa);
    }

    public void setLeitorControle(DiretorioXML leitorControle) {
        this.leitorControle = leitorControle;
    }

    private void iniciaParser() {

        //Conteúdo da tabela
        nomeThread = 0;
        for (final File xml : this.listaArquivosXML) {
            nomeThread++;
            ControleDOM.thread = new Thread(this.thG, "threadBusca_" + String.valueOf(nomeThread)) {
                @Override
                public void run() {
                    //Para cada arquivo encontrado é instanciado um objeto Parse
                    Parser p = new Parser(xml, ControleDOM.pesquisa);
                }
            };
            ControleDOM.thread.start();
        }
    }

    @Override
    public void doTag() throws JspException, IOException {

        StringWriter sw = new StringWriter();

        JspWriter out = getJspContext().getOut();

        JspFragment jspf = getJspBody();

        //Através do objeto DiretorioXML instanciado se obtém a lista filtrada
        //de arquivos XML, dada a escolha de diretório feita pelo usuário
        this.listaArquivosXML = this.leitorControle.escolheDiretorio();

        //Este é o método principal para se obter as respostas da pesquisa. Para que
        //a pesquisa funcione corretamente o Parser deve receber três objetos:
        //Um termo a se pesquisar, um arquivo xml e uma lista de retorno, onde as
        //respostas serão armazenadas. O termo de pesquisa é obtido ainda na JSP através
        //de javaBean. O arquivo de despejo foi obtido na instanciação desta classe, 
        //também pela JSP. O arquivo XML é passado um a um pelo método abaixo.
        this.iniciaParser();

        //Importante manter este loop. Isto assegura que todas a threads que foram lançadas
        //para pesquisa terminaram sua função
        while (this.thG.activeCount() > 0) {
        }

        int rCount = 0;
        Iterator<Resultado> iterator = Resultado.listaResultados.iterator();
        while (iterator.hasNext()) {

            rCount++;
            Resultado r = iterator.next();

            try {

                getJspBody().getJspContext().setAttribute("id", rCount);
                getJspBody().getJspContext().setAttribute("item", r.getItem());
                getJspBody().getJspContext().setAttribute("tela", r.getTela());

            } catch (Exception npe) {
                System.out.println("Null resposta");
            }

            if (jspf != null) {
                jspf.invoke(out);
            }

//            getJspBody().getJspContext().setAttribute("termo", this.getPesquisa());
//          getJspBody().getJspContext().setAttribute("itensRetorno", Resultado.listaResultados.size());
        }
    }

}
