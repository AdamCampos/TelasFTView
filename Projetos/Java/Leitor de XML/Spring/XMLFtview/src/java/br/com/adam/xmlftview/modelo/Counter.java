/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.modelo;

/**
 *
 * @author Adam
 */
public class Counter {
    private static int count;

    public static int getCount() {
        count++;
        return count;
    }

    public static void setCount(int aCount) {
        count = aCount;
    }
    
}
