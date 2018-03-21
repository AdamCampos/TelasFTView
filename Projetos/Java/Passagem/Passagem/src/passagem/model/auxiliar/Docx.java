package passagem.model.auxiliar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Docx {

    public Docx() {
        //Diretorio de origem
        File pastaOrigem = new File("E:\\Adam\\Projetos");
        //Lista de arquivos no diretório
        File[] listaArquivos = pastaOrigem.listFiles();

        //Encontra os arquivos .docx
        for (File f : listaArquivos) {
            String nome = f.getName();
            //Pega a extensão do arquivo
            nome = nome.substring(nome.indexOf(".") + 1);
            //Zipa todos os arquivos .docx (mas não os temporários)
            if (nome.equals("docx") && !f.getName().contains("$")) {
                System.out.println("Arquivo filtrado: " + f);
                try {
                    //Cria o novo nome do arquivo (zipa)
                    File fn = new File("E:\\Adam\\Projetos\\" + f.getName() + ".zip");
                    System.out.println("Nome novo: " + fn);
                    //Cria os caminhos (streams) para que haja a cópia
                    InputStream is = null;
                    OutputStream os = null;
                    //Tenta fazer a cópia do arquivo para o mesmo diretório, mas a cópia 
                    //já vai zipada devido ao nome do aqruivo mudada acima
                    try {
                        is = new FileInputStream(f);
                        os = new FileOutputStream(fn);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                      //Fecha os arquivos streaming  
                    } finally {
                        is.close();
                        os.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Docx.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
