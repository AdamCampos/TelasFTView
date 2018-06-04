
<%@page import="org.apache.jasper.runtime.JspWriterImpl"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.FileNotFoundException"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="br.com.adam.xmlftview.modelo.Parser"%>
<%@page import="java.io.File"%>
<%@page import="br.com.adam.xmlftview.modelo.DiretorioXML"%>
<%@taglib prefix="tagsAdam" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="handler" uri="/WEB-INF/tlds/tagsAdamLib" %>
<%@taglib prefix="dom" uri="/WEB-INF/tlds/tldInterfaceDOM" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="1kb" autoFlush="true" %>
<!DOCTYPE html>
<html>
    <head>

        <c:set var="xx" value="Adam"/>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/jsp.css" />
        <script type="text/javascript" src="js/jsp.js"></script>

        <title>Inserção de Front-end</title>

        <tagsAdam:header/>

    </head>
    <body onresize="msgResize();">
        <div id="divAntesTabela">

            <input type="button" value="Teste JS" name="btJS" onclick="handleClick(this.id);" id="btJS" />
            <br>
            <input type="button" value="bt2" name="bt2" onclick="click2();"/>
            <br>
            <input type="button" value="bt3" name="bt3" onclick="click3();"/>
            <br>
            <input type="button" value="chama jsp js" name="btJS_JSP" onclick="chamaJSP();"/>

        </div>

        A pesquisa pelo termo <span class="destaque"> ${termo} </span>
        retornou <span class="destaque"> ${itensRetorno} iten(s).</span>

        <div id="divTabela">
            <table id="tabela" border="1" >
                <dom:controleDom pesquisa="${param.termoPesquisaEL}" > 
                    <tr>
                        <td id="colunaId">
                            ${id}
                        </td>
                        <td>
                            ${item}
                        </td>
                        <td>
                            ${tela}
                        </td>
                    </tr>
                </dom:controleDom>  
            </table>
        </div>


        <div id="divRodape">

            <tagsAdam:footer/>   

        </div>

    </body>
</html>
