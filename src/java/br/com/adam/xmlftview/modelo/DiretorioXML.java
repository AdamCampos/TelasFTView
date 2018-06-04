package br.com.adam.xmlftview.modelo;

import java.awt.EventQueue;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 * Classe responsável por encontrar e abrir arquivos XML. <br>
 * Assim que abertos, os arquivos são conferidos quanto ao tipo. <br>
 * Se o arquivo encontrado for um .xml o mesmo é inseridos em um array de XMLs.
 * <br><br>
 *
 * @author Adam
 */
public class DiretorioXML {

    private File retorno;
    private File diretorioEscolhido;
    private final List<File> listaArquivosXML;

    public DiretorioXML() {
        this.listaArquivosXML = new ArrayList();
    }

    /**
     * Recebe todos os arquivos xml do diretório apontado. Esta lista será usada
     * no Servlet (view) e no model (DOM)
     *
     * @return Lista de arquivos XML
     */
    public ArrayList<File> getListaArquivosXML() {
        return (ArrayList) listaArquivosXML;
    }

    public File getRetorno() {

        if (this.listaArquivosXML.size() > 0) {
            return this.diretorioEscolhido;
        } else {
            System.err.println("Não há itens na lista XML filtrada");
            return null;
        }

    }

    private static void setDiretorioEscolhido(File diretorio) {

    }

    public void setRetorno(File retorno) {

        this.retorno = retorno;

        //Passa para a variável de classe o valor obtido da lista de arquivos
        //Loop através de todos os arquivos do diretório
        for (File arquivoDoDiretorio : this.retorno.listFiles()) {
            if (arquivoDoDiretorio.getName().endsWith(".xml")) {
                listaArquivosXML.add(arquivoDoDiretorio);
            }
        }
    }

    public ArrayList<File> escolheDiretorio() {
        /**
         * Abre a caixa de seleção O artifício da thread foi necesário, pois no
         * MAC OS o JFileChooser nåo aparece após primeira chamada.
         */
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JFileChooser fc = new JFileChooser();
                    //Apenas diretórios são permitidos de serem selecionados
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    //Seta o parâmetro do local onde o filechooser irá iniciar.
                    fc.setCurrentDirectory(new File("/Users/Adam/Desktop/"));
                    fc.showOpenDialog(null);
                    DiretorioXML.setDiretorioEscolhido(fc.getSelectedFile().getAbsoluteFile());
                    DiretorioXML.this.setRetorno(fc.getSelectedFile());
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            System.err.println("Erro no FileChooser do ControleDOM");
        }
        return this.getListaArquivosXML();
    }

}
