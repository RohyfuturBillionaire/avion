<% String ctx=pageContext.getServletContext().getInitParameter("key"); %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="outils.*" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <title>air jordan service</title>
    <style>
        body 
        {
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
        <h1 class="text-center">air jordan</h1>   
        <a href="list">liste des vol</a>
        <a href="insertionVol">insertion vol</a>
        <a href="listReservation">mes reservations</a>

    </div>
    <% if (request.getAttribute("message") != null) { %>
    <script>
        alert("<%= (String)request.getAttribute("message") %>");
    </script>
<% } %>
    <div class="content">
        <!-- Content goes here -->