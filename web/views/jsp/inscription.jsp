<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="outils.*" %>
<% 
    if(request.getAttribute("errors")!=null)
        {  ValueController con=(ValueController)request.getAttribute("errors");
            out.println("<div class='alert alert-danger'>");
                HashMap<String,ErrorMessage> errors=con.getErrorsMessage();
                for(String errorKey:errors.keySet()){
                    
                    out.println(errors.get(errorKey).getMessage());
                }
          
            out.println("</div>");
        }

    
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <link rel="stylesheet" href="./bootstrap-5.3.2-dist/css/bootstrap.min.css">
</head>
<body>

    <h1>inscription</h1>
    <div class="container">
        <form action="inscriptionPost" method="POST" role="form">
            <legend>inscription form</legend>
        
            <div class="form-group">
                <label for="username">username</label>
                <input type="text" name="user.nomUtilisateur" placeholder="username">
            </div>
        
            <div class="form-group">
                <label for="">password</label>
                <input type="password" name="user.pwdUtilisateur" placeholder="password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        
    </div>
   
    
</body>
</html>