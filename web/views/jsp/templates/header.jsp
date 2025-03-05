<% String ctx=pageContext.getServletContext().getInitParameter("key"); %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="${ctx}/bootstrap-5.3.2-dist/css/bootstrap.min.css">
    <title>Etat financier</title>
    <style>
        body {
                font-size: 14px; /* Adjust this value to make the text smaller */
            }
        .sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            background-color: #f8f9fa;
            padding-top: 20px;
        }
        .sidebar a {
            padding: 10px 15px;
            text-decoration: none;
            font-size: 14px;
            color: #333;
            display: block;
        }
        .sidebar a:hover {
            background-color: #ddd;
        }
        .content {
            margin-left: 260px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h1 class="text-center">Etat financier</h1>   
        <a href="/etat">insertion valeur compte</a>

        <a href="/posteInsertion">insertion poste</a>
        <a href="/posteFInsertion">insertion post fille</a>
        <a href="/bilan">bilan</a>
        <%-- <a href="${ctx}/bloc/update">Update Bloc Source</a> --%>
    </div>
    <% if (request.getAttribute("message") != null) { %>
    <script>
        alert("<%= (String)request.getAttribute("message") %>");
    </script>
<% } %>
    <div class="content">
        <!-- Content goes here -->