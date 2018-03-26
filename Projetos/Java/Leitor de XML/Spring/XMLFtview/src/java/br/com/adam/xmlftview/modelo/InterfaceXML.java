/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**Esta interface deve ser implementada pelas classe que desejam usar os objetos DOM.
 *
 * @author Adam
 */
public interface InterfaceXML {
    public abstract Document getDocumento();
    public abstract Node getRoot();
    public abstract void getFilhosRoot(Node node);
    public abstract void getAtributosRoot(Node node);
}
