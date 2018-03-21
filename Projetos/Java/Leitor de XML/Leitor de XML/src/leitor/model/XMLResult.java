package leitor.model;

import java.util.ArrayList;
import org.w3c.dom.Node;

public class XMLResult implements InterfaceXML {

    private ArrayList<String> retornoPai;

    @Override
    public ArrayList<Node> getTabela() {
        System.out.println("Iniciando array");
        return null;
    }

    @Override
    public String procuraValor(String valor) {
        System.out.println("Procurando pelo valor: " + valor
                + "\n\t O valor retornado será o nome do documento");
        return null;
    }

    @Override
    public void setDocumentoPai(String documento) {
        try {
            XMLResult.this.retornoPai.add(documento);
//            System.out.println("Setando pai: " + documento);
        } catch (Exception e) {
//            System.err.println("Erro em " + this.getClass());
            e.printStackTrace();
        }
    }

    @Override
    public String getDocumentosPai(int i) {

        try {
            System.out.println("Solicitando documento pai");
            return this.retornoPai.get(i);
        } catch (Exception e) {
            e.printStackTrace();
//            System.err.println("Erro em " + this);
            return "";
        }
    }

}
