<%-- 
    Document   : testeJS
    Created on : 04/05/2018, 16:19:57
    Author     : Adam
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="js" uri="/WEB-INF/tlds/jsTLD" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Testando JS</title>
    </head>
    <body>

   <%--     <% request.setAttribute("respostaJSP", "xxxxxx");%> --%>
        <%= "Request: " + request.getParameterValues("respostaJSP")%>
        <br>
        <input type="submit" name="vemDaJSP" value="xxxxx"/>

        <h1>Teste JavaScript</h1>

        <%
            PrintWriter writer = response.getWriter();

            Enumeration<String> s = request.getAttributeNames();
            while (s.hasMoreElements()) {

                System.out.println("Param: " + s.nextElement()
                        + " Valor: " + request.getParameter(s.toString()));
            }

            for (int i = 0; i < 10; i++) {

                writer.print("0__ " + String.valueOf(i));
            }
            writer.close();
        %>

    </body>
</html>
