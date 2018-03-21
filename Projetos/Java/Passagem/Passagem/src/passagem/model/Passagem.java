/*Esta classe é o DataModel dos objetos a serem inseridos na tabela (view)
principal do app*/
package passagem.model;

import javafx.beans.property.SimpleStringProperty;

public class Passagem {

    private SimpleStringProperty assunto;
    private SimpleStringProperty autor;
    private SimpleStringProperty data;
    private SimpleStringProperty tipo;

    public Passagem(String assunto, String autor, String data, String tipo) {
        this.assunto = new SimpleStringProperty(assunto);
        this.autor = new SimpleStringProperty(autor);
        this.data = new SimpleStringProperty(data);
        this.tipo = new SimpleStringProperty(tipo);
    }

    public String getAssunto() {
        return this.assunto.getValue();
    }

    public void setAssunto(SimpleStringProperty assunto) {
        this.assunto = assunto;
    }

    public String getAutor() {
        return this.autor.getValue();
    }

    public void setAutor(SimpleStringProperty autor) {
        this.autor = autor;
    }

    public String getData() {
        return this.data.getValue();
    }

    public void setData(SimpleStringProperty data) {
        this.data = data;
    }

    public String getTipo() {
        return this.tipo.getValue();
    }

    public void setTipo(SimpleStringProperty tipo) {
        this.tipo = tipo;
    }

}
