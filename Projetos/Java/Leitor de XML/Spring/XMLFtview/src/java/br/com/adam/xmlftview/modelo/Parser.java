package br.com.adam.xmlftview.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A classe Parser implementa uma interface para que se possa, através de um
 * dado arquivo XML obter-se o DOM já instanciado como objeto.
 *
 * @author Adam
 */
public final class Parser {

    public static File arquivoDeDespejo;

    private DocumentBuilderFactory construtorAnalisadorFactory;
    private DocumentBuilder construtorAnalisador;
    private Document analisadorXML;

    private static String pesquisa;
    private StringBuilder textoSaida;

    private Resultado resultado;

    ////
    private static int nivel = 0;
    private static long countTermo = 0;

    private static final String IDENT = "&ensp;";

    private static boolean detalhes = false;

    public Parser() {
        Parser.countTermo = 0;
        textoSaida = new StringBuilder();
    }

    /**
     * O construtor principal recebe um arquivo XML.
     *
     *
     * @param arquivo É o arquivo XML que deve ser passado para o parse
     * manipular. Deve ser do tipo {@link java.io.File}
     * @param pesquisa
     */
    public Parser(File arquivo, String pesquisa) {

        try {

            try {

                this.resultado = new Resultado();
                this.resultado.setThreadName(Thread.currentThread().getName());

                //Permite a criação do DOM
                this.construtorAnalisadorFactory = DocumentBuilderFactory.newInstance();
                this.construtorAnalisador = this.construtorAnalisadorFactory.newDocumentBuilder();
                //Analisador que fará a árvore do DOM
                this.analisadorXML = this.construtorAnalisador.parse(arquivo);
                this.textoSaida = new StringBuilder();
                Parser.pesquisa = pesquisa.toLowerCase();
                this.iniciaBusca();
//                System.out.println("Result: " + this.resultado);

//                System.out.println("\nPrimeiro quadro "
//                        + "\n\tArquivo: "
//                        + arquivo.getName()
//                        + "\n\tpesquisa: " + this.getPesquisa());
//                this.escreveTxt();
            } catch (IOException | SAXException e) {
                System.err.println("Erro de Parse 001");
            }

        } catch (ParserConfigurationException ex) {
            System.err.println("Erro de Parse 002");
        }
    }

    /**
     * Procura nos atributos o termo recebido externament.
     *
     * (1) - Ao ser instanciada, a classe aloca algumas variáveis como
     * isAtributoEncontrado, analisadorXML, pesquisa e textoSaida (2) - Ao ser
     * instanciada, a classe seta o valor de analisadorXML. Este é a raiz do
     * DOM, o Node que não tem pai. Acima dele só existe o nome do arquivo XML.
     * A partir deste momento haverá um conteúdo não nulo para o método
     * getDocumento (implementado de InterfaceXML. (3) - A pesquisa continua,
     * subindo a árvore do DOM, conseguindo todos os Nodes filhos do único
     * objeto que se tem assegurado existir, o Node raiz. A pesquisa usa o
     * método getRoot, também da interface para conseguir este objeto Node, e
     * utiliza o método getFilhosDoPrimogenito. (4) - Dentro de
     * getFilhosDoPrimogenito a pesquisa tenta obter a lista de Nodes filhos do
     * root, se tiver êxito percorre cada Node para saber se é um Element.
     * Apenas Nodes do tipo Element poderão ter as informações de atributos
     * relevante à pesquisa. (5) - A pesquisa entra em cada elemento tentando
     * conseguir uma lista de Attributos. De posse da lista de atributos a
     * pesquisa analisa o valor de cada um dos nós e compara aos termos de
     * pesquisa. Caso o termo seja encontrado, a pesquisa armazena o NodeName e
     * o NodeValue atual. (6) - Como o Node atual a pesquisa começa a se
     * deslocar para cima novamente, em direção ao root, utilizando o método
     * buscaTodosAtributos para encontrar cada Node ancestral de forma
     * hierárquica.
     *
     *
     */
    public void iniciaBusca() {

        /*O textoSaida é um objeto que aceita entre outros métodos o append().
        Este método tem uma especial funcionalidade, que é tokenizar a String*/
        textoSaida = new StringBuilder();

        //Assim que o Parser recebe o termo de pesquisa, ele chama o método getFilhosDoPrimogenito.
        //Porém para receber os nós filhos de root, tem-se que passar como argumento um nó
        //que seja o root do DOM. O root do DOM é o primeiro nó que aparece no documento.
//        System.out.println("Segundo quadro: \n\tNode raiz: "
//                + this.analisadorXML.getFirstChild().getNodeName());
        this.getFilhosDoPrimogenito(this.analisadorXML.getFirstChild());
    }

    /**
     * Este método permite que quem o implementa receba todo o DOM
     * detalhadamente aberto e lido hierarquicamente e com suas identações
     *
     * @param primogenito É o Node origem, de onde o arquivo começará a ser
     * lido. Normalmente é útil que seja passado o õde ∂ocument, pois é a raíz
     * do XML.
     *
     */
    public void getFilhosDoPrimogenito(Node primogenito) {

        /**
         * Um novo NodeList é instanciado a cada chamada do método filhosRoot.
         * Este método é primeiramente chamado fora da classe, por um objeto
         * instanciado do tipo Parse. Após este primeiro acesso o método é
         * recursivo, isto é, ele é chamado por si mesmo cada vez que um Element
         * é encontrado aninhado em um ChildNode.
         */
        try {
            NodeList listaFilhosPrimogenito = primogenito.getChildNodes();
            if (listaFilhosPrimogenito != null) {
                /**
                 * O método percorre todos os ChildNodes que existirem em um
                 * NOde, independente de que tipo é o Node. O que interessa para
                 * o método é identificar quais nodes têm filhos, e destes
                 * filhos quais têm filhos, e assim por diante.
                 */
                for (int n = 0; n < listaFilhosPrimogenito.getLength(); n++) {
                    Node elemento = listaFilhosPrimogenito.item(n);
                    if (elemento.getNodeType() == Node.ELEMENT_NODE) {

                        Node filhoDoPrimogenito = elemento;

//                        System.out.println("\nTerceiro quadro: "
//                                + "\n\tFilho do primogenito (elemento): "
//                                + elemento.getNodeName()
//                                + "\n\tvalor:" + elemento.getNodeValue());
                        //Pega uma lista com todos os elementos que tem atributos, 
                        //mas o que queremos é o elemento que tem o atributo correspondente
                        //à pesquisa
                        this.getAtributosFilhosPrimogenito(filhoDoPrimogenito);

                        try {
                            if (filhoDoPrimogenito.getChildNodes().getLength() > 0) {

                                //Chama novamente este método passando cada item de childNodes
                                this.getFilhosDoPrimogenito(filhoDoPrimogenito);

                            } else {
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        } catch (DOMException e) {
            System.err.println("_ERRO_ATRIB_PRIMOGENITO_");
        }

    }

    public void getAtributosFilhosPrimogenito(Node elementosFilhosDoPrimogenito) {

        for (int atrib = 0; atrib < elementosFilhosDoPrimogenito.getAttributes().getLength(); atrib++) {

//            System.out.println("\nQuarto quadro"
//                    + "\n\tAtributo do filho do primogenito (elemento): "
//                    + elementosFilhosDoPrimogenito.getNodeName()
//                    + "\n\tvalor:" + elementosFilhosDoPrimogenito.getNodeValue()
//            );
            String atribAtual = elementosFilhosDoPrimogenito.getAttributes().item(atrib).getNodeValue().toLowerCase();
            //System.out.println("Atual " + atribAtual);
            if (atribAtual.contains(pesquisa)) {
                //System.out.println("Atrib! " + atribAtual.toUpperCase());

                //Inserindo o Tag (primeiro termo)
                if (Parser.detalhes == true) {
                    textoSaida.append("<br><b>  ");
                    textoSaida.append("[").append(countTermo + 1).append("]\t");
                    this.resultado.setItem(elementosFilhosDoPrimogenito.getAttributes().item(atrib).getNodeValue());
                    this.resultado.setOriginalCount(countTermo);
                }

//                System.out.println("\nQuarto quadro (B) "
//                        + "\n\tAtributo bate com a pesquisa: "
//                        + elementosFilhosDoPrimogenito.getAttributes().item(atrib).getParentNode().getNodeName()
//                        + "\n\tvalor:" + elementosFilhosDoPrimogenito.getAttributes().item(atrib).getNodeValue()
//                );
                this.resultado.setItem(atribAtual);
                this.resultado.setOriginalCount(countTermo);

                textoSaida.append(elementosFilhosDoPrimogenito.getAttributes().item(atrib).getNodeValue());
                if (Parser.detalhes == true) {
                    textoSaida.append(Estaticos.NOVA_LINHA);
                }
                this.buscaTodosAtributos(elementosFilhosDoPrimogenito);
            }
        }
    }

    public void buscaTodosAtributos(Node paiDoAtributo) {

        if (Parser.detalhes == true) {
            textoSaida.append("</b>");
        }
        //Testa se o node passado tem ascendente
        if (paiDoAtributo.getParentNode() != null) {

            ++nivel;

            if (Parser.detalhes == true) {

                //Loop dos atributos dos ancestrais
                for (int atributo = 0; atributo < paiDoAtributo.getAttributes().getLength(); atributo++) {

                    //Inserindo os atributos do Tag principal
                    textoSaida.append(this.identar());
                    textoSaida.append(": ");
                    textoSaida.append(paiDoAtributo.getAttributes().item(atributo)).append(Estaticos.NOVA_LINHA);

                }

                textoSaida.append(Estaticos.NOVA_LINHA);
            }//Finaliza detalhes de atributo
            //Inserindo o nome do ascendente
            ++nivel;

            if (Parser.detalhes == true) {
                textoSaida.append(this.identar());
                textoSaida.append(paiDoAtributo.getParentNode().getNodeName()).append(Estaticos.NOVA_LINHA);
            }

            if (paiDoAtributo.getParentNode().getNodeName().contains("#document")) {
                ++nivel;
                textoSaida.append(this.identar());
                String arquivo
                        = new File(analisadorXML
                                .getFirstChild()
                                .getOwnerDocument()
                                .getDocumentURI())
                                .getName();

                //Inserindo o nome do arquivo (último item inserido)
                if (Parser.detalhes == true) {
                    textoSaida.append("<b>");
                    textoSaida.append(arquivo.replace(".xml", "").toUpperCase()).append(Estaticos.NOVA_LINHA);
                    textoSaida.append("</b>");
                    textoSaida.append("<br>");
                } else {
                    textoSaida.append("</td><td>");
                    textoSaida.append(arquivo.replace(".xml", "").toUpperCase()).append(Estaticos.NOVA_LINHA);
                    this.resultado.setTela(arquivo.replace(".xml", "").toUpperCase());

                    Resultado.listaResultados.add(this.resultado);
                    
                }

            }

            paiDoAtributo = paiDoAtributo.getParentNode();
            this.buscaTodosAtributos(paiDoAtributo);

        } else {
            ++nivel;

            if (Parser.detalhes == true) {
                textoSaida.append(this.identar()).append("<hr>");
            }
            countTermo++;
            nivel = 0;
        }
    }

    private void escreveTxt() {

        FileWriter fw;
        BufferedWriter bw;

        try {
            /**
             * O parâmetro true para FileWriter faz append no aquivo
             */

            String rootApp = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            arquivoDeDespejo = new File(rootApp + "/lib/txt/temp.txt");

            fw = new FileWriter(arquivoDeDespejo, true);
            bw = new BufferedWriter(fw);
            if (textoSaida != null) {
                bw.write(String.valueOf(textoSaida));
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.err.println("Erro ao escrever no temp");
        } catch (URISyntaxException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String identar() {
        String espaco = "";

        if (Parser.detalhes == true) {
            for (int _id = 0; _id < nivel; _id++) {
                espaco += "&ensp;";
            }
        }
        return espaco;
    }

    public void setTextoSaida() {
        this.textoSaida.setLength(0);
        this.textoSaida = new StringBuilder();
    }

    public static void setCountTermo(long countTermo) {
        Parser.countTermo = countTermo;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public static long getCounter() {
        return countTermo;
    }

    public String getTextoSaida() {
        return textoSaida.toString();
    }

}
