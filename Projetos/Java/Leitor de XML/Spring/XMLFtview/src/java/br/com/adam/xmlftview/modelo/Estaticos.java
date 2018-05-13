package br.com.adam.xmlftview.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Estaticos {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
    public static String AGORA = DATE_FORMAT.format(new Date());
    public static String NOVA_LINHA = System.getProperty("line.separator");

}
