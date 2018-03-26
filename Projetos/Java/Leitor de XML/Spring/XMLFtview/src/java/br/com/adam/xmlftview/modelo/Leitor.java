/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.modelo;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * Classe responsável por encontrar e abrir arquivos XML. <br>
 * Assim que abertos, os arquivos são conferidos quanto ao tipo. <br>
 * Se o arquivo encontrado for um .xml o mesmo é inseridos em um array de XMLs.
 * <br><br>
 *
 *
 *
 * @author Adam
 */
public class Leitor {

    private File[] listaArquivosXML;

    /**
     * Construtor padrão. Ao ser instanciada a classe, uma caixa de diálogo é
     * exibida para o usuário poder escolher o local onde procurar os arquivos
     * XML.
     */
    public Leitor() {
        try {
            final JFileChooser fc = new JFileChooser();
            //Apenas diretórios são permitidos de serem selecionados
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //O parâmetro passado no argumento é o local onde a procura deve começar
            fc.setCurrentDirectory(new File(
                    "/Users/Adam/Documents/Adam_Java/TelasFTView/Projetos/Java"
                            + "/Leitor de XML/Spring/XMLFtview/web/resources"));
            //Abre a caixa de seleção
            fc.showOpenDialog(null);
            //File retorno é uma lista de arquivos (e diretórios)
            File retorno = fc.getSelectedFile();
            //Passa para a variável de classe o valor obtido da lista de arquivos
            this.listaArquivosXML = retorno.listFiles();
            for (File f : retorno.listFiles()) {
                System.out.println("Arquivo encontrado " + f.getCanonicalPath());
            }
            
        } catch (HeadlessException | IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Recebe todos os arquivos xml do diretório apontado.
     *
     *
     * @return Lista de arquivos XML
     */
    public File[] getListaArquivosXML() {

        return listaArquivosXML;
    }

}
