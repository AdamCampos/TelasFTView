/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.modelo;

import java.io.File;
import java.util.HashSet;

/**
 *
 * @author Adam
 */
public class Resultado {

    private String item;
    private String tela;
    public static HashSet listaResultados = new HashSet<Resultado>();
    private File localBusca;
    private String threadName;

    private long originalCount;

    private long itemResultado;

    public Resultado() {

    }

    public long getOriginalCount() {
        return originalCount;
    }

    public void setOriginalCount(long originalCount) {
        this.originalCount = originalCount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTela() {
        return tela;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }

    public static HashSet<Resultado> getListaResultados() {
        return listaResultados;
    }

    public File getLocalBusca() {
        return localBusca;
    }

    public void setLocalBusca(File localBusca) {
        this.localBusca = localBusca;
    }

    public long getItemResultado() {
        return itemResultado;
    }

    public void setItemResultado(long itemResultado) {
        this.itemResultado = itemResultado;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public String toString() {
        return "[original:" + this.getOriginalCount()
                + "] [tela:" + this.getTela()
                + "] [item:" + this.getItem() + "]";
    }

}
