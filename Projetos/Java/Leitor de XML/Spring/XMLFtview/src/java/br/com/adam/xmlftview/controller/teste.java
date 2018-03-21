/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Adam
 */
@Controller
public class teste {
    
    //O metodo retorna uma String. A String eh o nome da pagina .jsp
    //O requestMapping possui um atributo, que eh o termo colocado no navegador apos a /
    //Exemplo: http://localhost:8080/XMLFtview/testePg2
    @RequestMapping("/teste")
    public String metodoTeste(){
    
        return "testeSpring";
    }
    
        @RequestMapping("/testePg2")
    public String metodoPagina2(){
    
        return "pagina2";
    }
    
}
