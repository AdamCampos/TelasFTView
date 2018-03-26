<%-- 
    Document   : pagina2
    Created on : 21/03/2018, 15:17:43
    Author     : Adam

    Esta JSP eh utilizada para exercitar o livro Use A Cabeca - Servlets e JSP


--%>

<%@page import="br.com.adam.xmlftview.modelo.Leitor"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.File"%>
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

        <%-- Bean -> setNome() getNome() isNome() --%>

        <%--! --------------------------------------------------------------------------------------- --%>

        <%--
        <section>
            <style scoped>
                table { border-collapse: collapse; border: solid thick; }
                colgroup, tbody { border: solid medium; }
                td { border: solid thin; height: 1.4em; width: 1.4em; text-align: center; padding: 0; }
            </style>
            <h1>Today's Sudoku</h1>
            <table>
                <colgroup><col><col><col>
                <colgroup><col><col><col>
                <colgroup><col><col><col>
                <tbody>
                    <tr> <td> 1 <td>   <td> 3 <td> 6 <td>   <td> 4 <td> 7 <td>   <td> 9
                    <tr> <td>   <td> 2 <td>   <td>   <td> 9 <td>   <td>   <td> 1 <td>
                    <tr> <td> 7 <td>   <td>   <td>   <td>   <td>   <td>   <td>   <td> 6
                <tbody>
                    <tr> <td> 2 <td>   <td> 4 <td>   <td> 3 <td>   <td> 9 <td>   <td> 8
                    <tr> <td>   <td>   <td>   <td>   <td>   <td>   <td>   <td>   <td>
                    <tr> <td> 5 <td>   <td>   <td> 9 <td>   <td> 7 <td>   <td>   <td> 1
                <tbody>
                    <tr> <td> 6 <td>   <td>   <td>   <td> 5 <td>   <td>   <td>   <td> 2
                    <tr> <td>   <td>   <td>   <td>   <td> 7 <td>   <td>   <td>   <td>
                    <tr> <td> 9 <td>   <td>   <td> 8 <td>   <td> 2 <td>   <td>   <td> 5
            </table>
        </section>

        <%--! --------------------------------------------------------------------------------------- --%>

        --%>

        <form>
            <table
                <th>

                    <input type="text" name="formName" >
                </th>
                </br>
                <input type="submit" name="saida" >
            </table>
        </form>
        <br><br><br>
        <%= request.getAttribute("nome")%>
        <br><br><br>
        <hr>
        <% Leitor leitor = new Leitor();
            for (File f : leitor.getListaArquivosXML()) {
                out.println(f.getName());
            }
        %>
    </body>
</html>
