package passagem.model.auxiliar;

import java.io.File;
import java.io.IOException;
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

    static int depthOfXML = 1;

    public Xml() throws SAXException {
        //Diretorio de origem
        File pastaOrigem = new File("E:\\Adam\\Projetos");
        //Lista de arquivos no diretório
        File[] listaArquivos = pastaOrigem.listFiles();

        //Encontra os arquivos .xml
        for (File f : listaArquivos) {
            String nome = f.getName();
            //Pega a extensão do arquivo
            nome = nome.substring(nome.indexOf(".") + 1);
            //Zipa todosEncontra os arquivos .xml (mas não os temporários)
            if (nome.equals("xml") && !f.getName().contains("$")) {
                try {
                    System.out.println("Encontrado xml: " + f.getName());
                    this.dom(f);
                } catch (IOException ex) {
                    Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void dom(File xml) throws SAXException, IOException {

        //Primeiro passo é carregar o XML na memória
        // Passo 1 - DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // Passo 2 - DocumentBuilder
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            // Passo 3 - Parse (particionamentos)
            Document doc = db.parse(xml);

            //Assim que se consegue o Parse, tem-se a Interface Document.
            //Desta forma é possível caminhar pela árvore do DOM
//-----------------------------------------------------------------------------------------------------------------
            Element n0 = doc.getDocumentElement();
            System.out.println("Encontrados " + n0.getChildNodes().getLength() + " elementos após raiz");
            this.linha();
            int nivel = 0;
//Nível 0 - Nodes/Attributes
            this.buscaRaizChildren(n0);
            this.buscaAtributos(n0);
//-----------------------------------------------------------------------------------------------------------------            
//Nível 1 - Nodes/Attributes
            if (n0.hasChildNodes()) {
                nivel++;
                NodeList n1 = n0.getChildNodes();
                for (int i = 0; i < n1.getLength(); i++) {
                    this.buscaNodesFilhos(n1, i, nivel);
                    this.buscaNodesAtributos(n1, i, nivel);
//-----------------------------------------------------------------------------------------------------------------  
//Nível 2 - Nodes/Attributes
                    if (n1.item(i).hasChildNodes()) {
                        nivel++;
                        NodeList n2 = n1.item(i).getChildNodes();
                        for (int j = 0; j < n2.getLength(); j++) {
                            this.buscaNodesFilhos(n2, j, nivel);
                            this.buscaNodesAtributos(n2, j, nivel);
//-----------------------------------------------------------------------------------------------------------------  
//Nível 3 - Nodes/Attributes
                            if (n2.item(j).hasChildNodes()) {
                                nivel++;
                                NodeList n3 = n2.item(j).getChildNodes();
                                for (int k = 0; k < n3.getLength(); k++) {
                                    this.buscaNodesFilhos(n3, k, nivel);
                                    this.buscaNodesAtributos(n3, k, nivel);
//----------------------------------------------------------------------------------------------------------------- 
//Nível 4 - Nodes/Attributes
                                    if (n3.item(k).hasChildNodes()) {
                                        nivel++;
                                        NodeList n4 = n3.item(k).getChildNodes();
                                        for (int l = 0; l < n4.getLength(); l++) {
                                            this.buscaNodesFilhos(n4, l, nivel);
                                            this.buscaNodesAtributos(n4, l, nivel);
//----------------------------------------------------------------------------------------------------------------- 
//Nível 5 - Nodes/Attributes
                                            if (n4.item(l).hasChildNodes()) {
                                                nivel++;
                                                NodeList n5 = n4.item(l).getChildNodes();
                                                for (int m = 0; m < n5.getLength(); m++) {
                                                    this.buscaNodesFilhos(n5, m, nivel);
                                                    this.buscaNodesAtributos(n5, m, nivel);
//----------------------------------------------------------------------------------------------------------------- 
//Nível 6 - Nodes/Attributes
                                                    if (n5.item(m).hasChildNodes()) {
                                                        nivel++;
                                                        NodeList n6 = n5.item(m).getChildNodes();
                                                        for (int n = 0; n < n6.getLength(); n++) {
                                                            this.buscaNodesFilhos(n6, n, nivel);
                                                            this.buscaNodesAtributos(n6, n, nivel);
//----------------------------------------------------------------------------------------------------------------- 
//Nível 7 - Nodes/Attributes
                                                            if (n6.item(n).hasChildNodes()) {
                                                                nivel++;
                                                                NodeList n7 = n6.item(n).getChildNodes();
                                                                for (int o = 0; o < n7.getLength(); o++) {
                                                                    this.buscaNodesFilhos(n7, o, nivel);
                                                                    this.buscaNodesAtributos(n7, o, nivel);
//----------------------------------------------------------------------------------------------------------------- 
//Nível 8 - Nodes/Attributes
                                                                    if (n7.item(o).hasChildNodes()) {
                                                                        nivel++;
                                                                        NodeList n8 = n7.item(o).getChildNodes();
                                                                        for (int p = 0; p < n7.getLength(); p++) {
                                                                            this.buscaNodesFilhos(n8, p, nivel);
                                                                            this.buscaNodesAtributos(n8, p, nivel);
                                                                        }
                                                                        nivel--;
                                                                    }
                                                                }
                                                                nivel--;
                                                            }
                                                        }
                                                        nivel--;
                                                    }
                                                }
                                                nivel--;
                                            }
                                        }
                                        nivel--;
                                    }
                                }

                                nivel--;
                            }
                        }
                        nivel--;
                    }
                }
                nivel--;
                System.out.println("Profundidade: " + this.printNode(n1, 1));
            }

//------------------------------------------------------------------------------------------------------------------
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Erro");
        }

    }

    private void addNode() {
    }

    private void linha() {
        System.out.println("______________________________________________________\n");
    }

    private void buscaAtributos(Element raiz) {
        if (raiz.hasAttributes()) {
            NamedNodeMap atributos = raiz.getAttributes();
            for (int i = 0; i < atributos.getLength(); i++) {
                System.out.println("   ." + atributos.item(i));
            }
        }
    }

    private void buscaRaizChildren(Element raiz) {
        if (raiz.hasChildNodes()) {
            System.out.println("(+)" + raiz.getNodeName());
        }
    }

    private void buscaNodesFilhos(NodeList nl, int i, int nivel) {

        String espaço = "";
        for (int n = 0; n < nivel; n++) {
            espaço = espaço + "\t";
        }

        if (nl.item(i).hasChildNodes()) {
            //Cada childNode encontrado em n0
            System.out.println(espaço + "(+)"
                    + " " + nl.item(i).getNodeName());
        } else if (nl.item(i).getNodeName().contains("#")) {
        } else {
            System.out.println(espaço + "(-)"
                    + " " + nl.item(i).getNodeName());
        }
    }

    private void buscaNodesAtributos(NodeList n1, int i, int nivel) {

        String espaço = "";
        for (int n = 0; n < nivel; n++) {
            espaço = espaço + "\t";
        }

        if (n1.item(i).hasAttributes()) {
            NamedNodeMap atributos = n1.item(i).getAttributes();
            for (int j = 0; j < atributos.getLength(); j++) {
                System.out.println(espaço + " ." + atributos.item(j));
            }
        }
    }

    private static int printNode(NodeList nodeList, int level) {
        level++;
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    printNode(node.getChildNodes(), level);
                    if (level > depthOfXML) {
                        depthOfXML = level;
                    }
                }
            }
        }
        return depthOfXML;

    }
}
