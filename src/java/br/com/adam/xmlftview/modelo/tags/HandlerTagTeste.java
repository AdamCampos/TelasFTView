/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adam.xmlftview.modelo.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HandlerTagTeste extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        try {
            out.println("Testando o handler da tag");
            for (int i = 0; i < 10; i++) {
                out.println("<br>");
                out.println(String.format(String.valueOf(i), "0#.00"));
            }
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in HandlerTagTeste tag", ex);
        }
    }

}
