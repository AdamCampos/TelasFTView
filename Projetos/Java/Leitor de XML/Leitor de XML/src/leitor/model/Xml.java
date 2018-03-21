package leitor.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {

    private ArrayList<File> listaArquivosXML = new ArrayList();

    //O objeto xmlr carrega as informações amigévis obtidas durante 
    //a leitura (e parse) do XML
    private XMLResult xmlr;
    private static int nivel = 0;

    public ArrayList getListaArquivosXML() {
        return listaArquivosXML;
    }
    static int depthOfXML = 1;

    public Xml() throws SAXException {
        xmlr = new XMLResult();
        //Diretorio de origem
        //( ! ) Inserir seletor de diretório
        File pastaOrigem = new File("E:\\Adam\\Projetos\\TELAS XML");
        //Lista de arquivos no diretório
        File[] listaArquivos = pastaOrigem.listFiles();

        //Encontra os arquivos .xml
        for (File f : listaArquivos) {
            String nome = f.getName();
            //Pega a extensão do arquivo
            nome = nome.substring(nome.indexOf(".") + 1);
            if (nome.equals("xml") && !f.getName().contains("$")) {
                try {
                    System.out.println("Encontrado xml: " + f.getName());
                    this.listaArquivosXML.add(f);
                    this.dom(f);
                } catch (Exception ex) {
                    Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void dom(File xml) throws SAXException, IOException, ParserConfigurationException {

        //Primeiro passo é carregar o XML na memória
        // Passo 1 - DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // Passo 2 - DocumentBuilder
        DocumentBuilder db;
        db = dbf.newDocumentBuilder();
        // Passo 3 - Parse (particionamentos)
        Document doc = db.parse(xml);

        //Assim que se consegue o Parse, tem-se a Interface Document.
        //Desta forma é possível caminhar pela árvore do DOM
//-----------------------------------------------------------------------------------------------------------------
        Element n0 = doc.getDocumentElement();
        this.linha();

        if (n0.hasChildNodes()) {
            nivel++;
            //A partir deste ponto pode existir Nodelists.
            NodeList n1 = n0.getChildNodes();
            printNode(n1, 0);
        }

    }

    private void linha() {
        System.out.println("______________________________________________________\n");
    }

    private int printNode(NodeList nodeList, int level) {
        level++;

        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodePrint = nodeList.item(i);

                //Itera por todos os nodes até encontrar um Element
                //Quando encontra um Element o método é chamando novamente
                //com a lista de ChildNodes passada como argumento
                if (nodePrint.getNodeType() == Node.ELEMENT_NODE) {
                    if (nodePrint.hasAttributes()) {
                        NamedNodeMap atributos = nodePrint.getAttributes();
                        for (int j = 0; j < atributos.getLength(); j++) {
                            String valorAtributo = atributos.item(j).toString();
                            if (valorAtributo.contains("::")) {
                                //Se for encontrado um valor igual à pesquisa, a interface recebe
                                //o nome do arquivo xml onde está contida
                                File arquivo = this.listaArquivosXML.get(this.listaArquivosXML.size() - 1);
                               
                                this.xmlr.setDocumentoPai("pppp");
                            }
                        }
                    }
                    printNode(nodePrint.getChildNodes(), level);
                    if (level > depthOfXML) {
                        depthOfXML = level;
                    }
                }
            }
        }
        return depthOfXML;

    }

    public XMLResult getXmlr() {
        return xmlr;
    }

}
