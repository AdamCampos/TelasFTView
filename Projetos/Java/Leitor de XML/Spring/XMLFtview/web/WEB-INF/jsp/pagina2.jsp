<%-- 
    Document   : pagina2
    Created on : 21/03/2018, 15:17:43
    Author     : Adam

    Esta JSP eh utilizada para exercitar o livro Use A Cabeca - Servlets e JSP


--%>

<%@page import="br.com.adam.xmlftview.modelo.Counter"%>
<%--
Os comentarios sao diferentes do HTML 
@xxx e =h um diretiva. Existem tres: @page, @include e @taglib

@page sera utilizado aqui para se fazer um import de uma classe que eh usada
dentro do codigo do JSP. O JSP aceita codigo HTML e codigo java dentro do corpo.

Elementos JSP
<%   ;%> Scriptlet
<%@  ;%> Diretiva
<%=   %> Expressao
<%!  ;%> Declaracao
${     } EL (expression language


--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Teste pagina 2</h1>
        <%-- Teste com o out.print--%>
        <% out.println("Contador " + Counter.getCount());%>
        </br>
        <%-- Teste com expression--%>
        <%= "Expressao contador= " + Counter.getCount() %>
    </body>
</html>
