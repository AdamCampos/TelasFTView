/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.modelo;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * A classe Parser implementa uma interface para que se possa, através de um
 * dado arquivo XML obter-se o DOM já instanciado como objeto.
 *
 * @author Adam
 */
public class Parser implements InterfaceXML {

    private static boolean isAtributoEncontrado;
    private static Document documentoXML;
    private static int nivel = 1;
    private String pesquisa;

    public void setPesquisa(String pesquisa) {

        if (pesquisa.isEmpty()) {
            this.pesquisa = "::";
        } else {
            this.pesquisa = pesquisa;
        }

        //Gambiarra para mostrar o arquivo
        this.getFilhosRoot(this.getRoot());

        if (isAtributoEncontrado) {

            System.out.println(ident + " \t\t\t\tdentro de <" + this.getRoot().getNodeName() + ">");
            File f = new File(documentoXML.getFirstChild().getOwnerDocument().getDocumentURI());
            System.out.println("---------------------------------------------------"
                    + "----------------------------------------------------------");
            System.out.println(ident + "\t   Arquivo atual:  " + f.getName().replace(".xml", "").toUpperCase());
            System.out.println("---------------------------------------------------"
                    + "----------------------------------------------------------");
        }
    }

    /**
     * O construtor principal recebe um arquivo XML.
     *
     *
     * @param arquivo É o arquivo XML que deve ser passado para o parse
     * manipular. Deve ser do tipo {@link java.io.File}
     */
    public Parser(File arquivo) {

        nivel = 1;
        isAtributoEncontrado = false;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            try {
                Document raizXML = db.parse(arquivo);
                documentoXML = raizXML;
            } catch (SAXException | IOException ex) {
                Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * O método getDocumento é overridado na interface. Ele possibilita para
     * quem o implementa, obter o documento XML já parseado.
     *
     * @return O objeto Document do DOM
     */
    @Override
    public Document getDocumento() {
        return documentoXML;
    }

    /**
     * Este método possibilita a quem o implementa receber o Node que é raiz de
     * todo o documento. Este root pode ter filhos e atributos.
     *
     * @return Retorna o Node raiz. Útil para saber o nome do Node raiz do
     * documento. Há apenas um root em XML validados (well-performed).
     */
    @Override
    public Node getRoot() {
        return documentoXML.getFirstChild();
    }

    /**
     * Este método permite que quem o implementa receba todo o DOM
     * detalhadamente aberto e lido hierarquicamente e com suas identações
     *
     * @param node É o Node origem, de onde o arquivo começará a ser lido.
     * Normalmente é útil que seja passado o õde ∂ocument, pois é a raíz do XML.
     *
     */
    @Override
    public void getFilhosRoot(Node node) {

        /*Um novo NodeList é instanciado a cada chamada do método filhosRoot. Este método é 
        primeiramente chamado fora da classe, por um objeto instanciado do tipo Parse. 
        Após este primeiro acesso o método é recursivo, isto é, ele é chamado por si mesmo
        cada vez que um Element é encontrado aninhado em um ChildNode.*/
        try {
            NodeList nl = node.getChildNodes();
            if (nl != null) {
                /*O método percorre todos os ChildNodes que existirem em um NOde, independente
                de que tipo é o Node. O que interessa para o método é identificar quais nodes
                têm filhos, e destes filhos quais têm filhos, e assim por diante.*/
                for (int n = 0; n < nl.getLength(); n++) {
                    if (nl.item(n).getNodeType() == 1) {
                        ident = "";
                        /*É exibida na tela a ordinalização do Node.*/
                        this.getAtributosRoot(nl.item(n));

                        try {
                            if (nl.item(n).getChildNodes().getLength() > 0) {
                                //Se o elemento tem filhos, aumenta a identação
                                nivel++;

                                //Chama novamente este método passando cada item de childNodes
                                this.getFilhosRoot(nl.item(n));
                            } else {
                            }
                        } catch (Exception e) {
                            System.err.println("Pulando...");
                        }
                    }
                }

                //Quando acaba a iteração a identação deve diminuir
                nivel--;
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void getAtributosRoot(Node n) {

        try {
            for (int atrib = 0; atrib < n.getAttributes().getLength(); atrib++) {

                if (n.getAttributes().item(atrib).getNodeValue().contains(this.pesquisa)) {
                    isAtributoEncontrado = true;
                    ident += "\t";
                    System.out.println("\n\nEncontrado  "
                            + n.getAttributes().item(atrib).getNodeValue()
                            + "  " + ident + "\n\tdentro de <"
                            + n.getNodeName() + ">");

                    this.getParent(n);
                }
            }
        } catch (DOMException e) {
        }
    }

    String ident = "";

    public void getParent(Node n) {
        /*Verifica se existe Parent para o Node passado no argumento*/
        if (n.getParentNode() != null) {
            /*Caso o node tenha um pai, verifica que tipo é. Se não for do tipo #document
            então é tentado buscar os atributos do elemento.
             */
            if (!n.getParentNode().getNodeName().contains("#document")) {
                String atributo = "";
                try {
                    NamedNodeMap nodeAtrib = n.getParentNode().getAttributes();
                    /*Se existirem atributos, os mesmo serão mostrados na tela*/
                    if (nodeAtrib.getLength() > 0) {
                        atributo = " : "
                                + n.getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                        ident += "     \t";
                    }
                } catch (DOMException e) {
                }
                System.out.println(ident + "\tdentro de <" + n.getParentNode().getNodeName()
                        + atributo + ">");

            } else if (n.getParentNode().getNodeName().contains("#document")) {

            }
            n = n.getParentNode();
            this.getParent(n);
        }
    }
}
