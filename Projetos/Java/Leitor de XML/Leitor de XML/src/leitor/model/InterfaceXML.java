/*Esta interface faz o acoplamento do view com os dados lidos do xml*/
package leitor.model;

import java.util.ArrayList;
import org.w3c.dom.Node;

/*Criado em 14/02/18

 */
public interface InterfaceXML {

    public abstract ArrayList<Node> getTabela();

    public abstract String getDocumentosPai(int i);

    public abstract void setDocumentoPai(String documento);

    public abstract String procuraValor(String valor);

}
